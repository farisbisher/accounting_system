import java.util.Scanner;
public class Auth {
    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();
    FileHandler fileHandler = new FileHandler();
    Item item = new Item();
    Product product = new Product();

    


    public void Auth() {
        System.out.println("--------Login--------\n 1. Admin\n 2. Cashier");
        int login = input.nextInt();
        String [][] access = new String[2][1];
        access [0][0] = "Permissions";
        
        switch (login){
            case 1:
            access[1][0] = "Admin";
            break;

            case 2:
            access[1][0] = "Cashier";
            break;
        }

        if (access[1][0] == "Admin"){

            fileHandler.readItemsHashMapFromFile(item.itemsHash, item.itemsPricePerGHash, item.itemsMeasurementHash, item.itemsPriceHash);
            fileHandler.readProductsHashMapFromFile();
            menu.displayAdminDashboard();
        }
        else if (access[1][0] == "Cashier")
        {   
            fileHandler.readItemsHashMapFromFile(item.itemsHash, item.itemsPricePerGHash, item.itemsMeasurementHash, item.itemsPriceHash);
            fileHandler.readProductsHashMapFromFile();
            menu.displayCashierDashboard();
        }
        else {
            System.out.println("You have to enter one of the choices");
        }
    }
}