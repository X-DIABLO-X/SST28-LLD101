import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry for add-on prices.
 * Adding a new add-on: register(AddOn.PARKING, 400.0).
 * No other code changes.
 */
public class AddOnPricingRegistry {

    private final Map<AddOn, Double> prices = new HashMap<>();

    public AddOnPricingRegistry register(AddOn addOn, double price) {
        prices.put(addOn, price);
        return this;  // fluent for chained registration
    }

    public double totalFor(List<AddOn> addOns) {
        return addOns.stream()
                .mapToDouble(a -> prices.getOrDefault(a, 0.0))
                .sum();
    }
}
