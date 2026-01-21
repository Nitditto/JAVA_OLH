package task3.src.repository;

import java.util.ArrayList;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;

public class DealershipRepo {
  private String name;
  private ArrayList<InventoryItem> inventory = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public DealershipRepo(String name) {
    this.name = name;
  }

  public void addVehicleToStock(InventoryItem item) {
    inventory.add(item);
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  public ArrayList<Customer> getCustomers() {
    return this.customers;
  }

  public ArrayList<InventoryItem> getInventory() {
    return this.inventory;
  }
}