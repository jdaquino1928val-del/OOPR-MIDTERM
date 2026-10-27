import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Your new balance is: " + (int)balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Your new balance is: " + (int)balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!");
        System.out.print("Enter your balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(initialBalance);

        int choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Withdraw Amount");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("How much amount you want to withdraw from your account?");
                System.out.print("Amount: ");
                double amount = scanner.nextDouble();
                account.withdraw(amount);
            } else if (choice == 2) {
                System.out.println("How much amount you want to deposit in your account?");
                System.out.print("Amount: ");
                double amount = scanner.nextDouble();
                account.deposit(amount);
            } else if (choice == 3) {
                System.out.println("Thank you for using the system!");
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}