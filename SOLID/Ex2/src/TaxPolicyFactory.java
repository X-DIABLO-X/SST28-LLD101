import java.util.HashMap;
import java.util.Map;

/**
 * Resolves a TaxPolicy from a customer-type string.
 * Adding a new type = one line: policies.put("alumni", new AlumniTaxPolicy()).
 */
public class TaxPolicyFactory {

    private final Map<String, TaxPolicy> policies = new HashMap<>();

    public TaxPolicyFactory() {
        policies.put("student",   new StudentTaxPolicy());
        policies.put("staff",     new StaffTaxPolicy());
        policies.put("corporate", new CorporateTaxPolicy());
    }

    public TaxPolicy getPolicy(String type) {
        return policies.getOrDefault(
                type == null ? "" : type.toLowerCase(),
                new DefaultTaxPolicy()
        );
    }
}
