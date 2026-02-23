public class Money {
    public final double amount;
    public Money(double amount) { this.amount = Math.round(amount * 100.0) / 100.0; }
    public Money plus(Money o)  { return new Money(this.amount + o.amount); }
    @Override public String toString() { return String.format("%.2f", amount); }
}
