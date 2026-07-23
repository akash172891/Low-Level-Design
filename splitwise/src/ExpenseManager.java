import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Double> netBalances = new HashMap<>(); // userId -> net amount
    private final List<Expense> expenses = new ArrayList<>();
    private int userCounter = 1;
    private int expenseCounter = 1;

    public User createUser(String name) {
        User user = new User("U" + userCounter++, name);
        users.put(user.getId(), user);
        netBalances.putIfAbsent(user.getId(), 0.0);
        return user;
    }

    // Equal split: each participant owes amount / n; payer's net increases by what others owe
    public Expense addEqualExpense(User paidBy, double amount, List<User> participants) {
        if (participants.isEmpty()) throw new IllegalArgumentException("No participants");

        double share = amount / participants.size();
        List<Split> splits = new ArrayList<>();

        for (User participant : participants) {
            splits.add(new Split(participant, share));
            // Payer fronted everyone's share; each person (including payer) consumes their share
            netBalances.merge(participant.getId(), -share, Double::sum);
        }
        // Payer paid the full bill upfront
        netBalances.merge(paidBy.getId(), amount, Double::sum);

        Expense expense = new Expense("E" + expenseCounter++, paidBy, amount, splits);
        expenses.add(expense);
        return expense;
    }

    public Balance getBalance(User user) {
        return new Balance(user, netBalances.getOrDefault(user.getId(), 0.0));
    }

    public List<Balance> getAllBalances() {
        List<Balance> result = new ArrayList<>();
        for (User user : users.values()) {
            result.add(getBalance(user));
        }
        return result;
    }

    public void showAllBalances() {
        System.out.println("--- All Balances ---");
        getAllBalances().forEach(b -> System.out.println(b));
    }

    public void showBalance(User user) {
        System.out.println(getBalance(user));
    }

    // Greedy settlement on net balances — minimizes number of transactions
    public List<String> getSimplifiedSettlements() {
        List<double[]> creditors = new ArrayList<>(); // [userIndex, amount]
        List<double[]> debtors = new ArrayList<>();
        List<User> userList = new ArrayList<>(users.values());

        for (int i = 0; i < userList.size(); i++) {
            double bal = netBalances.getOrDefault(userList.get(i).getId(), 0.0);
            if (bal > 0.01) creditors.add(new double[]{i, bal});
            else if (bal < -0.01) debtors.add(new double[]{i, -bal});
        }

        List<String> settlements = new ArrayList<>();
        int ci = 0, di = 0;
        while (ci < creditors.size() && di < debtors.size()) {
            double pay = Math.min(creditors.get(ci)[1], debtors.get(di)[1]);
            User from = userList.get((int) debtors.get(di)[0]);
            User to = userList.get((int) creditors.get(ci)[0]);
            settlements.add(from + " pays " + pay + " to " + to);

            creditors.get(ci)[1] -= pay;
            debtors.get(di)[1] -= pay;
            if (creditors.get(ci)[1] < 0.01) ci++;
            if (debtors.get(di)[1] < 0.01) di++;
        }
        return settlements;
    }
}
