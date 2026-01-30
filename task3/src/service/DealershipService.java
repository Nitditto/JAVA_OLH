package task3.src.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import task3.src.constant.MessageConstant;
import task3.src.exception.InsufficientBalanceException;
import task3.src.exception.OutOfStockException;
import task3.src.exception.StoreException;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.Vehicle;

public class DealershipService {

    private ArrayList<InventoryItem> inventory = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public void addVehicleToStock(Vehicle vehicle, int quantity) {
        inventory.add(new InventoryItem(vehicle, quantity));
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public InventoryItem getItemByIndex(int index) {
        if (index < 0 || index >= inventory.size()) {
            throw new StoreException("Invalid vehicle selection!");
        }
        return inventory.get(index);
    }

    public Customer getCustomerByIndex(int index) {
        if (index < 0 || index >= customers.size()) {
            throw new StoreException("Invalid customer selection!");
        }
        return customers.get(index);
    }

    public BigDecimal calculateFinalPrice(Vehicle v, Customer c) {
        BigDecimal base = v.totalPrice();
        BigDecimal discountRate = c.getLoyaltyLevel().getDiscountRate();
        return base.subtract(base.multiply(discountRate));
    }

    public ArrayList<InventoryItem> getSuggestions(Class<? extends Vehicle> type) {
        ArrayList<InventoryItem> suggestions = new ArrayList<>();
        for (InventoryItem item : inventory) {
            if (type.isInstance(item.getVehicle()) && item.getQuantity() > 0) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }

    public void processTransaction(Customer c, InventoryItem item, BigDecimal finalPrice) {
        if (item.getQuantity() <= 0) {
            throw new OutOfStockException(MessageConstant.ERR_OUT_OF_STOCK);
        }

        if (c.getBalance().compareTo(finalPrice) < 0) {
            BigDecimal missing = finalPrice.subtract(c.getBalance());
            throw new InsufficientBalanceException(
                    MessageConstant.ERR_NOT_ENOUGH_BALANCE + " Missing: " + String.format("%,.0f", missing));
        }

        c.setBalance(c.getBalance().subtract(finalPrice));
        item.decreaseQuantity();
        c.addPurchase(item.getVehicle());
    }
}