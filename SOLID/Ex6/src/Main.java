public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");

        AuditLog audit = new AuditLog();

        // phone = "9876543210" — not E.164, so WA will return an error result
        Notification n = new Notification(
            "Welcome",
            "Hello and welcome to SST!",
            "riya@sst.edu",
            "9876543210"
        );

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms   = new SmsSender(audit);
        NotificationSender wa    = new WhatsAppSender(audit);

        // No try/catch needed — the contract guarantees send() never throws
        dispatchAndLog(email, n, "EMAIL", audit);
        dispatchAndLog(sms,   n, "SMS",   audit);
        dispatchAndLog(wa,    n, "WA",    audit);

        System.out.println("AUDIT entries=" + audit.size());
    }

    /**
     * Calls send(), checks the result, and prints any failure.
     * Audit logging for failures is handled here (sender already logged success).
     */
    private static void dispatchAndLog(
            NotificationSender sender,
            Notification n,
            String label,
            AuditLog audit) {

        SendResult result = sender.send(n);
        if (!result.success()) {
            System.out.println(label + " ERROR: " + result.errorMessage());
            audit.add(label.toLowerCase() + " failed");
        }
    }
}
