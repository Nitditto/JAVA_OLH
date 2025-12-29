import java.util.ArrayList;

public class Dealership {

  private String name;
  private ArrayList<InventoryItem> inventory = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public Dealership(String name) {
    this.name = name;
  }

  public void addVehicleToStock(Vehicle vehicle, int quantity) {
    this.inventory.add(new InventoryItem(vehicle, quantity));
  }

  public void addCustomerToStock(Customer customer) {
    this.customers.add(customer);
  }

  public void displayInventory() {
    if(inventory.isEmpty()) {
      System.out.println("There is no inventory items in this dealership!");
      return;
    }
    for (int i = 0; i < inventory.size(); i++) {
      inventory.get(i).getVehicle().displayInfo();
      System.out.println("Stock Quantity: " + inventory.get(i).getQuantity());
    }
  }
  public void displayCustomers() {
    if(customers.isEmpty()) {
      System.out.println("No customer found!");
      return;
    }
    for (int i = 0; i < customers.size(); i++) {
      customers.get(i).displayCustomerInformation();
    }
  }
}
