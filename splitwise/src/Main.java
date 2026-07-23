import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        User user1 = manager.createUser("User1");
        User user2 = manager.createUser("User2");
        User user3 = manager.createUser("User3");

        // User1 pays 300 split equally among User1, User2, User3
        manager.addEqualExpense(user1, 300, Arrays.asList(user1, user2, user3));

        manager.showAllBalances();
        System.out.println();
        manager.showBalance(user1);
        System.out.println();
        System.out.println("--- Simplified Settlements ---");
        manager.getSimplifiedSettlements().forEach(System.out::println);
    }
}
