import java.util.HashMap;
import java.util.Map;

/**
 * SRP: Handles output (print) and persistence of formatted invoices.
 * Reason to change: only if output destination or storage format changes.
 */
public class InvoiceWriter {

    private final Map<String, String> store = new HashMap<>();

    public void writeAndStore(String invoiceId, String formattedContent) {
        System.out.print(formattedContent);
        store.put(invoiceId, formattedContent);
        System.out.println("Saved invoice: " + invoiceId + " (lines=" + countLines(invoiceId) + ")");
    }

    private int countLines(String invoiceId) {
        String c = store.getOrDefault(invoiceId, "");
        if (c.isEmpty()) return 0;
        return c.split("\n").length;
    }
}
