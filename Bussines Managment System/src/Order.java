import java.util.HashMap;
import java.util.Scanner;

public class Order {

    HashMap<String, Double> checkIngredientHash = new HashMap<>();

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();
    Product product = new Product();
    Item item = new Item();
    FileHandler fileHandler = new FileHandler();


    public void showMenu(HashMap<String, Double> productPriceHash){
        System.out.println("## The Menu ##");
        System.out.println("Products     ==============      Price");
        for (String i : productPriceHash.keySet())
        {
            // fileHandler.readProductsHashMapFromFile();
            System.out.println(i + "      ==============      " + productPriceHash.get(i) + " SAR");
        }
    }


    public void order(HashMap<String, HashMap<String, Double>> productsHash, HashMap<String, Double> itemsHash, HashMap<String, Double> productPriceHash){
        System.out.println("Enter the name of the product that you want to order:");
        String name = input.nextLine();
        System.out.println("Enter the quantity of your order");
        double quantity = input.nextDouble();
        boolean itemExist = checkItemAvailability(name, quantity, productsHash, itemsHash);
    
        if (itemExist)
        {
            System.out.println("You have ordered " + quantity + " Pieces of " + name);
            System.out.println("The price is: " + addTax(name, productPriceHash));
            System.out.println("Your order has been placed, Thank you");
        }
        else {
            System.out.println("There isn't enough resources in the inventory to order the product");
        }
    }

    public double addTax(String name, HashMap<String, Double> productPriceHash)
    {
        System.out.println(productPriceHash);
        double price = productPriceHash.get(name) + (productPriceHash.get(name) * (15.0/100.0));
        return price;
    }

        public boolean checkItemAvailability(String name, double quantity, HashMap<String, HashMap<String, Double>> productsHash, HashMap<String, Double> itemsHash) {
        boolean itemExist = false;

        for (String i : productsHash.keySet())
        {
            checkIngredientHash = productsHash.get(i);
            for (String j : checkIngredientHash.keySet())
            {
                if (itemsHash.containsKey(j)) 
                {
                    if (itemsHash.get(j) > (checkIngredientHash.get(j) * quantity))
                    {
                        double newQuantity = itemsHash.get(j) - (checkIngredientHash.get(j) * quantity);
                        itemsHash.put(j, newQuantity);
                        itemExist = true;
                        System.out.println("There is enough quantity from " + i + " in the inventory");
                    }
                }  
            }
        }
        return itemExist;
        }
    }
    



    // product Product = new product();
    // item Item = new item();



    // public void showMenu() {
    //     System.out.println(productPriceHash);
    //     calculateItemPrice();
    //     calculateProductPrice();
    //     System.out.println("Product: =========>>  Price:");
    //     for (String i : productPriceHash.keySet())
    //     {
    //         System.out.println(i +"  =========>>   " + productPriceHash.get(i) + " SAR");
    //     }
    // }




    
    // public void calculaeProductPrice() {
    //     double ProductPrice = 0;
    //     for (String i : ItemPrice.keySet())
    //     {
    //         ProductPrice += ItemPrice.get(i);
    //     }
    // }}
    
    // public void calculaeProductPrice(){
    //     double productPrice = 0;

    //     for (String i : ItemsPerG.keySet())
    //     {
    //         float sumes = 0.0f;
    //         if(ingredients.containsKey(i))
    //         {
    //             itemPrice = ItemsPerG.get(i)*ingredients.get(i);
    //             // productPrice.put(i, itemPrice);}

    //     //     for (double f : productPrice.values()) {
    //     //         sumes += f;

    //     //     }System.out.println("the total prices is: " +sumes);
    //     // }
    //     System.out.println(productPrice);}}}


    
    
    
// public void calcaulattax(){
//     double pricewithtax = price+(price * 0.15);
//     System.out.println("the price with tax is:  " +pricewithtax);
// }

// public void calculateProductFinalPrice() {
//     double finalPrice;
// for (String i : productPrice.keySet()) {
//     // finalPrice = price - productPrice.get(i);
//     }
//     // System.out.println("the total prices is: " + finalPrice);
// }
// }









    




// public void calculateItemPrice() {
//         double itemPrice = 0;
//         for (String i : ItemsPerG.keySet())
//         {
//             if(ingredients.containsKey(i))
//             {
//                 itemPrice = ItemsPerG.get(i)*ingredients.get(i);
//                 System.out.println(ItemsPerG.get(i));
//                 System.out.println(ingredients.get(i));
//                 ItemPrice.put(i, itemPrice);
//             }
//             else
//             {
//                 System.out.println("the items of the product isn't available in the inventory");
//             }

//         }
//         System.out.println(ItemPrice);

//     }