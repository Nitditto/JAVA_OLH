package task1;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {
  protected String modelName;
  protected String brand;
  protected int productionYear;
  protected double basePrice;
  protected boolean origin;

  public Vehicle() {
  }

  public Vehicle(String modelName, String brand, int productionYear, double basePrice, boolean origin) {
    this.modelName = modelName;
    this.brand = brand;
    this.productionYear = productionYear;
    this.basePrice = basePrice;
    this.origin = origin;
  }

  public abstract double calculateImportTax();

  public abstract double calculateSpecialTax();

  public double totalPrice() {
    double importTax = calculateImportTax();
    double specialTax = calculateSpecialTax();
    double totalBeforeVAT = basePrice + importTax + specialTax;
    return totalBeforeVAT + totalBeforeVAT * 0.10;
  }

  public void inputInfo(Scanner sc) {
    System.out.print("Name Model: ");
    modelName = sc.nextLine();
    System.out.print("Brand: ");
    brand = sc.nextLine();
    System.out.print("Year: ");
    productionYear = Integer.parseInt(sc.nextLine());
    System.out.print("Base Price: ");
    basePrice = Double.parseDouble(sc.nextLine());

    System.out.print("Origin (1: Domestic, 2: Imported): ");
    int originChoice = Integer.parseInt(sc.nextLine());
    if (originChoice == 2) {
      origin = true;
    } else {
      origin = false;
    }
  }

  public void displayInfo() {
    System.out.println("Type vehicle: " + this.getClass().getSimpleName());
    System.out.println("Model: " + modelName + " | Brand: " + brand);
    System.out.println("Year: " + productionYear + " | Origin: " + (origin ? "Export" : "Import"));
    System.out.printf("Base Price: %,.0f VND\n", basePrice);
    System.out.printf("Import Tax: %,.0f VND\n", calculateImportTax());
    System.out.printf("Special Tax: %,.0f VND\n", calculateSpecialTax());
    System.out.printf(">> Total Price: %,.0f VND\n", totalPrice());
  }
}

class Car extends Vehicle {
  private int seatCount;
  private String fuelType;
  private double engineCC;
  private String bodyType;

  public Car() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    System.out.print("Seat Count: ");
    seatCount = Integer.parseInt(sc.nextLine());
    System.out.print("Fuel Type: ");
    fuelType = sc.nextLine();
    System.out.print("Engine (cc): ");
    engineCC = Double.parseDouble(sc.nextLine());
    System.out.print("Body Type: ");
    bodyType = sc.nextLine();
  }

  // public Car(String modelName, String brand, int productionYear, double
  // basePrice, boolean origin, int seatCount,
  // String fuelType,
  // int engineCC, String bodyType) {
  // super(modelName, brand, productionYear, basePrice, origin);
  // this.seatCount = seatCount;
  // this.fuelType = fuelType;
  // this.engineCC = engineCC;
  // this.bodyType = bodyType;
  // }

  @Override
  public double calculateImportTax() {
    if (!origin)
      return 0;
    return basePrice * 0.50;
  }

  @Override
  public double calculateSpecialTax() {
    double baseWithImport = basePrice + calculateImportTax();
    return (engineCC < 3000) ? baseWithImport * 0.50 : baseWithImport * 1.00;
  }
}

class Motorcycle extends Vehicle {
  private int engineCC;
  private String type;
  private int power;

  public Motorcycle() {
  }
  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    System.out.print("Engine (cc): ");
    engineCC = Integer.parseInt(sc.nextLine());
    System.out.print("Type (Manual/Automatic): ");
    type = sc.nextLine();
    System.out.print("Power: ");
    power = Integer.parseInt(sc.nextLine());
  }

  @Override
  public double calculateImportTax() {
    return origin ? basePrice * 0.30 : 0;
  }

  @Override
  public double calculateSpecialTax() {
    if (engineCC <= 150) {
      return 0;
    } else {
      double baseWithImport = basePrice + calculateImportTax();
      return baseWithImport * 0.20;
    }
  }
}

class Bicycle extends Vehicle {
  private String type;
  private String material;

  public Bicycle() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    System.out.print("Bicycle type: ");
    type = sc.nextLine();
    System.out.println("Material: ");
    material = sc.nextLine();
  }

  @Override
  public double calculateImportTax() {
    return origin ? basePrice * 0.1 : 0;
  }

  @Override
  public double calculateSpecialTax() {
    return 0;
  }
}

public class VehicleSystem {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    System.out.print("Enter number of vehicles: ");
    int n = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < n; i++) {
      System.out.println("1. Car");
      System.out.println("2. Motorcycle");
      System.out.println("3. Bicycle");
      int choice = Integer.parseInt(sc.nextLine());
      Vehicle v;
      switch (choice) {
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
          System.out.println("Invalid choice!");
          continue;
      }
      v.inputInfo(sc);
      vehicles.add(v);
    }
    for (Vehicle v : vehicles) {
      v.displayInfo();
    }
  }
}