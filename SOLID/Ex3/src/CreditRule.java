/**
 * Rule: earned credits check.
 * Threshold sourced from RuleConfig.
 */
public class CreditRule implements EligibilityRule {

    private final int minCredits;

    public CreditRule(RuleConfig config) {
        this.minCredits = config.minCredits;
    }

    @Override
    public RuleResult apply(StudentProfile student) {
        if (student.earnedCredits < minCredits) {
            return RuleResult.fail("credits below " + minCredits);
        }
        return RuleResult.pass();
    }
}
