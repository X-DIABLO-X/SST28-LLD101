/**
 * Rule: attendance percentage check.
 * Threshold sourced from RuleConfig.
 */
public class AttendanceRule implements EligibilityRule {

    private final int minAttendancePct;

    public AttendanceRule(RuleConfig config) {
        this.minAttendancePct = config.minAttendancePct;
    }

    @Override
    public RuleResult apply(StudentProfile student) {
        if (student.attendancePct < minAttendancePct) {
            return RuleResult.fail("attendance below " + minAttendancePct);
        }
        return RuleResult.pass();
    }
}
