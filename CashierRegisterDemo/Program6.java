
import java.io.*;
import java.util.*;

public class Program6 {
    private final ArrayList<StoreItem> inventory;
    private final CashRegister cashRegister;
    private final Scanner scanner;

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
        getFileNames();
        
        int choice = 0;
        while (choice >= 0) 
        {
            cashRegister.displayMenu(inventory);
            choice = getUserInput();
            if (choice >= 0)
            {
                handleChoice(choice);
            }
            if (choice < 0)
            {
                System.out.println("Goodbye!");
            }
        }
    }
    // public String getUserInput(String prompt)
	// {
	// 	System.out.print(prompt);
		
	// 	String inputData = input.nextLine();

	// 	return inputData;
	// }
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

    private void getFileNames() 
    {
        System.out.println("===============================================================");
        System.out.println("Welcome to the Cash Register Program!");
        System.out.println("===============================================================");

        System.out.print("Enter the inventory file name: ");
        loadFile();
    }
    private String validateFileName() 
    {
        boolean isValid = false;
        while(!isValid)
        {
            
            String fileName = scanner.nextLine();
            
        }
        
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

    private void handleChoice(int choice) 
    {
        System.out.println("===============================================================");
        switch (choice) 
        {
            // case 1:
            //     System.out.print("Enter item description(s) to purchase: ");
            //     // Allow user to enter multiple items
            //     String itemDescription = scanner.nextLine();
            //     StoreItem item = inventory.stream()
            //                               .filter(i -> i.getItemDescription().equals(itemDescription))
            //                               .findFirst()
            //                               .orElse(null);
            //     if (item != null) {
            //         cashRegister.purchaseItem(inventory, item);
            //         System.out.println("Item added to cash register.");
            //     } 
            //     else 
            //     {
            //         System.out.println("Item not found.");
            //     }
            //     break;
            case 1:
                System.out.print("Enter item description(s) to purchase: ");
                String input = scanner.nextLine();
                String[] itemDescriptions = input.trim().split("[\\s,]+");
                Arrays.stream(itemDescriptions).forEach(System.out::println);
                cashRegister.purchaseItem(inventory, itemDescriptions);
                break;
            case 2:
                cashRegister.showItems();
                break;
            case 3:
                cashRegister.clearRegister(inventory);
                System.out.println("Cash register cleared.");
                break;
            case 4:
                cashRegister.showInventory(inventory);
                break;
            case 5:
                cashRegister.checkOut();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

}