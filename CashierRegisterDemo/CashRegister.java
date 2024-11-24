import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashRegister 
{
    private final List<StoreItem> cart;
    private final List<String> cashiers;
    private final Scanner scan = new Scanner(System.in);
    
    // Constructor
    public CashRegister() 
    {
        cart = new ArrayList<>();
        cashiers = new ArrayList<>();
    }


    // Methods
    public void purchaseItem(List<StoreItem> inventory , String[] itemDescriptions)
    {
        for (String description : itemDescriptions) {
            String trimmedDescription = description.trim();
            StoreItem item = inventory.stream()
                                    .filter(i -> i.getItemDescription().equals(trimmedDescription) && i.getUnitsInInventory() > 0)
                                    .findFirst()
                                    .orElse(null);
            if (item != null) {
                //Decrease the item from inventory and add it to the cart
                inventory.stream()
                         .filter(i -> i.getItemDescription().equals(trimmedDescription))
                         .findFirst()
                         .ifPresent(i -> i.setUnitsInInventory(i.getUnitsInInventory() - 1));
                cart.add(item);
                System.out.println("Item added to cash register: " + trimmedDescription);
            } else {
                System.out.println("Item not found: " + trimmedDescription);
            }
        }
        
    }

    public double getTotal() 
    {
        return cart.stream().mapToDouble(StoreItem::getPrice).sum();
    }

    public void showItems() 
    {
        cart.stream()
            .sorted((item1, item2) -> item1.getItemDescription().compareTo(item2.getItemDescription()))
            .forEach(item -> System.out.println(item.getItemDescription() + ": " + item.getPrice()));
    }

    public void clearRegister(List<StoreItem> inventory) 
    {
        // Add all items in cart back to inventory
        for (StoreItem item : cart) {
            inventory.stream()
                    .filter(i -> i.getItemDescription().equals(item.getItemDescription()))
                    .findFirst()
                    .ifPresent(i -> i.setUnitsInInventory(i.getUnitsInInventory() + 1));
                    
            if (inventory.stream().noneMatch(i -> i.getItemDescription().equals(item.getItemDescription()))) {
                inventory.add(new StoreItem(item.getItemNumber(), item.getItemDescription(), 1, item.getPrice()));
            }
        }
        cart.clear();
        System.out.println("Cash register cleared.");
    }

    public void showInventory(List<StoreItem> inventory) 
    {
        inventory.forEach(item -> System.out.println(item.getItemDescription() + ": " + item.getUnitsInInventory()));
    }

    public Boolean checkOut() 
    {
        boolean continueLoop = true;
        showItems();
        double total = getTotal();
        double tax = total * 0.0825;
        double finalPrice = total + tax;
        System.out.println("===============================================================");
        System.out.printf("Invoice\nTotal: %.2f\nTax: %.2f\nFinal Price: %.2f\n", total, tax, finalPrice);
        System.out.println("Would you like to complete the purchase?(0 for no / 1 for yes)\n===============================================================");
        String input = scan.nextLine();
        int choice;
            try 
            {
                choice = Integer.parseInt(input);
                System.out.println("Input: " + choice);
                switch (choice) {
                    case 1 -> {
                        printReceiptToFile();
                        System.out.println("Purchase completed. Receipt printed to file.");
                        continueLoop = false;
                    }
                    case 0 -> {
                        clearRegister(cart);
                        System.out.println("Purchase cancelled.");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input.");
            }
        return continueLoop;
    }

   

    public void displayMenu(List<StoreItem> inventory) 
    {   
        int length = inventory.size();
        String[] menuOptions = {"Purchase item(s)", "Show Cash Register", "Clear Cash Register", "Show inventory", "Check out"};
        //Print out item descriptions from inventory
        System.out.println("===============================================================");
        System.out.println("Available items:");
        System.out.println("===============================================================");
        for(int i = 0; i < length; i++)
        {
            System.out.println(i + ". " + inventory.get(i).getItemDescription());
        }
        System.out.println("===============================================================");
        System.out.println("Menu Options (Enter Option Number):");
        System.out.println("===============================================================");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + length) + ". " + menuOptions[i]);
        }
        System.out.println("===============================================================");
        System.out.println("Enter any number under 0 to exit");
        System.out.println("===============================================================");
    }

    public void printReceiptToFile() 
    {
        // Print receipt to file Program6-output.txt
        try (PrintWriter writer = new PrintWriter("Program6-output.txt")) {
            writer.println("===============================================================");
            writer.println("Receipt");
            writer.println("===============================================================");
            writer.println("Cashier: " + selectRandomCashier());
            writer.println("Time: " + java.time.LocalTime.now());
            writer.println("Date: " + java.time.LocalDate.now());
            writer.println("===============================================================");
            cart.forEach(item -> writer.println(item.getItemDescription() + ": " + item.getPrice()));
            writer.println("===============================================================");
            writer.println("Total: " + getTotal());
            writer.println("Tax: " + getTotal() * 0.0825);
            writer.println("Final Price: " + getTotal() + getTotal() * 0.0825);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to the file");
        }
    }

    public String selectRandomCashier() 
    {
        return cashiers.get((int) (Math.random() * cashiers.size()));
    }
    
    public void setCashiers(List<String> cashier) 
    {
        cashiers.addAll(cashier);
    }
}