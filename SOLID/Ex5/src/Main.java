
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req  = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter      pdf  = new PdfExporter();
        Exporter      csv  = new CsvExporter();
        Exporter      json = new JsonExporter();

        System.out.println("PDF: "  + safe(pdf,  req));
        System.out.println("CSV: "  + safe(csv,  req));
        System.out.println("JSON: " + safe(json, req));
    }

    /**
     * Calls export() and returns a display string.
     * No try/catch — the LSP contract guarantees export() never throws.
     */
    private static String safe(Exporter e, ExportRequest r) {
        ExportResult result = e.export(r);
        if (result.isError()) return "ERROR: " + result.errorMessage();
        return "OK bytes=" + result.bytes.length;
    }
}
