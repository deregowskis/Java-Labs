package mini.java.basic.collections.test;

import java.io.IOException;
import java.util.*;

import static java.lang.Integer.min;

public class CachingAndPagingDataRepository {
    private SimpleDataRepository dataRepository;
    private HashMap<Integer, SimpleDataRepository.Product> cacheproducts = new HashMap<>();
    private HashMap<Integer, SimpleDataRepository.Warehouse> cachewarehouses = new HashMap<>();

    boolean productsarecached = false;
    boolean warehousesarecached = false;

    public CachingAndPagingDataRepository() throws ProductDataMalformedException, NoDataException, WarehouseDataMalformedException, IOException {
        dataRepository = new SimpleDataRepository("dataset.csv");
    }

    /**
     * This function should not be changed!!!
     * @return Returns number of calls registered by fake database engine
     */
    public int getRepositoryCalls() {
        return dataRepository.getCalls();
    }

    /***
     * Returns all products from database. Can be used to pre-populate single element
     * cache (for use in getProductById(id)). Can also be used as a helper function in
     * other methods.
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @return List of all products
     */
    public Collection<SimpleDataRepository.Product> getAllProducts() {
        if(productsarecached){
            return cacheproducts.values();
        }
        for(SimpleDataRepository.Product product : dataRepository.getProducts()){
            cacheproducts.put(product.getId(), product);
        }
        productsarecached = true;
        return cacheproducts.values();
    }

    /***
     * Retrieves product from database. In best case scenario should be aware of getAllProducts() method.
     * @see CachingAndPagingDataRepository#getAllProducts()
     * @param i id of product to retrieve
     * @return Retrieved product
     */
    public SimpleDataRepository.Product getProductsById(int i) {
        if (!cacheproducts.containsKey(i))
            cacheproducts.put(i,dataRepository.getProductById(i));
        return cacheproducts.get(i);
    }

    /***
     * Returns all products from database, sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsSortedByName() {
        List<SimpleDataRepository.Product> productstolist = new ArrayList<>(getAllProducts());
        Comparator<SimpleDataRepository.Product> comparator = new Comparator<SimpleDataRepository.Product>() {
            @Override
            public int compare(SimpleDataRepository.Product product1, SimpleDataRepository.Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        };
        productstolist.sort(comparator);
        return productstolist;

    }

    /***
     * Returns all products from database, filtered by expires true sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsFilteredByExpiresTrueAndSortedByName() {
        List<SimpleDataRepository.Product> productstolist = new ArrayList<>();
        for(SimpleDataRepository.Product product : getAllProducts()){
            if(product.isExpires()){
                productstolist.add(product);
            }
        }
        Comparator<SimpleDataRepository.Product> comparator = new Comparator<SimpleDataRepository.Product>() {
            @Override
            public int compare(SimpleDataRepository.Product product1, SimpleDataRepository.Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        };
        productstolist.sort(comparator);
        return productstolist;
    }

    /***
     * Returns page of products from database. Should behave gracefully - return empty
     * list - if page is out of bounds.
     * @param page Page number
     * @param pagesize Size of page
     * @return List of products
     */
        public Collection<SimpleDataRepository.Product> getProductsPage(int page, int pagesize) {
            List<SimpleDataRepository.Product> strona = new ArrayList<>();
            if(cacheproducts.size()==0){
                int i = 1;
                for(SimpleDataRepository.Product product : dataRepository.getProducts()){
                    cacheproducts.put(i, product);
                    i++;
                }
            }
            if(page*pagesize<cacheproducts.size()){
                for(int i=pagesize*page+1; i<=min(cacheproducts.size(), pagesize*page+pagesize); i++) {
                    strona.add(cacheproducts.get(i));
                }
            }
            return strona;
        }



    /***
     * Returns all warehouses from database. Should fill products field in each retrieved warehouse element
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @see SimpleDataRepository.Warehouse
     * @return List of all warehouses
     */
    public Collection<SimpleDataRepository.Warehouse> getAllWarehouses() {
        if(!warehousesarecached){
            int i = 1;
            for(SimpleDataRepository.Warehouse warehouse : dataRepository.getWarehouses()){
                List<SimpleDataRepository.Product> products = new ArrayList<>();
                for(int productid : warehouse.getProductIds()){
                    products.add(getProductsById(productid));
                }
                warehouse.setProducts(products);
                cachewarehouses.put(i, warehouse);
                i++;
            }
            warehousesarecached = true;
        }
        return cachewarehouses.values();
    }


    /**
     * Updates product in database
     * @param i Id of product to update
     * @param product Product data to save to database
     */
    public void updateProduct(int i, SimpleDataRepository.Product product) {
        dataRepository.updateProduct(i,product);
        cacheproducts.remove(i);
        productsarecached=false;
    }

    /**
     * Upserts product in database - inserts if id is missing, updates if id exists in database
     * @param product Product data to save to database
     */
    public void upsertProduct(SimpleDataRepository.Product product) {
        if(cacheproducts.containsKey(product.getId())){
            warehousesarecached = false;
        }
        dataRepository.upsertProduct(product);
        cacheproducts.remove(product.getId());
        productsarecached=false;
    }
}