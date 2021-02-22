package mini.java.basic.collections.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public List<Object[]> getData(String dataFileName, String dataset) throws IOException, NoDataException {
        List<Object[]> dataSetObjects = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(dataFileName));
        String line;
        while ((line = csvReader.readLine()) != null) {
            Object[] nowyRekord = line.split(";");
            if((nowyRekord[0]).toString().equals(dataset)) {
                Object[] doZapisu = new Object[nowyRekord.length-1];
                for(int i=1;i<nowyRekord.length;i++){
                    doZapisu[i-1]=nowyRekord[i];
                }
                dataSetObjects.add(doZapisu);
            }
        }
        csvReader.close();
        if(dataSetObjects.size()==0){
            throw new NoDataException();
        }
        return dataSetObjects;
    }
}