import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BillingOrchestrator {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceBuilder   builder;
    private final InvoiceFormatter formatter;
    private final InvoiceWriter    writer;
    private int invoiceSeq = 1000;

    public BillingOrchestrator(InvoiceBuilder builder, InvoiceFormatter formatter, InvoiceWriter writer) {
        this.builder   = builder;
        this.formatter = formatter;
        this.writer    = writer;
    }

    public void addToMenu(MenuItem item) { menu.put(item.id, item); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invoiceId = "INV-" + (++invoiceSeq);
        Invoice inv      = builder.build(invoiceId, customerType, menu, lines);
        String content   = formatter.format(inv);
        writer.writeAndStore(invoiceId, content);
    }
}
