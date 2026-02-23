public class SmsSender extends NotificationSender {

    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public SendResult send(Notification n) {
        // subject is intentionally unused — SMS channel constraint (see Javadoc above)
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return SendResult.ok();
    }
}
