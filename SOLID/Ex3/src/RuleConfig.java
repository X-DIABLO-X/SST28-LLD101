
public class RuleConfig {
    public final double minCgr;
    public final int    minAttendancePct;
    public final int    minCredits;

    /** Default institutional thresholds. */
    public RuleConfig() {
        this(8.0, 75, 20);
    }

    /** Custom thresholds — e.g., for different academic programs. */
    public RuleConfig(double minCgr, int minAttendancePct, int minCredits) {
        if (minCgr < 0 || minAttendancePct < 0 || minCredits < 0)
            throw new IllegalArgumentException("Thresholds cannot be negative");
        this.minCgr           = minCgr;
        this.minAttendancePct = minAttendancePct;
        this.minCredits       = minCredits;
    }
}
