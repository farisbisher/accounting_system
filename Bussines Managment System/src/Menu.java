import java.util.Scanner;
public class Menu {
	Scanner input = new Scanner(System.in);
    Item item = new Item();
    Product product = new Product();
    FileHandler fileHandler = new FileHandler();
    public void displayAdminDashboard() {
        int choice = 0;
        System.out.println("### Welcome to the most modern business management program as Admin ###");
        System.out.println(" 1. Manage Items \n 2. Manage Products \n 3. Manage Orders \n 4. Save and Logout");          
        choice = input.nextInt();
        manageAdminDashboard(choice);
    }
    public void displayCashierDashboard() {
        int casherChoice = 0;
        System.out.println("### Welcome to the most modern business management program as Casher ###");
        System.out.println(" 1. Manage Orders \n 2. Save and Logout");          
        casherChoice = input.nextInt();
        manageCashierDashboard(casherChoice);
    }
    
    public void manageAdminDashboard(int choice) {

        switch (choice) {
            
            case 1:
            
            manageItems();
            break;
            
            case 2:
            manageProducts();
            break;

            case 3:
            product.productPriceHash = fileHandler.readProductsPriceHashMapFromFile();
            orderChoices();
            break;
            
            case 4:

                fileHandler.writeItemsHashMapToFile(item.itemsHash, item.itemsPricePerGHash, item.itemsMeasurementHash, item.itemsPriceHash);
                fileHandler.writeProductsHashMapToFile("products.txt", product.productsHash, product.productPriceHash);

                System.out.println("Saving................................");
                System.out.println("Existing Program");
                System.exit(0);

            default:
                System.out.println("Invalid choice");
        }
    }
    public void manageCashierDashboard(int casherChoice) {
        switch (casherChoice) {
            case 1:
            product.productPriceHash = fileHandler.readProductsPriceHashMapFromFile();
            casherOrderChoices();
            break;
            
            case 2:
                fileHandler.writeItemsHashMapToFile(item.itemsHash, item.itemsPricePerGHash, item.itemsMeasurementHash, item.itemsPriceHash);
                fileHandler.writeProductsHashMapToFile("products.txt", product.productsHash, product.productPriceHash);
                System.out.println("Saving................................");
                System.out.println("Loging out");
                System.exit(0);

            default:
                System.out.println("Invalid choice");
        }
    }
    
    public void manageItems() {
        int itemsChoice = 0;

        while (itemsChoice != 5){

            System.out.println(" 1. Add Item \n 2. Edit Item \n 3. Delete Item \n 4. Show All Items \n 5. Back To Main Menu");
            itemsChoice = input.nextInt();

            switch (itemsChoice) {
                case 1:
    
                item.addItem();
                break;
                
                case 2:
                item.editItem();
                break;
    
                case 3:
                item.delete();
                break;
    
                case 4:
                item.showItems();
                break;
                
                case 5:
                displayAdminDashboard();
                break;
    
                default:
                System.out.println("Invalid choice");
            }
        }
    }

    public void manageProducts() {
        int productsChoice = 0;

        while (productsChoice != 5){
            System.out.println(" 1. Add Product \n 2. Edit Product Price \n 3. Delete Product \n 4. Show Current Products \n 5. Back To Main Menu");
            String name;
            productsChoice = input.nextInt();

            switch (productsChoice) {
                case 1:
                name = product.addProduct();
                product.setProductIngredientPrice(item.itemsPricePerGHash);
                product.setProductPrice(name);
                fileHandler.writeProductsHashMapToFile("products.txt", product.productsHash, product.productPriceHash);

                break;
                
                case 2:
                product.editProductPrice();
                fileHandler.writeProductsHashMapToFile("products.txt", product.productsHash, product.productPriceHash);

                break;
                
                case 3:
                product.deleteProduct(product.fileProductsHash);
                break;

                case 4:
                product.displayCurrentProducts();
                break;

                case 5:
                displayAdminDashboard();
                break;

                default:
                System.out.println("Invalid choice");
            }
            }
    }


    public void orderChoices(){
        int orderChoice = 0;
        Order order = new Order();
        while (orderChoice != 3){
            System.out.println("Enter your choice: \n 1- Show The Menu \n 2- Order a Product \n 3- Back To The Menu");
            orderChoice = input.nextInt();
            switch(orderChoice)
            {
                case 1: 
                order.showMenu(product.productPriceHash);
                break;

                case 2: 
                order.order(product.productsHash, item.itemsHash, product.productPriceHash);
                break;

                case 3:
                displayAdminDashboard();
                break;
            }
        }
    }

    public void casherOrderChoices(){
        int casherOrderChoices = 0;
        Order order = new Order();
        while (casherOrderChoices != 3){
            System.out.println("Enter your choice: \n 1- Show The Menu \n 2- Order a Product \n 3- Back To The Menu");
            casherOrderChoices = input.nextInt();
            switch(casherOrderChoices)
            {
                case 1: 
                order.showMenu(product.productPriceHash);
                break;

                case 2: 
                order.order(product.productsHash, item.itemsHash, product.productPriceHash);
                break;

                case 3:
                displayCashierDashboard();
                break;
            }
        }
    }
}
        
            




