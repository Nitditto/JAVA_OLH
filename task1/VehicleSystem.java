package task1;

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
    // int originChoice = Integer.parseInt(sc.nextLine());
    int originChoice;
    do {
      originChoice = getValidInteger(sc, "Choose Origin (1-2)");
      if (originChoice != 1 && originChoice != 2)
        System.out.println("Invalid choice! Please enter 1 or 2.");
    } while (originChoice != 1 && originChoice != 2);
    origin = (originChoice == ORIGIN_IMPORTED);
  }

  public void displayInfo() {
    System.out.println("Type vehicle: " + this.getClass().getSimpleName());
    System.out.println("Model: " + modelName + " | Brand: " + brand);
    System.out.println("Year: " + productionYear + " | Origin: " + (origin ? "Imported" : "Domestic"));
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
    if (!origin)
      return BigDecimal.ZERO;
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
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    System.out.print("Enter number of vehicles: ");
    int n = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < n; i++) {
      System.out.println("Input Vehicle");
      System.out.println("1. Car");
      System.out.println("2. Motorcycle");
      System.out.println("3. Bicycle");
      int choice;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = Integer.parseInt(sc.nextLine());
          if (choice >= 1 && choice <= 3)
            break;
          System.out.println("Please choose 1, 2, or 3.");
        } catch (NumberFormatException e) {
          System.out.println("Invalid choice!");
        }
      }
      Vehicle selectedVehicle;
      switch (choice) {
        case 1:
          selectedVehicle = new Car();
          break;
        case 2:
          selectedVehicle = new Motorcycle();
          break;
        case 3:
          selectedVehicle = new Bicycle();
          break;
        default:
          System.out.println("Invalid choice!");
          continue;
      }
      selectedVehicle.inputInfo(sc);
      vehicles.add(selectedVehicle);
    }
    System.out.println();
    for (Vehicle vehicle : vehicles) {
      vehicle.displayInfo();
    }
  }
}