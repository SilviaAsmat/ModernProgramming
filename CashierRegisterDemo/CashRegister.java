import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashRegister 
{
    private final List<StoreItem> cart;
    private final List<String> cashiers;

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
                                    .filter(i -> i.getItemDescription().equals(trimmedDescription))
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
        cart.forEach(item -> System.out.println(item.getItemDescription() + ": " + item.getPrice()));
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
        //inventory.addAll(cart);
        cart.clear();
    }

    public void showInventory(List<StoreItem> inventory) 
    {
        inventory.forEach(item -> System.out.println(item.getItemDescription() + ": " + item.getUnitsInInventory()));
    }

    public void checkOut() 
    {
        double total = getTotal();
        double tax = total * 0.0825;
        double finalPrice = total + total * 0.0825;
        System.out.println("===============================================================");
        System.out.println("Invoice\nTotal: "+ total +"\nTax: " + tax + "\nFinal Price: " + finalPrice);
        System.out.println("Would you like to complete the purchase?(0 for no / 1 for yes\n\"===============================================================\")");
        Scanner scan = new Scanner(System.in);
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
                        cart.clear();
                    }
                    case 0 -> {
                        clearRegister(cart);
                        System.out.println("Purchase cancelled.");
                    }
                    default -> System.out.println("Invalid choice.");
                }
                scan.close();
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input.");
            }
        // int choice = Integer.parseInt(input);
        // if (choice == 1) {
        //     printReceiptToFile();
        //     System.out.println("Purchase completed. Receipt printed to file.");
        //     cart.clear();
        //     scan.close();
        // } else if (choice == 0) {
        //     clearRegister(cart);
        //     System.out.println("Purchase cancelled.");
        // } else {
        //     System.out.println("Invalid choice.");
        // }
    }

    public void displayMenu(List<StoreItem> inventory) 
    {
        //Print out item descriptions from inventory
        System.out.println("===============================================================");
        System.out.println("Available items:");
        System.out.println("===============================================================");
        inventory.forEach(item -> System.out.println(item.getItemDescription()));
        System.out.println("===============================================================");
        System.out.println("1 - Purchase an Item:");
        System.out.println("2 - Show Cash Register");
        System.out.println("3 - Clear Cash Register");
        System.out.println("4 - Show Inventory");
        System.out.println("5 - Check Out");
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
    
    public void addCashier(String cashier) 
    {
        cashiers.add(cashier);
    }
}