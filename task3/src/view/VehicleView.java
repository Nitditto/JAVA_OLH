package task3.src.view;

import java.math.BigDecimal;
import java.util.List;
import task3.src.model.Bicycle;
import task3.src.model.Car;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.LoyaltyLevel;
import task3.src.model.Motorcycle;
import task3.src.model.Vehicle;
import task3.src.utils.InputHelper;

public class VehicleView {

  private void inputCommonInfo(Vehicle vehicle) {
    vehicle.setModelName(InputHelper.getString("Model Name"));
    vehicle.setBrand(InputHelper.getString("Brand"));
    vehicle.setProductionYear(InputHelper.getValidInteger("Production Year"));
    vehicle.setBasePrice(InputHelper.getValidBigDecimal("Base Price"));

    System.out
        .println("Origin:\n  " + Vehicle.ORIGIN_DOMESTIC + ". Domestic\n  " + Vehicle.ORIGIN_IMPORTED + ". Imported");
    int origin;
    do {
      origin = InputHelper.getValidInteger("Choose Origin");
    } while (origin != Vehicle.ORIGIN_DOMESTIC && origin != Vehicle.ORIGIN_IMPORTED);
    vehicle.setOrigin(origin == Vehicle.ORIGIN_IMPORTED);
  }

  public void inputVehicleInfo(Vehicle vehicle) {
    inputCommonInfo(vehicle);

    if (vehicle instanceof Car) {
      inputCarInfo((Car) vehicle);
    } else if (vehicle instanceof Motorcycle) {
      inputMotoInfo((Motorcycle) vehicle);
    } else if (vehicle instanceof Bicycle) {
      inputBikeInfo((Bicycle) vehicle);
    }
  }

  public void showMenu() {
    System.out.println("\nMENU");
    System.out.println("1. Add Vehicle to Stock");
    System.out.println("2. Add New Customer");
    System.out.println("3. Show Inventory");
    System.out.println("4. Show Customers");
    System.out.println("5. Sell vehicles");
    System.out.println("0. Exit");
  }

  public void displayError(String message) {
    System.out.println("Error: " + message);
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

  private void inputCarInfo(Car car) {
    car.setSeatCount(InputHelper.getValidInteger("Seat Count"));
    System.out.println("Select Fuel Type:\n  1. Gasoline\n  2. Diesel\n  3. Electric");
    int fuel = InputHelper.getValidInteger("Choose Fuel (1-3)");
    car.setFuelType(fuel == 1 ? "Gasoline" : (fuel == 2 ? "Diesel" : "Electric"));
    car.setCylinderCapacity(InputHelper.getValidDouble("Engine Capacity (cc)"));
    car.setBodyType(InputHelper.getString("Body Type"));
  }

  private void inputMotoInfo(Motorcycle motorcycle) {
    motorcycle.setCylinderCapacity(InputHelper.getValidDouble("Engine Capacity (cc)"));
    System.out.println("Select Motorcycle Type:\n  1. Manual\n  2. Automatic");
    int type = InputHelper.getValidInteger("Choose Type (1-2)");
    motorcycle.setType(type == 1 ? "Manual" : "Automatic");
    motorcycle.setPower(InputHelper.getValidInteger("Power (HP/kW)"));
  }

  private void inputBikeInfo(Bicycle bicycle) {
    bicycle.setType(InputHelper.getString("Bike Type"));
    bicycle.setMaterial(InputHelper.getString("Material"));
  }

  public void displayCustomers(List<Customer> customers) {
    if (customers.isEmpty()) {
      System.out.println("Not found customer!");
      return;
    }
    System.out.println("Select customer:");
    for (int i = 0; i < customers.size(); i++) {
      Customer customer = customers.get(i);
      System.out.println((i + 1) + ". " + customer.getName() + " \nPhone: " + customer.getPhone());
    }
  }

  public void displayInventory(List<InventoryItem> inventory) {
    if (inventory.isEmpty()) {
      System.out.println("Inventory is empty!");
      return;
    }
    System.out.println("\nSelect vehicle");
    for (InventoryItem item : inventory) {
      displayVehicleDetail(item.getVehicle());
      System.out.println("Stock Quantity: " + item.getQuantity());
    }
  }

  public void displayCustomerDetail(Customer customer) {
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Phone: " + customer.getPhone());
    System.out.printf("Balance: %,15.0f VND\n", customer.getBalance());
    System.out.println("Loyalty Level: " + customer.getLoyaltyLevel());
    System.out.println("Owned Vehicles: " + customer.getPurchasedVehicles().size());

    if (!customer.getPurchasedVehicles().isEmpty()) {
      System.out.print("History: ");
      for (int i = 0; i < customer.getPurchasedVehicles().size(); i++) {
        Vehicle vehicle = customer.getPurchasedVehicles().get(i);
        System.out.printf(" %d. %s %s (%d)\n",
            (i + 1), vehicle.getBrand(), vehicle.getModelName(), vehicle.getProductionYear());
      }
    }
  }

  public void displayVehicleDetail(Vehicle vehicle) {
    System.out.println("Type vehicle: " + vehicle.getClass().getSimpleName());
    System.out.println("Model: " + vehicle.getModelName() + " | Brand: " + vehicle.getBrand());
    System.out
        .println("Year: " + vehicle.getProductionYear() + " | Origin: " + (v.isImported() ? "Imported" : "Domestic"));
    System.out.printf("Base Price: %,15.0f VND\n", vehicle.getBasePrice());
    System.out.printf("Import Tax: %,15.0f VND\n", vehicle.calculateImportTax());
    System.out.printf("Special Tax: %,15.0f VND\n", vehicle.calculateSpecialTax());
    System.out.printf(">> Total Price: %,15.0f VND\n", vehicle.totalPrice());
  }

  public void printTransaction(Customer customer, Vehicle vehicle, BigDecimal finalPrice) {
    BigDecimal basePrice = vehicle.totalPrice();
    LoyaltyLevel level = customer.getLoyaltyLevel();
    BigDecimal discountRate = level.getDiscountRate();
    BigDecimal discountAmount = basePrice.multiply(discountRate);
    BigDecimal sellPrice = basePrice.subtract(discountAmount);
    System.out.println("\nTransaction:");

    System.out.println("Vehicle " + vehicle.getBrand() + " " + vehicle.getModelName());
    System.out.printf("Price: %,.0f VND\n", basePrice);
    System.out.printf("Loyalty Discount (%s - %.0f%%): -%,.0f VND\n",
        level, discountRate.multiply(new BigDecimal(100)), discountAmount);
    System.out.printf("Total to pay: %,.0f VND\n", sellPrice);
  }

  public void printSuccess(Customer customer) {
    System.out.println("Vehicle purchased successfully!");
    System.out.printf("New Balance: %,.0f VND\n", customer.getBalance());
    System.out.println("Your Loyalty Level is now: " + customer.getLoyaltyLevel());
  }

  public void printFail(Customer customer, BigDecimal price) {
    System.out.println("Insufficient balance!");
    System.out.printf("You need %,.0f VND more\n", price.subtract(customer.getBalance()));
  }

  public void displaySuggestions(List<InventoryItem> items) {
    if (items.isEmpty()) {
      System.out.println("No similar vehicles available");
    } else {
      System.out.println("Similar vehicles in stock:");
      for (InventoryItem item : items) {
        Vehicle vehicle = item.getVehicle();
        System.out.printf("- %s %s (Price: %,.0f VND)\n", vehicle.getBrand(), vehicle.getModelName(),
            vehicle.totalPrice());
      }
    }
  }

}
