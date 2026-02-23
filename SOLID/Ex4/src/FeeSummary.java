/**
 * Value object holding the complete billing result.
 * Separates calculated fee data from receipt formatting.
 */
public final class FeeSummary {
    public final Money monthly;
    public final Money deposit;
    public final Money totalDueNow;

    public FeeSummary(Money monthly, Money deposit) {
        this.monthly     = monthly;
        this.deposit     = deposit;
        this.totalDueNow = monthly.plus(deposit);
    }
}
