
import java.math.BigDecimal;
import java.util.Scanner;

public class VehicleSystem {

  public static int getValidInteger(Scanner sc, String prompt) {
    while (true) {
      try {
        System.out.print(prompt + ": ");
        return Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid integer.");
      }
    }
  }

  public static BigDecimal getValidBigDecimal(Scanner sc, String prompt) {
    while (true) {
      try {
        System.out.println(prompt + ": ");
        return new BigDecimal(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid amount.");
      }
    }
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

  private static void addVehicleToStock(Scanner sc, Dealership dealership) {
    System.out.println("How many vehicles do you want to add?");
    int vehicleCount = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < vehicleCount; i++) {
      System.out.println("Vehicle " + (i + 1) + ":");
      System.out.println("1. Car\n2. Moto\n3. Bike");
      
      int typeChoice;
      do {
        typeChoice = getValidInteger(sc, "Choose Type ");
      } while (typeChoice < 1 || typeChoice > 3);
      Vehicle vehicle;
      if (typeChoice == 1) {
        vehicle = new Car();
      } else if (typeChoice == 2) {
        vehicle = new Motorcycle();
      } else {
        vehicle = new Bicycle();
      }
      
      vehicle.inputInfo(sc);
      int quantity = getValidInteger(sc, "Enter Stock Quantity");
      dealership.addVehicleToStock(vehicle, quantity);
    }
    System.out.println("Import success!");
  }

  private static void addCustomer(Scanner sc, Dealership dealership) {
    int customerCount = getValidInteger(sc, "Number of Customers to register?");
    for (int i = 0; i < customerCount; i++) {
      System.out.println("Customer " + (i + 1) + ":");
      System.out.print("Name: ");
      String name = sc.nextLine();
      System.out.print("Phone: ");
      String phone = sc.nextLine();
      System.out.print("Address: ");
      String address = sc.nextLine();
      BigDecimal balance = getValidBigDecimal(sc, "Balance");

      Customer customer = new Customer(name, address, phone, balance);
      dealership.addCustomer(customer);
    }
    System.out.println("Customer added successfully.");
  }

  private static void sellVehicle(Scanner sc, Dealership dealership) {
    if (dealership.getCustomers().isEmpty()) {
      System.out.println("Not found customer!");
      return;
    }
    System.out.println("Select customer:");
    for (int i = 0; i < dealership.getCustomers().size(); i++) {
      Customer customer = dealership.getCustomers().get(i);
      System.out.println((i + 1) + ". " + customer.getName() + " " + "\n" + "Phone: " + customer.getPhone());
    }
    int customerChoice = getValidInteger(sc, "Choose customer number");
    int customerIndex = customerChoice - 1;
    if (customerIndex < 0 || customerIndex >= dealership.getCustomers().size()) {
      System.out.println("Invalid selection!");
      return;
    }
    Customer selectedCustomer = dealership.getCustomers().get(customerIndex);

    if (dealership.getInventory().isEmpty()) {
      System.out.println("Inventory is empty!");
      return;
    }
    System.out.println("\nSelect vehicle");
    for (int i = 0; i < dealership.getInventory().size(); i++) {
      InventoryItem item = dealership.getInventory().get(i);
      Vehicle vehicle = item.getVehicle();
      System.out.printf("%d. %s %s %s\nPrice: %,.0f VND\nStock: %d\n",
          (i + 1),
          vehicle.getClass().getSimpleName(),
          vehicle.getBrand(),
          vehicle.getModelName(),
          vehicle.totalPrice(),
          item.getQuantity());
    }
    int vehicleChoice = getValidInteger(sc, "Choose vehicle number to buy");
    int vehicleIndex = vehicleChoice - 1;

    if (vehicleIndex < 0 || vehicleIndex >= dealership.getInventory().size()) {
      System.out.println("Invalid vehicle selection!");
      return;
    }
    InventoryItem selectedItem = dealership.getInventory().get(vehicleIndex);
    Vehicle selectedVehicle = selectedItem.getVehicle();

    System.out.println("\nProcessing purchase for: " + selectedCustomer.getName());
    dealership.sellVehicle(selectedCustomer, selectedVehicle.getModelName(), selectedVehicle.getBrand());
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Dealership name: ");
    String dealershipName = sc.nextLine();
    Dealership dealership = new Dealership(dealershipName);

    while (true) {
      showMenu();

      int choice = getValidInteger(sc, "Choose Your Choice");
      switch (choice) {
        case 1:
          addVehicleToStock(sc, dealership);
          break;

        case 2:
          addCustomer(sc, dealership);
          break;

        case 3:
          dealership.displayInventory();
          break;
        case 4:
          dealership.displayCustomers();
          break;

        case 5:
          sellVehicle(sc, dealership);
          break;
        case 0:
          System.out.println("Exit");
          sc.close();
          return;

        default:
          System.out.println("Invalid choice.");
      }
    }
  }
}