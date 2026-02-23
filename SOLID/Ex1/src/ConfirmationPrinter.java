import java.util.List;

/**
 * SRP: Single responsibility - handle console output for registration outcomes.
 * Reason to change: only if the output format or channel changes
 *                   (e.g., switch to JSON API response, log file, etc.)
 */
public class ConfirmationPrinter {

    // Prints a successful registration confirmation.
    public void printSuccess(String id, int totalCount, StudentRecord rec) {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }

    // Prints error messages when registration cannot proceed.
    public void printErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) System.out.println("- " + e);
    }
}
