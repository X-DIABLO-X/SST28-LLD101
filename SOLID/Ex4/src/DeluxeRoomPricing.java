/** Default fallback — handles DELUXE and any unregistered room type. */
public class DeluxeRoomPricing implements RoomPricingStrategy {
    @Override public boolean supports(int code) { return true; }
    @Override public double monthlyFee()         { return 16000.0; }
}
