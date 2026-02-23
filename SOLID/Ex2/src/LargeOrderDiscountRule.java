/**
 * Discount rule: applies a fixed amount when order has ≥ N distinct lines.
 * Example: staff orders ≥ 3 lines → 15 off.
 */
public class LargeOrderDiscountRule implements DiscountRule {

    private final String forCustomerType;
    private final int    minLines;
    private final double discountAmount;

    public LargeOrderDiscountRule(String forCustomerType, int minLines, double discountAmount) {
        this.forCustomerType = forCustomerType;
        this.minLines        = minLines;
        this.discountAmount  = discountAmount;
    }

    @Override
    public boolean matches(String customerType, double subtotal, int lines) {
        return forCustomerType.equalsIgnoreCase(customerType) && lines >= minLines;
    }

    @Override
    public double amount(String customerType, double subtotal, int lines) {
        return discountAmount;
    }
}
