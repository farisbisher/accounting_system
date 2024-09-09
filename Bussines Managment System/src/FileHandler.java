import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {

    public void writeProductsHashMapToFile(String filename, HashMap<String, HashMap<String, Double>> productsHash, HashMap<String, Double> priceHashMap) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            for (Map.Entry<String, HashMap<String, Double>> entry : productsHash.entrySet()) {
                String productName = entry.getKey();
                HashMap<String, Double> ingredientMap = entry.getValue();
    
                writer.println(productName);
                
                if (priceHashMap.containsKey(productName)) {
                    Double productPrice = priceHashMap.get(productName);
                    writer.println("Price=" + productPrice);
                } else {
                    writer.println("Price= 0");
                }
    
                for (Map.Entry<String, Double> ingredientEntry : ingredientMap.entrySet()) {
                    String ingredientName = ingredientEntry.getKey();
                    Double quantity = ingredientEntry.getValue();
    
                    if (ingredientName.equals("price")) {
                        continue;
                    }
    
                    writer.println(ingredientName + "=" + quantity);
                }
    
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, HashMap<String, Double>> readProductsHashMapFromFile() {
        String filename = "products.txt";
        HashMap<String, Double> productPriceHash = new HashMap<>();
        HashMap<String, HashMap<String, Double>> productsHash = new HashMap<>();
    
        try (Scanner scanner = new Scanner(new File(filename))) {
            String productName = null;
            HashMap<String, Double> ingredientMap = new HashMap<>();
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
    
                if (line.isEmpty()) {
                    if (productName != null && !ingredientMap.isEmpty()) {
                        productsHash.put(productName, ingredientMap);
                        ingredientMap = new HashMap<>();
                        productName = null; 
                    }
                } else if (line.startsWith("Price=")) {
                    String priceString = line.substring(6).trim();
                    Double productPrice = priceString.equals("N/A") ? null : Double.parseDouble(priceString);
                    productPriceHash.put(productName, productPrice);
                } else {
                    if (productName == null) {
                        productName = line; 
                    } else {
                        String[] parts = line.split("=");
                        if (parts.length == 2) {
                            String ingredientName = parts[0].trim();
                            Double quantity = Double.parseDouble(parts[1].trim());
                            ingredientMap.put(ingredientName, quantity);
                        }
                    }
                }
            }
    
            if (productName != null && !ingredientMap.isEmpty()) {
                productsHash.put(productName, ingredientMap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return productsHash;
    }
    
    
    public HashMap<String, Double> readProductsPriceHashMapFromFile() {
        String filename = "products.txt";
        HashMap<String, Double> productPriceHash = new HashMap<>();
    
        try (Scanner scanner = new Scanner(new File(filename))) {
            String productName = null;
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
    
                if (line.isEmpty()) {
                    productName = null; 
                } else if (line.startsWith("Price=")) {
                    String priceString = line.substring(6).trim();
                    Double productPrice = priceString.equals("N/A") ? null : Double.parseDouble(priceString);
                    productPriceHash.put(productName, productPrice);
                } else {
                    if (productName == null) {
                        productName = line; 
                    } else {
                        String[] parts = line.split("=");
                        if (parts.length == 2) {
                            String ingredientName = parts[0].trim();
                            Double quantity = Double.parseDouble(parts[1].trim());
    
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        return productPriceHash;
    }

    
    public void writeItemsHashMapToFile(  HashMap<String, Double> itemsHash,  
                                                HashMap<String, Double> itemsPricePerGHash, 
                                                HashMap<String, String> itemsMeasurementHash, 
                                                HashMap<String, Double> itemsPriceHash ) {
        String fileName = "items.txt";

        try {
            File file = new File(fileName);
            if (file.exists()) {

                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(itemsHash + "\n" + itemsPricePerGHash + "\n" + itemsMeasurementHash + "\n" + itemsPriceHash + "\n");
                    bufferedWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public HashMap<String, Double> readItemsHashMapFromFile(HashMap<String, Double> itemsHash,
                                     HashMap<String, Double> itemsPricePerGHash,
                                     HashMap<String, String> itemsMeasurementHash,
                                     HashMap<String, Double> itemsPriceHash) {
    try (BufferedReader br = new BufferedReader(new FileReader("items.txt"))) {
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null) {
            String[] keyValuePairs = line.replace("{", "").replace("}", "").split(",");
            HashMap<String, Double> currentMap = null;
            switch (lineNumber) {
                case 1:
                    currentMap = itemsHash;
                    break;
                case 2:
                    currentMap = itemsPricePerGHash;
                    break;
                case 3:
                    currentMap = null; 
                    for (String pair : keyValuePairs) {
                        String[] keyValue = pair.split("=");
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        itemsMeasurementHash.put(key, value);
                    }
                    break;
                case 4:
                    currentMap = itemsPriceHash;
                    break;
            }
            if (currentMap != null) {
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split("=");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    try {
                        double numericValue = Double.parseDouble(value);
                        currentMap.put(key, numericValue);
                    } catch (NumberFormatException e) {
                        
                    }
                }
            }

            lineNumber++;
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
            catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("");;
        }

        return itemsHash;
}


}
