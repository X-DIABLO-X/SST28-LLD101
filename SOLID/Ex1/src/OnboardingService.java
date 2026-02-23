import java.util.*;

/**
 * SRP COMPLIANT: OnboardingService is now a pure orchestrator.
 *
 * It sequences the steps of onboarding but delegates every concrete task:
 *   - Parsing         → RawInputParser
 *   - Validation      → StudentValidator
 *   - ID generation   → StudentIdGenerator
 *   - Persistence     → StudentRepository (interface)
 *   - Output          → ConfirmationPrinter
 *
 * The only reason to change this class: the overall workflow steps change.
 */
public class OnboardingService {

    private final StudentRepository     repository;
    private final RawInputParser        parser = new RawInputParser();
    private final StudentValidator      validator = new StudentValidator();
    private final StudentIdGenerator    idGenerator = new StudentIdGenerator();
    private final ConfirmationPrinter   printer = new ConfirmationPrinter();

    public OnboardingService(StudentRepository repository) {
        this.repository  = repository;
    }

    /**
     * Registers a student from a raw key=value input string.
     * Orchestrates: parse → validate → generate ID → save → print.
     */
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> fields = parser.parse(raw);
        List<String> errors = validator.validate(fields);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = idGenerator.generate(repository.count());
        StudentRecord rec = new StudentRecord(
                id,
                fields.get("name"),
                fields.get("email"),
                fields.get("phone"),
                fields.get("program")
        );

        repository.save(rec);
        printer.printSuccess(id, repository.count(), rec);
    }
}
