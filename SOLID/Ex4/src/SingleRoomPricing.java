public class SingleRoomPricing implements RoomPricingStrategy {
    @Override public boolean supports(int code) { return code == LegacyRoomTypes.SINGLE; }
    @Override public double monthlyFee()         { return 14000.0; }
}
