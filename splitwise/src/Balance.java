// Net balance for a user: positive = others owe them, negative = they owe others
public class Balance {
    private final User user;
    private final double amount;

    public Balance(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() { return user; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        if (amount > 0) return user + " gets back " + amount;
        if (amount < 0) return user + " owes " + (-amount);
        return user + " is settled up";
    }
}
