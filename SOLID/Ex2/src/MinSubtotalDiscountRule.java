/**
 * Discount rule: applies a fixed amount when subtotal reaches a threshold.
 * Example: student orders ≥ 180 → 10 off.
 */
public class MinSubtotalDiscountRule implements DiscountRule {

    private final String forCustomerType;
    private final double minSubtotal;
    private final double discountAmount;

    public MinSubtotalDiscountRule(String forCustomerType, double minSubtotal, double discountAmount) {
        this.forCustomerType = forCustomerType;
        this.minSubtotal     = minSubtotal;
        this.discountAmount  = discountAmount;
    }

    @Override
    public boolean matches(String customerType, double subtotal, int lines) {
        return forCustomerType.equalsIgnoreCase(customerType) && subtotal >= minSubtotal;
    }

    @Override
    public double amount(String customerType, double subtotal, int lines) {
        return discountAmount;
    }
}
