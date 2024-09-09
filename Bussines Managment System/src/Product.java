import java.util.Scanner;
import java.util.HashMap;

public class Product extends Material {

  HashMap <String, HashMap<String, Double>> productsHash = new HashMap<>();
  HashMap <String, Double> productSubIngrediantsHash = new HashMap<>();
  HashMap<String, Double> productItemsPriceHash = new HashMap<>();
  HashMap<String, Double> productCostHash = new HashMap<>();
  HashMap<String, Double> productPriceHash = new HashMap<>();

  FileHandler fileHandler = new FileHandler();

  HashMap<String, HashMap<String, Double>> fileProductsHash = fileHandler.readProductsHashMapFromFile();

  Scanner input = new Scanner(System.in);
  Item item = new Item();

  public HashMap<String, Double> buildIngredientsHash(int numberItems) {
        String itemName;
        String itemMeasurement;
        double itemQuantity;
        HashMap <String, Double> productSubIngrediantsHash = new HashMap<String, Double>();

        for (int i = 1; i <= numberItems; i++) {
            System.out.println("Enter item " + i + " name:");
            itemName = input.nextLine(); 

            System.out.println("Choose the measurement of item " + i + ":\n1- g\n2- ml\n3- pcs");
            itemMeasurement = input.next(); 

            System.out.println("Enter the quantity of item " + i + " you want to include in the product:");
            itemQuantity = input.nextDouble(); 
            input.nextLine();

            productSubIngrediantsHash.put(itemName, itemQuantity); 
        }
        return productSubIngrediantsHash;
    }

  public String addProduct() {
    boolean productExist = false;
    System.out.println("Enter the name of the product:");
    name = input.nextLine();

    if (productsHash.containsKey(name)) {
        System.out.println("Product already exists. Please try again with a new name for the product.");
        productExist = true;
    } else {
        System.out.println("Enter the number of items you want the product to include:");
        int numberItems = input.nextInt();
        input.nextLine();

        if (!productExist) {
            productsHash.put(name, buildIngredientsHash(numberItems));
            System.out.println("Your product has been added to the menu successfully");
        }
      }
        return name;
    }


  public void editProductPrice() {
    System.out.println("Enter the name of the product:");
    String name = input.nextLine();

    if(productCostHash.containsKey(name)){
      double cost = productCostHash.get(name);
      System.out.println("The cost price of " + name + " is " + cost +"SAR");

      System.out.println("Enter the new price of the product:");
      double price = input.nextDouble();
      input.nextLine();

      productPriceHash.put(name, price);
    } else {
      System.out.println("Sorry I did not find any Products with this name, please try again");
    }
  }


  public void deleteProduct(HashMap<String, HashMap<String, Double>> fileProductsHash) {
    System.out.println("Enter the name of the product you want to delete:");
            String name = input.nextLine();

            if (fileProductsHash.containsKey(name)) {
                      fileProductsHash.remove(name);
                      productItemsPriceHash.remove(name);
                      productCostHash.remove(name);
                      productPriceHash.remove(name);
                      System.out.println(name + " has been deleted from the Products");
                      return;
            } else {
              System.out.println("Sorry I did not find any Products with this name, please try again");
          }
  }

  public void setProductIngredientPrice(HashMap<String, Double> itemsPricePerGHash){
    double productSum = 0;
    for (String i : productsHash.keySet()){ 
    productItemsPriceHash = productsHash.get(i); 
      for (String j : productItemsPriceHash.keySet()) { 
        if (itemsPricePerGHash.containsKey(j)){  
          productSum += (itemsPricePerGHash.get(j) * productItemsPriceHash.get(j)); 
          productCostHash.put(i, productSum);
        }
      }
      productSum = 0;
    }
    System.out.println("The Ingrediants Price of The " + productCostHash);
  }


    public void setProductPrice(String name){

      System.out.println("Enter the final price of the product:");
      price = input.nextDouble();
      input.nextLine();
      productPriceHash.put(name, price);
      System.out.println(productPriceHash);
    }

    public void displayCurrentProducts(){
        System.out.println("Your current products: " + fileProductsHash);
    }
}




