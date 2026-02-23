/**
 * Rule: disciplinary flag check. No threshold — flag == NONE or not.
 * Does not depend on RuleConfig (no threshold to configure).
 */
public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public RuleResult apply(StudentProfile student) {
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return RuleResult.fail("disciplinary flag present");
        }
        return RuleResult.pass();
    }
}
