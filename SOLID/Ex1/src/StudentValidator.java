import java.util.*;

/**
 * SRP: Single responsibility - validate student registration fields.
 * Reason to change: only if validation rules change (new fields, new constraints).
 */
public class StudentValidator {

    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    // Validates the provided field map.
    public List<String> validate(Map<String, String> fields) {
        List<String> errors = new ArrayList<>();

        String name    = fields.getOrDefault("name", "");
        String email   = fields.getOrDefault("email", "");
        String phone   = fields.getOrDefault("phone", "");
        String program = fields.getOrDefault("program", "");

        if (name.isBlank())
            errors.add("name is required");
        if (email.isBlank() || !email.contains("@"))
            errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(program))
            errors.add("program is invalid");

        return errors;
    }
}
