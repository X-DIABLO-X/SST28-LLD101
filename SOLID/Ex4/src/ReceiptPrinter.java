// Adapted ReceiptPrinter — accepts FeeSummary value object.
public class ReceiptPrinter {
    public static void print(BookingRequest req, FeeSummary summary) {
        System.out.println("Room: " + LegacyRoomTypes.nameOf(req.roomType) + " | AddOns: " + req.addOns);
        System.out.println("Monthly: " + summary.monthly);
        System.out.println("Deposit: " + summary.deposit);
        System.out.println("TOTAL DUE NOW: " + summary.totalDueNow);
    }

    public static void print(BookingRequest req, Money monthly, Money deposit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }
}
