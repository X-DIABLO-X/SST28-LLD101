/**
 * A single discount rule in a composable discount chain.
 * Rules are evaluated in order; the first matching rule wins.
 */
public interface DiscountRule {
    boolean matches(String customerType, double subtotal, int lines);
    double amount(String customerType, double subtotal, int lines);
}
