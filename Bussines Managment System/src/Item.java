import java.util.Scanner;
import java.util.HashMap;


public class Item extends Material{

    public HashMap<String, Double> itemsHash = new HashMap<String, Double>();
    public HashMap<String, Double> itemsPricePerGHash = new HashMap<String, Double>();
    public HashMap<String, String> itemsMeasurementHash = new HashMap<String, String>();
    public HashMap<String, Double> itemsPriceHash = new HashMap<String, Double>();

    
    FileHandler fileHandler = new FileHandler();
    HashMap<String, Double> viewItemsHash = fileHandler.readItemsHashMapFromFile(itemsHash, itemsPricePerGHash, itemsMeasurementHash, itemsPriceHash);


    Scanner input = new Scanner(System.in);

    public void addItem() {
            double itemsPricePerG;
            boolean itemExist = false;
            boolean bMeasurement = false;
            String measurement = "";

            System.out.println("Enter the name of the item:");
            name = input.nextLine();

            while (!bMeasurement) {
                System.out.println("Choose the measurement of the item: \n 1- kg \n 2- g \n 3- L \n 4- ml \n 5- pcs");
                measurement = input.next();

                switch (measurement) {
                    case "1":
                        measurement = "kg";
                        bMeasurement = true;
                        break;
        
                    case "2":
                        measurement = "g";
                        bMeasurement = true;
                        break;
        
                    case "3":
                        measurement = "l";
                        bMeasurement = true;
                        break;
        
                    case "4":
                        measurement = "ml";
                        bMeasurement = true;
                        break;
        
                    case "5":
                        measurement = "pcs";
                        bMeasurement = true;
                        break;
        
                    default:
                        System.out.println("Invalid choice");
                }

            }
            
            System.out.println("Enter the quantity:");
            double quantity = input.nextDouble();
            input.nextLine();

            System.out.println("Enter the price of the total amount of the item in riyals:");
            price = input.nextDouble();
            input.nextLine();
            
            switch (measurement) {
                case "kg":
                quantity *= 1000;
                measurement = "g";
                break;

                case "l":
                quantity *= 1000;
                measurement = "ml";
                break;

            }

                itemsPricePerG = price/quantity;

                if (itemsHash.containsKey(name)) {
                    double existingQuantity = itemsHash.get(name);
                    double existingPrice = itemsPriceHash.get(name);
                    double existingItemsPerG = itemsPricePerGHash.get(name);

                    itemsHash.put(name, existingQuantity + quantity);
                    itemsPriceHash.put(name, existingPrice + price);
                    itemsPricePerGHash.put(name, existingItemsPerG + itemsPricePerG);

                    System.out.println(itemsPriceHash);
                    System.out.println(itemsHash);
                    System.out.println(itemsPricePerGHash);
                    
                    System.out.println(name + " already exists, Quantity updated to: " + itemsHash.get(name) + measurement + " Price updated to: " + itemsPriceHash.get(name) + "SAR");
                    itemExist = true;
                } 

                
            if (!itemExist) {
                itemsPriceHash.put(name, price);
                itemsHash.put(name, quantity);
                itemsPricePerGHash.put(name, itemsPricePerG);
                itemsMeasurementHash.put(name, measurement);
                fileHandler.writeItemsHashMapToFile(itemsHash, itemsPricePerGHash, itemsMeasurementHash, itemsPriceHash);
                System.out.println("Your Item has been added to the inventory successfully");
                System.out.println(itemsPricePerGHash);
            }
    }

    public void editItem(){
        double itemsPricePerG;
        boolean bMeasurement = false;
        String measurement = "";

        System.out.println("Enter the name of the item:");
        String name = input.nextLine();
            
            if (itemsHash.containsKey(name)) {
                while (!bMeasurement) {
                System.out.println("Choose the measurement of the item: \n 1- kg \n 2- g \n 3- L \n 4- ml \n 5- pcs");
                measurement = input.next();

                switch (measurement) {
                    case "1":
                        measurement = "kg";
                        bMeasurement = true;
                        break;
        
                    case "2":
                        measurement = "g";
                        bMeasurement = true;
                        break;
        
                    case "3":
                        measurement = "l";
                        bMeasurement = true;
                        break;
        
                    case "4":
                        measurement = "ml";
                        bMeasurement = true;
                        break;
        
                    case "5":
                        measurement = "pcs";
                        bMeasurement = true;
                        break;
        
                    default:
                        System.out.println("Invalid choice");
                }

                }

                System.out.println("Enter the quantity:");
                double quantity = input.nextDouble();
                input.nextLine();
                
                System.out.println("Enter the price of the total amount of the item in riyals:");
                double price = input.nextDouble();
                input.nextLine();

                switch (measurement) {
                    case "kg":
                    quantity *= 1000;
                    measurement = "g";
                    break;

                    case "l":
                    quantity *= 1000;
                    measurement = "ml";
                    break;

                }

                itemsPricePerG = price/quantity;

                    itemsHash.put(name, quantity);
                    itemsPriceHash.put(name, price);
                    itemsMeasurementHash.put(name, measurement);
                    itemsPricePerGHash.put(name, itemsPricePerG);

                    System.out.println(itemsPriceHash);
                    System.out.println(itemsHash);
                    System.out.println(name + " has been updated successfully, Quantity updated to: " + itemsHash.get(name) + measurement + " Price updated to: " + itemsPriceHash.get(name) + "SAR");
            } else {
                System.out.println("Sorry I did not find any items with this name, please try again");
            }
        }
    
    public void delete() {
        System.out.println("Enter the name of the item you want to delete:");
        String name = input.next();
        input.nextLine();
        if (itemsHash.containsKey(name)) {
                itemsHash.remove(name);
                itemsPriceHash.remove(name);
                itemsMeasurementHash.remove(name);
                itemsPricePerGHash.remove(name);
                System.out.println(name + " has been deleted from the inventory");
                return;
        } else {
            System.out.println("Sorry I did not find any items with this name, please try again");
        }
    }
    
    public void showItems() {
        
        System.out.println("all items:");
        for(String i : itemsHash.keySet())
        {
            System.out.println(i + ": " + itemsHash.get(i) + itemsMeasurementHash.get(i));
        }
    }


    
    
}