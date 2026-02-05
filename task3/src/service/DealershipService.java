package task3.src.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import task3.src.exception.InsufficientBalanceException;
import task3.src.exception.OutOfStockException;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.Vehicle;

public class DealershipService {

    private List<InventoryItem> inventory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addVehicleToStock(Vehicle vehicle, int quantity) {
        inventory.add(new InventoryItem(vehicle, quantity));
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public InventoryItem getItemByIndex(int index) {
        if (index < 0 || index >= inventory.size()) {
            throw new RuntimeException("Invalid vehicle selection!");
        }
        return inventory.get(index);
    }

    public Customer getCustomerByIndex(int index) {
        if (index < 0 || index >= customers.size()) {
            throw new RuntimeException("Invalid customer selection!");
        }
        return customers.get(index);
    }

    public BigDecimal calculateFinalPrice(Vehicle vehicle, Customer customer) {
        BigDecimal totalPriceBeforeDiscount = vehicle.totalPrice();
        BigDecimal discountRate = customer.getLoyaltyLevel().getDiscountRate();
        return totalPriceBeforeDiscount.subtract(totalPriceBeforeDiscount.multiply(discountRate));
    }

    public List<InventoryItem> getSuggestions(Class<? extends Vehicle> type) {
        List<InventoryItem> suggestions = new ArrayList<>();
        for (InventoryItem item : inventory) {
            if (type.isInstance(item.getVehicle()) && item.getQuantity() > 0) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }

    public void processTransaction(Customer customer, InventoryItem item, BigDecimal finalPrice) {
        OutOfStockException.checkOutOfStock(item.getQuantity());
        InsufficientBalanceException.checkNotEnoughBalance(customer.getBalance(), finalPrice);

        customer.setBalance(customer.getBalance().subtract(finalPrice));
        item.decreaseQuantity();
        customer.addPurchase(item.getVehicle());
    }
}