public class DoubleRoomPricing implements RoomPricingStrategy {
    @Override public boolean supports(int code) { return code == LegacyRoomTypes.DOUBLE; }
    @Override public double monthlyFee()         { return 15000.0; }
}
