import java.util.ArrayList;
import java.util.List;

// OCP-compliant engine — identical to sol1.
public class EligibilityEngine {

    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore  store = new FakeEligibilityStore();

    public EligibilityEngine(List<EligibilityRule> rules) {
        this.rules = List.copyOf(rules);
    }

    public void runAndPrint(StudentProfile student) {
        ReportPrinter printer = new ReportPrinter();
        EligibilityEngineResult result = evaluate(student);
        printer.print(student, result);
        store.save(student.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile student) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            RuleResult r = rule.apply(student);
            if (!r.passed()) {
                reasons.add(r.reason());
                return new EligibilityEngineResult("NOT_ELIGIBLE", reasons);
            }
        }
        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }
}
