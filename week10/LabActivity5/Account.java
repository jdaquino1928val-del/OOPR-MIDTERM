public abstract class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return false;
        }
        this.balance += amount;
        System.out.printf("New %s balance: P%.2f%n", getAccountType(), this.balance);
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        
        double fee = calculateWithdrawalFee();
        if (this.balance < amount + fee) {
            System.out.println("Insufficient funds! Transaction rejected.");
            return false;
        }
        
        this.balance -= (amount + fee);
        System.out.printf("Withdrawal fee: P%.2f | New %s balance: P%.2f%n", fee, getAccountType(), this.balance);
        return true;
    }

    public abstract double calculateWithdrawalFee();
    public abstract String getAccountType();
}