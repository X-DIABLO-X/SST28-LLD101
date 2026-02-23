import java.util.Random;

/**
 * Service that calculates FeeSummary, prints receipt, and persists booking.
 * No switch-case. No if/else chains. Zero changes needed when room types grow.
 */
public class HostelFeeService {

    private final RoomPricingRegistry  roomRegistry;
    private final AddOnPricingRegistry addOnRegistry;
    private final FakeBookingRepo      repo;

    public HostelFeeService(RoomPricingRegistry roomRegistry,
                            AddOnPricingRegistry addOnRegistry,
                            FakeBookingRepo repo) {
        this.roomRegistry  = roomRegistry;
        this.addOnRegistry = addOnRegistry;
        this.repo          = repo;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        FeeSummary summary = new FeeSummary(monthly, deposit);
        ReceiptPrinter.print(req, summary);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base   = roomRegistry.getFor(req.roomType).monthlyFee();
        double addOns = addOnRegistry.totalFor(req.addOns);
        return new Money(base + addOns);
    }
}
