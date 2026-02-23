import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builds an Invoice from order data using injected pricing strategies.
 * - Uses TaxPolicy for tax rate
 * - Iterates DiscountRule chain to find first matching discount
 */
public class InvoiceBuilder {

    private final TaxPolicyFactory   taxFactory;
    private final List<DiscountRule> discountRules;

    public InvoiceBuilder(TaxPolicyFactory taxFactory, List<DiscountRule> discountRules) {
        this.taxFactory     = taxFactory;
        this.discountRules  = List.copyOf(discountRules);
    }

    public Invoice build(String invoiceId, String customerType,
                         Map<String, MenuItem> menu, List<OrderLine> orderLines) {
        List<InvoiceLineItem> lineItems = new ArrayList<>();
        double subtotal = 0;

        for (OrderLine ol : orderLines) {
            MenuItem item = menu.get(ol.itemId);
            InvoiceLineItem li = new InvoiceLineItem(item.name, ol.qty, item.price);
            lineItems.add(li);
            subtotal += li.lineTotal;
        }

        double taxPct   = taxFactory.getPolicy(customerType).taxPercent();
        double tax      = subtotal * (taxPct / 100.0);
        double discount = resolveDiscount(customerType, subtotal, orderLines.size());
        double total    = subtotal + tax - discount;

        return new Invoice(invoiceId, lineItems, subtotal, taxPct, tax, discount, total);
    }

    private double resolveDiscount(String customerType, double subtotal, int lines) {
        for (DiscountRule rule : discountRules) {
            if (rule.matches(customerType, subtotal, lines)) {
                return rule.amount(customerType, subtotal, lines);
            }
        }
        return 0.0;
    }
}
