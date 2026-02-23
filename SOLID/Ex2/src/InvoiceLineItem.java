/**
 * Value object representing a single computed order line.
 */
public final class InvoiceLineItem {
    public final String itemName;
    public final int    qty;
    public final double unitPrice;
    public final double lineTotal;

    public InvoiceLineItem(String itemName, int qty, double unitPrice) {
        this.itemName  = itemName;
        this.qty       = qty;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice * qty;
    }
}
