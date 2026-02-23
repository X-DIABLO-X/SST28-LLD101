import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        // Change thresholds here for a different department without touching rule logic
        RuleConfig config = new RuleConfig(8.0, 75, 20);

        List<EligibilityRule> rules = List.of(
                new DisciplinaryFlagRule(),
                new CgrThresholdRule(config),
                new AttendanceRule(config),
                new CreditRule(config)
        );

        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        EligibilityEngine engine = new EligibilityEngine(rules);
        engine.runAndPrint(s);
    }
}
