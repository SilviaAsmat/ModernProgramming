
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

public class Program6 {
    private final ArrayList<StoreItem> inventory;
    private final CashRegister cashRegister;
    private final Scanner scanner;
    private final HashMap<Integer, Callable<Boolean>> menuOptionsMap = new HashMap<>();

    public Program6() 
    {
        inventory = new ArrayList<>();
        cashRegister = new CashRegister();
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) 
    {
        Program6 program = new Program6();
        program.run();
    }
    public void run() 
    {
        getFilesFromUser();
        loadMenuMap();

        int choice = 0;
        boolean continueLoop = true;
        while (choice >= 0 && continueLoop == true) 
        {
            cashRegister.displayMenu(inventory);
            choice = getUserInput();
            if (choice >= 0)
            {
                continueLoop = processMenuOption(choice);
            }
            if (choice < 0)
            {
                continueLoop = false;
                System.out.println("Goodbye!");
            }
        }
    }

    public Integer getUserInput() 
    {
        int choice = 0;
        boolean valid = false;
        while (!valid) 
        {
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            try 
            {
                choice = Integer.parseInt(input);
                System.out.println("Input: " + choice);
                valid = true; 
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input.");
            }
        }
        return choice;
    }
    private void getFilesFromUser() 
    {
        System.out.println("===============================================================");
        System.out.println("Welcome to the Cash Register Program!");
        System.out.println("===============================================================");
        System.out.print("Enter the inventory file name: ");
        String inventoryFileName = validateFileName();
        fillInventory(inventoryFileName);
        System.out.print("Enter the cashier file name: ");
        String cashierFileName = validateFileName();
        fillCashier(cashierFileName);
    }
    private void fillCashier(String cashierFileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(cashierFileName))) 
            {
                List<String> cashiers = new ArrayList<>();
                String line = br.readLine();
                while ( line != null) 
                {
                    cashiers.add(line);
                    line = br.readLine();
                }
                cashRegister.setCashiers(cashiers);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
    }
    private String validateFileName() 
    {
        boolean isValid = false;
        String fileName = "";
        while(!isValid)
        {
            fileName = scanner.nextLine(); 
            try 
            {
                new BufferedReader(new FileReader(fileName)).close();
                isValid = true;
            } 
            catch (IOException e) 
            {
                System.out.println("Error reading file: " + e.getMessage());
                System.out.print("Enter a valid file name: ");
            }  
        }
        return fileName;
    }
    
    private void fillInventory(String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) 
            {
                // Item Number, Item Description, Units in Inventory, and Price
                //1000     Pants      10     19.99
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] parts = line.split("\\s+");
                    int itemNumber = Integer.parseInt(parts[0].trim());
                    String itemDescription = parts[1].trim();
                    int unitsInInventory = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    inventory.add(new StoreItem(itemNumber, itemDescription, unitsInInventory, price));
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

    }

    // private Integer handleChoice(int choice) 
    // {
    //     System.out.println("===============================================================");
    //     switch (choice) 
    //     {
    //         case 1 -> {
    //             System.out.print("Enter item description(s) to purchase: ");
    //             String input = scanner.nextLine();
    //             String[] itemDescriptions = input.trim().split("[\\s,]+");
    //             Arrays.stream(itemDescriptions).forEach(System.out::println);
    //             cashRegister.purchaseItem(inventory, itemDescriptions);
    //             return 1;
    //         }
    //         case 2 -> {cashRegister.showItems();
    //             return 1;}
    //         case 3 -> {
    //             cashRegister.clearRegister(inventory);
    //             System.out.println("Cash register cleared.");
    //             return 1;}
    //         case 4 -> {cashRegister.showInventory(inventory);
    //             return 1;}
    //         case 5 -> {
    //             return cashRegister.checkOut();}
    //         default -> {System.out.println("Invalid choice.");
    //             return 1;}
    //     }
    // }
    // 

    public void loadMenuMap() {
        int length = inventory.size();
        menuOptionsMap.put(length, this::purchaseItem);
        menuOptionsMap.put(1 + length, this::showCashRegister);
        menuOptionsMap.put(2 + length, this::clearCashRegister);
        menuOptionsMap.put(3 + length, this::showInventory);
        menuOptionsMap.put(4 + length, this::checkOut);
    }

    public boolean processMenuOption(int option) {
        Callable<Boolean> action = menuOptionsMap.getOrDefault(option, this::processDefaultMenuOption);
        try {
            return action.call();
        } catch (Exception e) {
            System.out.println("Error processing menu option: " + e.getMessage());
            return false;
        }
    }

    private Boolean purchaseItem() {
        System.out.print("Enter item description(s) to purchase: ");
        String input = scanner.nextLine();
        String[] itemDescriptions = input.trim().split("[\\s,]+");
        Arrays.stream(itemDescriptions).forEach(System.out::println);
        cashRegister.purchaseItem(inventory, itemDescriptions);
        return true;
    }
    private Boolean showCashRegister() {
        System.out.println("Showing cash register...");
        cashRegister.showItems();
        return true;
    }

    private Boolean clearCashRegister() {
        System.out.println("Clearing cash register...");
        cashRegister.clearRegister(inventory);
        return true;
    }

    private Boolean showInventory() {
        System.out.println("Showing inventory...");
        cashRegister.showInventory(inventory);
        return true;
    }

    private Boolean checkOut() {
        boolean checkedOut = cashRegister.checkOut();
        System.out.println("Checked out: " + !checkedOut);
        return checkedOut;
    }

    private Boolean processDefaultMenuOption() {
        System.out.println("Invalid choice.");
        return true;
    }
}