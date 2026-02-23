/**
 * Fallback discount rule: applies a fixed flat discount for a specific customer type.
 * Example: staff always gets 5 off (when no larger discount matches).
 */
public class DefaultDiscountRule implements DiscountRule {

    private final String forCustomerType;
    private final double flatDiscount;

    public DefaultDiscountRule(String forCustomerType, double flatDiscount) {
        this.forCustomerType = forCustomerType;
        this.flatDiscount    = flatDiscount;
    }

    @Override
    public boolean matches(String customerType, double subtotal, int lines) {
        return forCustomerType.equalsIgnoreCase(customerType);
    }

    @Override
    public double amount(String customerType, double subtotal, int lines) {
        return flatDiscount;
    }
}
