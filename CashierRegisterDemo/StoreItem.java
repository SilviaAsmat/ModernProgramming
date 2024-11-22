public class StoreItem {
    private final int itemNumber;
    private final String itemDescription;
    private int unitsInInventory;
    private final double price;

    // Constructor
    public StoreItem(int itemNumber, String itemDescription, int unitsInInventory, double price) {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.unitsInInventory = unitsInInventory;
        this.price = price;
    }

    // Getters and Setters
    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getUnitsInInventory() {
        return unitsInInventory;
    }

    public double getPrice() {
        return price;
    }

    public void setUnitsInInventory(int unitsInInventory) {
        this.unitsInInventory = unitsInInventory;
    }
}