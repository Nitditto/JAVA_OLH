package task3.src.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import task3.src.model.Bicycle;
import task3.src.model.Car;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.LoyaltyLevel;
import task3.src.model.Motorcycle;
import task3.src.model.Vehicle;
import task3.src.utils.InputHelper;

public class VehicleView {

  private void inputCommonInfo(Vehicle v) {
    v.setModelName(InputHelper.getString("Model Name"));
    v.setBrand(InputHelper.getString("Brand"));
    v.setProductionYear(InputHelper.getValidInteger("Production Year"));
    v.setBasePrice(InputHelper.getValidBigDecimal("Base Price"));

    System.out
        .println("Origin:\n  " + Vehicle.ORIGIN_DOMESTIC + ". Domestic\n  " + Vehicle.ORIGIN_IMPORTED + ". Imported");
    int origin;
    do {
      origin = InputHelper.getValidInteger("Choose Origin");
    } while (origin != Vehicle.ORIGIN_DOMESTIC && origin != Vehicle.ORIGIN_IMPORTED);
    v.setOrigin(origin == Vehicle.ORIGIN_IMPORTED);
  }

  public void inputVehicleInfo(Vehicle v) {
    inputCommonInfo(v);

    if (v instanceof Car) {
      inputCarInfo((Car) v);
    } else if (v instanceof Motorcycle) {
      inputMotoInfo((Motorcycle) v);
    } else if (v instanceof Bicycle) {
      inputBikeInfo((Bicycle) v);
    }
  }

  private void inputCarInfo(Car c) {
    c.setSeatCount(InputHelper.getValidInteger("Seat Count"));
    System.out.println("Select Fuel Type:\n  1. Gasoline\n  2. Diesel\n  3. Electric");
    int fuel = InputHelper.getValidInteger("Choose Fuel (1-3)");
    c.setFuelType(fuel == 1 ? "Gasoline" : (fuel == 2 ? "Diesel" : "Electric"));
    c.setCylinderCapacity(InputHelper.getValidDouble("Engine Capacity (cc)"));
    c.setBodyType(InputHelper.getString("Body Type"));
  }

  private void inputMotoInfo(Motorcycle m) {
    m.setCylinderCapacity(InputHelper.getValidDouble("Engine Capacity (cc)"));
    System.out.println("Select Motorcycle Type:\n  1. Manual\n  2. Automatic");
    int type = InputHelper.getValidInteger("Choose Type (1-2)");
    m.setType(type == 1 ? "Manual" : "Automatic");
    m.setPower(InputHelper.getValidInteger("Power (HP/kW)"));
  }

  private void inputBikeInfo(Bicycle b) {
    b.setType(InputHelper.getString("Bike Type"));
    b.setMaterial(InputHelper.getString("Material"));
  }

  public void displayCustomers(ArrayList<Customer> customers) {
    if (customers.isEmpty()) {
      System.out.println("Not found customer!");
      return;
    }
    System.out.println("Select customer:");
    for (int i = 0; i < customers.size(); i++) {
      Customer c = customers.get(i);
      System.out.println((i + 1) + ". " + c.getName() + " \nPhone: " + c.getPhone());
    }
  }

  public void displayInventory(ArrayList<InventoryItem> inventory) {
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

  public void displayCustomerDetail(Customer c) {
    System.out.println("Name: " + c.getName());
    System.out.println("Address: " + c.getAddress());
    System.out.println("Phone: " + c.getPhone());
    System.out.printf("Balance: %,15.0f VND\n", c.getBalance());
    System.out.println("Loyalty Level: " + c.getLoyaltyLevel());
    System.out.println("Owned Vehicles: " + c.getPurchasedVehicles().size());

    if (!c.getPurchasedVehicles().isEmpty()) {
      System.out.print("History: ");
      for (int i = 0; i < c.getPurchasedVehicles().size(); i++) {
        Vehicle vehicle = c.getPurchasedVehicles().get(i);
        System.out.printf(" %d. %s %s (%d)\n",
            (i + 1), vehicle.getBrand(), vehicle.getModelName(), vehicle.getProductionYear());
      }
    }
  }

  public void displayVehicleDetail(Vehicle v) {
    System.out.println("Type vehicle: " + v.getClass().getSimpleName());
    System.out.println("Model: " + v.getModelName() + " | Brand: " + v.getBrand());
    System.out.println("Year: " + v.getProductionYear() + " | Origin: " + (v.isImported() ? "Imported" : "Domestic"));
    System.out.printf("Base Price: %,15.0f VND\n", v.getBasePrice());
    System.out.printf("Import Tax: %,15.0f VND\n", v.calculateImportTax());
    System.out.printf("Special Tax: %,15.0f VND\n", v.calculateSpecialTax());
    System.out.printf(">> Total Price: %,15.0f VND\n", v.totalPrice());
  }

  public void printTransaction(Customer c, Vehicle v, BigDecimal finalPrice) {
    BigDecimal basePrice = v.totalPrice();
    LoyaltyLevel level = c.getLoyaltyLevel();
    BigDecimal discountRate = level.getDiscountRate();
    BigDecimal discountAmount = basePrice.multiply(discountRate);
    BigDecimal sellPrice = basePrice.subtract(discountAmount);
    System.out.println("\nTransaction:");

    System.out.println("Vehicle " + v.getBrand() + " " + v.getModelName());
    System.out.printf("Price: %,.0f VND\n", basePrice);
    System.out.printf("Loyalty Discount (%s - %.0f%%): -%,.0f VND\n",
        level, discountRate.multiply(new BigDecimal(100)), discountAmount);
    System.out.printf("Total to pay: %,.0f VND\n", sellPrice);
  }

  public void printSuccess(Customer c) {
    System.out.println("Vehicle purchased successfully!");
    System.out.printf("New Balance: %,.0f VND\n", c.getBalance());
    System.out.println("Your Loyalty Level is now: " + c.getLoyaltyLevel());
  }

  public void printFail(Customer c, BigDecimal price) {
    System.out.println("Insufficient balance!");
    System.out.printf("You need %,.0f VND more\n", price.subtract(c.getBalance()));
  }

  public void displaySuggestions(ArrayList<InventoryItem> items) {
    if (items.isEmpty()) {
      System.out.println("No similar vehicles available");
    } else {
      System.out.println("Similar vehicles in stock:");
      for (InventoryItem item : items) {
        Vehicle v = item.getVehicle();
        System.out.printf("- %s %s (Price: %,.0f VND)\n", v.getBrand(), v.getModelName(), v.totalPrice());
      }
    }
  }

}
