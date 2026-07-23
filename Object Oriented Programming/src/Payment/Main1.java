public class Main {
    public static void main(String[] args) {
        Payment p1 = new CreditCard();
        Payment p2 = new BankTransfer();
        Payment p3 = new MobileWallet();
        p1.pay(100); p2.pay(200); p3.pay(50);
    }
}