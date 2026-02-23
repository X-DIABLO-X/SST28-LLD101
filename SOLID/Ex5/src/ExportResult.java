
public final class ExportResult {

    private final boolean error;
    private final String  errorMessage;
    public  final String  contentType;
    public  final byte[]  bytes;

    private ExportResult(String contentType, byte[] bytes, boolean error, String errorMessage) {
        this.contentType  = contentType;
        this.bytes        = bytes;
        this.error        = error;
        this.errorMessage = errorMessage;
    }

    // ── factory methods ──────────────────────────────────────────────────────

    /** Successful export with a non-null byte payload. */
    public static ExportResult ok(String contentType, byte[] bytes) {
        return new ExportResult(contentType, bytes, false, null);
    }

    /**
     * Failed export with a human-readable reason.
     * {@code contentType} is null and {@code bytes} is empty.
     */
    public static ExportResult error(String message) {
        return new ExportResult(null, new byte[0], true, message);
    }

    // ── accessors ────────────────────────────────────────────────────────────

    public boolean isError()       { return error; }
    public String  errorMessage()  { return errorMessage; }
}
