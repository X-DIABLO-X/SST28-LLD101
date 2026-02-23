public class LegacyRoomTypes {
    public static final int SINGLE = 1, DOUBLE = 2, TRIPLE = 3, DELUXE = 4;

    public static String nameOf(int t) {
        return switch (t) {
            case SINGLE -> "SINGLE"; case DOUBLE -> "DOUBLE";
            case TRIPLE -> "TRIPLE"; default      -> "DELUXE";
        };
    }
}
