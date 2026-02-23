public class WhatsAppSender extends NotificationSender {

    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public SendResult send(Notification n) {
        // WhatsApp channel constraint: phone must be E.164 (starts with "+" and country code)
        if (n.phone == null || !n.phone.startsWith("+")) {
            // LSP-safe: signal via result, never via exception
            return SendResult.error("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
        return SendResult.ok();
    }
}
