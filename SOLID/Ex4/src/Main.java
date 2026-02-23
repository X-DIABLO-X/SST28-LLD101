import java.util.List;

/**
 * Main — wires registries and runs.
 * Adding a new room type: register it here.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        // Room pricing registry — specific types first, fallback last
        RoomPricingRegistry roomRegistry = new RoomPricingRegistry();
        roomRegistry.register(LegacyRoomTypes.SINGLE, new SingleRoomPricing());
        roomRegistry.register(LegacyRoomTypes.DOUBLE, new DoubleRoomPricing());
        roomRegistry.register(LegacyRoomTypes.TRIPLE, new TripleRoomPricing());
        roomRegistry.setFallback(new DeluxeRoomPricing());

        // Add-on pricing registry
        AddOnPricingRegistry addOnRegistry = new AddOnPricingRegistry()
                .register(AddOn.MESS,    1000.0)
                .register(AddOn.LAUNDRY,  500.0)
                .register(AddOn.GYM,      300.0);

        HostelFeeService service = new HostelFeeService(roomRegistry, addOnRegistry, new FakeBookingRepo());

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        service.process(req);
    }
}
