public class LegacyFlags {
    public static final int NONE = 0, WARNING = 1, SUSPENDED = 2;

    public static String nameOf(int f) {
        return switch (f) {
            case WARNING   -> "WARNING";
            case SUSPENDED -> "SUSPENDED";
            default        -> "NONE";
        };
    }
}
