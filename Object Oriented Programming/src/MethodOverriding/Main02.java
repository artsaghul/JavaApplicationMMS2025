interface Payment {
    void pay(double amount);
}

class CreditCard implements Payment {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card");
    }
}

class BankTransfer implements Payment {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Bank Transfer");
    }
}

class MobileWallet implements Payment {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Mobile Wallet");
    }
}