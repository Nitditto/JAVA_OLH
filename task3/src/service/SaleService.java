package task3.src.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import task3.src.model.Customer;
import task3.src.model.InventoryItem;
import task3.src.model.Vehicle;
import task3.src.repository.DealershipRepo;

public class SaleService {
    private DealershipRepo repo;

    public SaleService(DealershipRepo repo) {
        this.repo = repo;
    }

    public ArrayList<InventoryItem> getSuggestions(Class<? extends Vehicle> type) {
        ArrayList<InventoryItem> suggestions = new ArrayList<>();
        for (InventoryItem item : repo.getInventory()) {
            Vehicle vehicle = item.getVehicle();
            if (type.isInstance(vehicle) && item.getQuantity() > 0) {
                suggestions.add(item);
            }
        }
        return suggestions;
    }

    public BigDecimal calculateFinalPrice(Vehicle v, Customer c) {
        BigDecimal base = v.totalPrice();
        BigDecimal discountRate = c.getLoyaltyLevel().getDiscountRate();
        return base.subtract(base.multiply(discountRate));
    }

    public boolean processTransaction(Customer c, InventoryItem item, BigDecimal amount) {
        if (c.getBalance().compareTo(amount) >= 0) {
            c.setBalance(c.getBalance().subtract(amount));
            item.decreaseQuantity();
            c.addPurchase(item.getVehicle());
            return true;
        }
        return false;
    }
}