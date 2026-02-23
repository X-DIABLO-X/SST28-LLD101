import java.util.List;
// Same workflow with modular desgin.
public class CafeteriaSystem {

    private final BillingOrchestrator orchestrator;

    public CafeteriaSystem() {
        List<DiscountRule> rules = List.of(
                new MinSubtotalDiscountRule("student", 180.0, 10.0),
                new LargeOrderDiscountRule("staff", 3, 15.0),
                new DefaultDiscountRule("staff", 5.0)
        );

        this.orchestrator = new BillingOrchestrator(
                new InvoiceBuilder(new TaxPolicyFactory(), rules),
                new InvoiceFormatter(),
                new InvoiceWriter()
        );
    }

    public void addToMenu(MenuItem item)                             { orchestrator.addToMenu(item); }
    public void checkout(String customerType, List<OrderLine> lines) { orchestrator.checkout(customerType, lines); }
}
