// 4. Main class to test
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("12345", "John Doe", 1000);
        acc.displayAccountDetails();
        acc.deposit(500);
        acc.withdraw(200);
        acc.displayAccountDetails();
    }
}

