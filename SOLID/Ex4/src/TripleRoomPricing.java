public class TripleRoomPricing implements RoomPricingStrategy {
    @Override public boolean supports(int code) { return code == LegacyRoomTypes.TRIPLE; }
    @Override public double monthlyFee()         { return 12000.0; }
}
