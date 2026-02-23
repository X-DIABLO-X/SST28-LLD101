import java.util.*;

/**
 * In-memory student data store.
 * Now implements StudentRepository so it's usable through the interface.
 */
public class FakeDb implements StudentRepository {

    private final List<StudentRecord> rows = new ArrayList<>();

    @Override
    public void save(StudentRecord r) { rows.add(r); }

    @Override
    public int count() { return rows.size(); }

    public List<StudentRecord> all() { return Collections.unmodifiableList(rows); }
}
