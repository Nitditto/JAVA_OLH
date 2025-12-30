
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Dealership name: ");
    String dealershipName = sc.nextLine();
    Dealership dealership = new Dealership(dealershipName);

    while (true) {
      System.out.println("MENU");
      System.out.println("1. Add Vehicle to Stock ");
      System.out.println("2. Add New Customer ");
      System.out.println("3. Show Inventory ");
      System.out.println("4. Show Customers ");
      System.out.println("0. Exit");

      int choice = getValidInteger(sc, "Choose Your Choice");
      switch (choice) {
        case 1:
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
          break;

        case 2:
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
          break;

        case 3:
          dealership.displayInventory();
          break;
        case 4:
          dealership.displayCustomers();
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