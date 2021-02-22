package mini.java.basic.collections.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataMappers {
    /**
     * Maps line of data to @SimpleDataRepository.Product
     * @param objects Data loaded from DataLoader
     * @return Object[] parsed into @SimpleDataRepository.Product
     * @throws ProductDataMalformedException thrown if data is malformed/cannot be parsed
     */
    public static SimpleDataRepository.Product mapToProduct(Object[] objects) throws ProductDataMalformedException {

        if(objects.length==4) {
            for(Object i : objects){
                if(i==null){
                    throw new ProductDataMalformedException();
                }
            }
            int id = Integer.parseInt((String) objects[0]);
            String name = objects[1].toString();
            double price = Double.parseDouble((String) objects[2]);
            boolean expires = Boolean.parseBoolean((String) objects[3]);

            return (new SimpleDataRepository.Product(id, name, price, expires));
        }
        else{
            throw new ProductDataMalformedException();

        }
    }


    /**
     * Maps line of data to @SimpleDataRepository.Warehouse
     * @param objects Data loaded from DataLoader
     * @return Object[] parsed into @SimpleDataRepository.Warehouse
     * @throws WarehouseDataMalformedException thrown if data is malformed/cannot be parsed
     */
    public static SimpleDataRepository.Warehouse mapToWarehouse(Object[] objects) throws WarehouseDataMalformedException {

        if(objects.length<3) {
            throw new WarehouseDataMalformedException();
        }
            for(Object i : objects){
                if(i==null){
                    throw new WarehouseDataMalformedException();
                }
            }
            int id = Integer.parseInt((String) objects[0]);
            String location = objects[1].toString();
            boolean open = Boolean.parseBoolean((String) objects[2]);

            List<Integer> product = new ArrayList<>();

        if(objects.length==3){
            return new SimpleDataRepository.Warehouse(id, location, open, product);
        }
            String[] listaproduktow = ((String) objects[3]).split(",");
            for (String idproduktu : listaproduktow) {
                product.add(Integer.parseInt(idproduktu));
            }
            return (new SimpleDataRepository.Warehouse(id, location, open, product));
        }

    }


