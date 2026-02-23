/**
 * SRP: Single responsibility - generate unique student IDs.
 * Reason to change: only if ID format or sequencing strategy changes.
 */
public class StudentIdGenerator {

    //Generates the next student ID based on the current student count.
    public String generate(int currentCount) {
        return IdUtil.nextStudentId(currentCount);
    }
}
