/** Room pricing strategy contract — same as sol1. */
public interface RoomPricingStrategy {
    boolean supports(int roomTypeCode);
    double monthlyFee();
}
