/**
 * Rule: CGR threshold check.
 * Threshold sourced from RuleConfig — change threshold without editing this class.
 */
public class CgrThresholdRule implements EligibilityRule {

    private final double minCgr;

    public CgrThresholdRule(RuleConfig config) {
        this.minCgr = config.minCgr;
    }

    @Override
    public RuleResult apply(StudentProfile student) {
        if (student.cgr < minCgr) {
            return RuleResult.fail("CGR below " + minCgr);
        }
        return RuleResult.pass();
    }
}
