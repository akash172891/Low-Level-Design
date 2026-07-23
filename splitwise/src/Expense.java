import java.util.List;

public class Expense {
    private final String id;
    private final User paidBy;
    private final double amount;
    private final List<Split> splits;

    public Expense(String id, User paidBy, double amount, List<Split> splits) {
        this.id = id;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public String getId() { return id; }
    public User getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
    public List<Split> getSplits() { return splits; }
}
