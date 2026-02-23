/**
 * SRP: Formats an Invoice into a printable string.
 * Does NOT print. Does NOT store. Returns a String.
 */
public class InvoiceFormatter {

    public String format(Invoice inv) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(inv.invoiceId).append("\n");
        for (InvoiceLineItem li : inv.lineItems) {
            out.append(String.format("- %s x%d = %.2f\n", li.itemName, li.qty, li.lineTotal));
        }
        out.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.tax));
        out.append(String.format("Discount: -%.2f\n", inv.discount));
        out.append(String.format("TOTAL: %.2f\n", inv.total));
        return out.toString();
    }
}
