public final class SendResult {

    private final boolean success;
    private final String  errorMessage;

    private SendResult(boolean success, String errorMessage) {
        this.success      = success;
        this.errorMessage = errorMessage;
    }

    /** Delivery succeeded. */
    public static SendResult ok() {
        return new SendResult(true, null);
    }

    public static SendResult error(String reason) {
        return new SendResult(false, reason);
    }

    public boolean success()      { return success; }
    public String  errorMessage() { return errorMessage; }
}
