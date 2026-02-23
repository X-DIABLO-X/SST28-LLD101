import java.util.List;

// Immutable computed invoice. All financial values are fully resolved.
public final class Invoice {
    public final String             invoiceId;
    public final List<InvoiceLineItem> lineItems;
    public final double             subtotal;
    public final double             taxPct;
    public final double             tax;
    public final double             discount;
    public final double             total;

    public Invoice(String invoiceId, List<InvoiceLineItem> lineItems,
                   double subtotal, double taxPct, double tax, double discount, double total) {
        this.invoiceId = invoiceId;
        this.lineItems = List.copyOf(lineItems);
        this.subtotal  = subtotal;
        this.taxPct    = taxPct;
        this.tax       = tax;
        this.discount  = discount;
        this.total     = total;
    }
}
