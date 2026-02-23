import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    public ExportResult export(ExportRequest req) {
        // sanitise: RFC 4180 disallows bare newlines and commas outside quoted fields
        String body = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        String csv  = "title,body\n" + req.title + "," + body + "\n";
        return ExportResult.ok("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
