/** Value object for a single rule's evaluation outcome. */
public final class RuleResult {
    private final boolean passed;
    private final String  reason;

    private RuleResult(boolean passed, String reason) {
        this.passed = passed;
        this.reason = reason;
    }

    public static RuleResult pass()             { return new RuleResult(true, null); }
    public static RuleResult fail(String why)   { return new RuleResult(false, why); }

    public boolean passed() { return passed; }
    public String  reason() { return reason; }
}
