// OCP contract for eligibility rules.
public interface EligibilityRule {
    RuleResult apply(StudentProfile student);
}
