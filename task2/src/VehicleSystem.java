

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {

  protected String modelName;
  protected String brand;
  protected int productionYear;
  protected BigDecimal basePrice;
  protected boolean origin;

  public static final BigDecimal VAT_RATE = new BigDecimal("0.10");
  public static final int ORIGIN_DOMESTIC = 1;
  public static final int ORIGIN_IMPORTED = 2;

  public Vehicle() {
  }

  public abstract BigDecimal calculateImportTax();

  public abstract BigDecimal calculateSpecialTax();

  public BigDecimal totalPrice() {
    BigDecimal importTax = calculateImportTax();
    BigDecimal specialTax = calculateSpecialTax();
    BigDecimal totalBeforeVAT = basePrice.add(importTax).add(specialTax);
    BigDecimal vatAmount = totalBeforeVAT.multiply(VAT_RATE);
    return totalBeforeVAT.add(vatAmount);
  }

  public void inputInfo(Scanner sc) {
    System.out.print("Name Model: ");
    modelName = sc.nextLine();
    System.out.print("Brand: ");
    brand = sc.nextLine();
    productionYear = getValidInteger(sc, "Production Year");
    basePrice = getValidBigDecimal(sc, "Base Price");
    System.out.println("Origin:");
    System.out.println("  1. Domestic (Trong nuoc)");
    System.out.println("  2. Imported (Nhap khau)");
    System.out.printf("Origin (%d: Domestic, %d: Imported): ", ORIGIN_DOMESTIC, ORIGIN_IMPORTED);
    int originChoice;
    do {
      originChoice = getValidInteger(sc, "Choose Origin (1-2)");
      if (originChoice != 1 && originChoice != 2) {
        System.out.println("Invalid choice! Please enter 1 or 2.");
      }
    } while (originChoice != 1 && originChoice != 2);
    origin = (originChoice == ORIGIN_IMPORTED);
  }

  public void displayInfo() {
    System.out.println("Type vehicle: " + this.getClass().getSimpleName());
    System.out.println("Model: " + modelName + " | Brand: " + brand);
    System.out.println(
        "Year: " + productionYear + " | Origin: " + (origin ? "Imported" : "Domestic"));
    System.out.printf("Base Price: %,15.0f VND\n", basePrice);
    System.out.printf("Import Tax: %,15.0f VND\n", calculateImportTax());
    System.out.printf("Special Tax: %,15.0f VND\n", calculateSpecialTax());
    System.out.printf(">> Total Price: %,15.0f VND\n", totalPrice());
  }

  protected int getValidInteger(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return Integer.parseInt(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Error: Please enter a valid integer number!");
      }
    }
  }

  protected double getValidDouble(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return Double.parseDouble(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Error: Please enter a valid number!");
      }
    }
  }

  protected BigDecimal getValidBigDecimal(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return new BigDecimal(sc.nextLine().trim());
      } catch (Exception e) {
        System.out.println("Error: Please enter a valid amount!");
      }
    }
  }
}

class Car extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.50");
  private static final BigDecimal SPECIAL_TAX_LOW_RATE = new BigDecimal("0.50");
  private static final BigDecimal SPECIAL_TAX_HIGH_RATE = new BigDecimal("1.00");
  private static final double CAPACITY = 3000.0;
  private int seatCount;
  private String fuelType;
  private double cylinderCapacity;
  private String bodyType;

  public Car() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    seatCount = getValidInteger(sc, "Seat Count");
    System.out.println("Select Fuel Type:");
    System.out.println("  1. Gasoline (Xang)");
    System.out.println("  2. Diesel (Dau)");
    System.out.println("  3. Electric (Dien)");
    int fuelType;
    do {
      fuelType = getValidInteger(sc, "Choose Fuel (1-3)");
    } while (fuelType < 1 || fuelType > 3);
    cylinderCapacity = getValidDouble(sc, "Engine Capacity (cc)");

    System.out.print("Body Type (Ex: Sedan, SUV, Truck...): ");
    bodyType = sc.nextLine();
  }

  @Override
  public BigDecimal calculateImportTax() {
    if (!origin) {
      return BigDecimal.ZERO;
    }
    return basePrice.multiply(IMPORT_TAX_RATE);
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    BigDecimal baseWithImport = basePrice.add(calculateImportTax());
    return (cylinderCapacity < CAPACITY)
        ? baseWithImport.multiply(SPECIAL_TAX_LOW_RATE)
        : baseWithImport.multiply(SPECIAL_TAX_HIGH_RATE);
  }
}

class Motorcycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.30");
  private static final BigDecimal SPECIAL_TAX_RATE = new BigDecimal("0.20");
  private static final double CAPACITY = 150.0;

  private double cylinderCapacity;
  private String type;
  private int power;

  public Motorcycle() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    cylinderCapacity = getValidDouble(sc, "Engine Capacity (cc)");

    System.out.println("Select Motorcycle Type:");
    System.out.println("  1. Manual (Xe so)");
    System.out.println("  2. Automatic (Tay ga)");
    int typeChoice;
    do {
      typeChoice = getValidInteger(sc, "Choose Type (1-2)");
    } while (typeChoice != 1 && typeChoice != 2);

    type = (typeChoice == 1) ? "Manual" : "Automatic";
    power = getValidInteger(sc, "Power (HP/kW)");
  }

  @Override
  public BigDecimal calculateImportTax() {
    return origin ? basePrice.multiply(IMPORT_TAX_RATE) : BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    if (cylinderCapacity <= CAPACITY) {
      return BigDecimal.ZERO;
    } else {
      BigDecimal baseWithImport = basePrice.add(calculateImportTax());
      return baseWithImport.multiply(SPECIAL_TAX_RATE);
    }
  }
}

class Bicycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.10");
  private String type;
  private String material;

  public Bicycle() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    System.out.print("Bicycle Type (Ex: Mountain, Road, Hybrid): ");
    type = sc.nextLine();
    System.out.print("Frame Material (Ex: Carbon, Aluminum, Steel): ");
    material = sc.nextLine();
  }

  @Override
  public BigDecimal calculateImportTax() {
    return origin ? basePrice.multiply(IMPORT_TAX_RATE) : BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    return BigDecimal.ZERO;
  }
}

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
          int n = Integer.parseInt(sc.nextLine());
          for (int i = 0; i < n; i++) {
            System.out.println("Vehicle " + (i + 1) + ":");
            System.out.println("1. Car\n2. Moto\n3. Bike");
            int typeChoice;
            do {
              typeChoice = getValidInteger(sc, "Choose Type ");
            }
            while (typeChoice < 1 || typeChoice > 3);
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
          int m = getValidInteger(sc, "Number of Customers to register?");
          for (int i = 0; i < m; i++) {
            System.out.println("Customer " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Phone: ");
            String phone = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            BigDecimal balance = getValidBigDecimal(sc, "Balance");
            Customer customer = new Customer(name, address, phone, balance);
            dealership.addCustomerToStock(customer);
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