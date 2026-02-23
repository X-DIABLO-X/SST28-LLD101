/**
 * Wiring entry point — output is identical to the original.
 *
 * The only change vs. the original Main: OnboardingService now receives
 * its collaborators as constructor arguments (explicit composition).
 * FakeDb is still used directly for TextTable (a display utility).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");

        FakeDb db = new FakeDb();

        OnboardingService svc = new OnboardingService(db);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
