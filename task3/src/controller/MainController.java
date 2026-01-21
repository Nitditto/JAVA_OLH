package task3.src.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import task3.src.constant.MessageConstant;
import task3.src.model.Bicycle;
import task3.src.model.Car;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.Motorcycle;
import task3.src.model.Vehicle;
import task3.src.repository.DealershipRepo;
import task3.src.service.SaleService;
import task3.src.utils.InputHelper;
import task3.src.view.VehicleView;

public class MainController {

  private DealershipRepo repo;
  private SaleService service;
  private VehicleView view;

  public MainController() {
    this.view = new VehicleView();
  }

  public static void showMenu() {
    System.out.println("MENU");
    System.out.println("1. Add Vehicle to Stock ");
    System.out.println("2. Add New Customer ");
    System.out.println("3. Show Inventory ");
    System.out.println("4. Show Customers ");
    System.out.println("5. Sell vehicles ");
    System.out.println("0. Exit");
  }

  public void run() {
    String name = InputHelper.getString("Dealership name");
    this.repo = new DealershipRepo(name);
    this.service = new SaleService(repo);

    while (true) {
      showMenu();

      int choice = InputHelper.getValidInteger("Choose Your Choice");
      switch (choice) {
        case 1:
          importVehicle();
          break;
        case 2:
          addCustomer();
          break;
        case 3:
          view.displayInventory(repo.getInventory());
          break;
        case 4:
          view.displayCustomers(repo.getCustomers());
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
    }
  }

  private void importVehicle() {
    System.out.println("How many vehicles do you want to add?");
    int count = InputHelper.getValidInteger("");
    for (int i = 0; i < count; i++) {
      System.out.println("Vehicle " + (i + 1) + ":");
      System.out.println("1. Car\n2. Moto\n3. Bike");
      int typeChoice;
      do {
        typeChoice = InputHelper.getValidInteger("Choose Type ");
      } while (typeChoice < 1 || typeChoice > 3);

      Vehicle vehicle;
      if (typeChoice == 1) {
        vehicle = new Car();
      } else if (typeChoice == 2) {
        vehicle = new Motorcycle();
      } else {
        vehicle = new Bicycle();
      }
      view.inputVehicleInfo(vehicle);
      int qty = InputHelper.getValidInteger("Enter Stock Quantity");

      repo.addVehicleToStock(new InventoryItem(vehicle, qty));
    }
    System.out.println(MessageConstant.MSG_IMPORT_SUCCESS);
  }

  private void addCustomer() {
    int count = InputHelper.getValidInteger("Number of Customers to register?");
    for (int i = 0; i < count; i++) {
      System.out.println("Customer " + (i + 1) + ":");
      repo.addCustomer(new Customer(
          InputHelper.getString("Name"),
          InputHelper.getString("Address"),
          InputHelper.getString("Phone"),
          InputHelper.getValidBigDecimal("Balance")));
    }
    System.out.println(MessageConstant.MSG_CUSTOMER_ADDED);
  }

  private void sellVehicle() {
    if (repo.getCustomers().isEmpty()) {
      System.out.println("Not found customer!");
      return;
    }
    view.displayCustomers(repo.getCustomers());
    int customerIdx = InputHelper.getValidInteger("Choose customer number") - 1;

    if (customerIdx < 0 || customerIdx >= repo.getCustomers().size()) {
      System.out.println("Invalid selection!");
      return;
    }
    Customer customer = repo.getCustomers().get(customerIdx);

    if (repo.getInventory().isEmpty()) {
      System.out.println("Inventory is empty!");
      return;
    }

    view.displayInventory(repo.getInventory());
    int vehicleIdx = InputHelper.getValidInteger("Choose vehicle number to buy") - 1;

    if (vehicleIdx < 0 || vehicleIdx >= repo.getInventory().size()) {
      System.out.println("Invalid vehicle selection!");
      return;
    }

    InventoryItem item = repo.getInventory().get(vehicleIdx);
    Vehicle vehicle = item.getVehicle();

    if (item.getQuantity() <= 0) {
      System.out.println("Vehicle not found or out of stock!");
      ArrayList<InventoryItem> suggestions = service.getSuggestions(vehicle.getClass());
      view.displaySuggestions(suggestions);
      return;
    }

    System.out.println("\nProcessing purchase for: " + customer.getName());

    BigDecimal finalPrice = service.calculateFinalPrice(vehicle, customer);
    view.printTransaction(customer, vehicle, finalPrice);

    boolean success = service.processTransaction(customer, item, finalPrice);

    if (success) {
      view.printSuccess(customer);
    } else {
      view.printFail(customer, finalPrice);

      ArrayList<InventoryItem> suggestions = service.getSuggestions(vehicle.getClass());
      view.displaySuggestions(suggestions);
    }
  }
}