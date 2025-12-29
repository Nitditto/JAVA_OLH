public class InventoryItem {

  private Vehicle vehicle;
  private int quantity;

  public InventoryItem(Vehicle vehicle, int quantity) {
    this.vehicle = vehicle;
    this.quantity = quantity;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public int getQuantity() {
    return quantity;
  }

}
