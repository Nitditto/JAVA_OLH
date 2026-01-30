package task3.src.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import task3.src.constant.MessageConstant;
import task3.src.exception.InsufficientBalanceException;
import task3.src.exception.OutOfStockException;
import task3.src.exception.StoreException;
import task3.src.model.Bicycle;
import task3.src.model.Car;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.Motorcycle;
import task3.src.model.Vehicle;
import task3.src.service.DealershipService;
import task3.src.utils.InputHelper;
import task3.src.view.VehicleView;

public class MainController {

  private DealershipService service;
  private VehicleView view;

  public MainController() {
    this.service = new DealershipService();
    this.view = new VehicleView();
  }

  public void run() {

    System.out.println("Welcome to the Vehicle Management System");

    while (true) {
      showMenu();
      int choice = InputHelper.getValidInteger("Choose Your Choice");

      try {
        switch (choice) {
          case 1:
            importVehicle();
            break;
          case 2:
            addCustomer();
            break;
          case 3:
            view.displayInventory(service.getInventory());
            break;
          case 4:
            view.displayCustomers(service.getCustomers());
            break;
          case 5:
            sellVehicle();
            break;
          case 0:
            System.out.println("Exit");
            return;
          default:
            System.out.println("Invalid choice.");
        }
      } catch (StoreException e) {

        System.out.println("Error: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
      }
    }
  }

  private void showMenu() {
    System.out.println("\nMENU");
    System.out.println("1. Add Vehicle to Stock");
    System.out.println("2. Add New Customer");
    System.out.println("3. Show Inventory");
    System.out.println("4. Show Customers");
    System.out.println("5. Sell vehicles");
    System.out.println("0. Exit");
  }

  private void importVehicle() {
    int count = InputHelper.getValidInteger("How many vehicles to add?");
    for (int i = 0; i < count; i++) {
      System.out.println("Vehicle " + (i + 1) + ":");
      System.out.println("1. Car\n2. Moto\n3. Bike");

      int type;
      do {
        type = InputHelper.getValidInteger("Choose Type");
      } while (type < 1 || type > 3);

      Vehicle v;
      switch (type) {
        case 1:
          v = new Car();
          break;
        case 2:
          v = new Motorcycle();
          break;
        case 3:
          v = new Bicycle();
          break;
        default:

          continue;
      }

      view.inputVehicleInfo(v);
      int qty = InputHelper.getValidInteger("Enter Stock Quantity");

      service.addVehicleToStock(v, qty);
    }
    System.out.println(MessageConstant.MSG_IMPORT_SUCCESS);
  }

  private void addCustomer() {
    int count = InputHelper.getValidInteger("Number of Customers?");
    for (int i = 0; i < count; i++) {
      System.out.println("Customer " + (i + 1) + ":");
      Customer c = new Customer(
          InputHelper.getString("Name"),
          InputHelper.getString("Address"),
          InputHelper.getString("Phone"),
          InputHelper.getValidBigDecimal("Balance"));

      service.registerCustomer(c);
    }
    System.out.println(MessageConstant.MSG_CUSTOMER_ADDED);
  }

  private void sellVehicle() {

    if (service.getCustomers().isEmpty()) {
      System.out.println(MessageConstant.ERR_CUSTOMER_NOT_FOUND);
      return;
    }
    if (service.getInventory().isEmpty()) {
      System.out.println(MessageConstant.ERR_INVENTORY_EMPTY);
      return;
    }

    view.displayCustomers(service.getCustomers());
    int cusIdx = InputHelper.getValidInteger("Choose customer number") - 1;
    Customer customer = service.getCustomerByIndex(cusIdx);

    view.displayInventory(service.getInventory());
    int vehIdx = InputHelper.getValidInteger("Choose vehicle number to buy") - 1;
    InventoryItem item = service.getItemByIndex(vehIdx);
    Vehicle vehicle = item.getVehicle();

    System.out.println("\nProcessing purchase for: " + customer.getName());

    BigDecimal finalPrice = service.calculateFinalPrice(vehicle, customer);

    view.printTransaction(customer, vehicle, finalPrice);

    try {
      service.processTransaction(customer, item, finalPrice);

      view.printSuccess(customer);

    } catch (OutOfStockException | InsufficientBalanceException e) {

      System.out.println(e.getMessage());

      ArrayList<InventoryItem> suggestions = service.getSuggestions(vehicle.getClass());
      view.displaySuggestions(suggestions);
    }
  }
}