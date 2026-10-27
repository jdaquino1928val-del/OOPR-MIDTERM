import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the OOP Banking System");
        double initialBalance = readNonNegativeBalance(scanner);

        SavingsAccount savingsAccount = new SavingsAccount("SA-101", initialBalance);
        CheckingAccount checkingAccount = new CheckingAccount("CA-101", initialBalance);

        // Polymorphic reference
        Account selectedAccount = savingsAccount;

        boolean running = true;
        while (running) {
            printAccountSummary(selectedAccount);
            showMenu();

            System.out.print("Choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a valid number.");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    double withdrawAmount = readPositiveAmount(scanner, "Amount to withdraw: ");
                    if (withdrawAmount > 0) {
                        selectedAccount.withdraw(withdrawAmount);
                    }
                    break;
                case 2:
                    double depositAmount = readPositiveAmount(scanner, "Amount to deposit: ");
                    if (depositAmount > 0) {
                        selectedAccount.deposit(depositAmount);
                    }
                    break;
                case 3:
                    System.out.printf("Current %s Balance: P%.2f%n", 
                                      selectedAccount.getAccountType(), selectedAccount.getBalance());
                    break;
                case 4:
                    if (selectedAccount == savingsAccount) {
                        selectedAccount = checkingAccount;
                    } else {
                        selectedAccount = savingsAccount;
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu choice.");
                    break;
            }
        }

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("1 Withdraw  2 Deposit  3 Show balance  4 Switch account  5 Exit");
    }

    public static double readNonNegativeBalance(Scanner scanner) {
        double amount = -1;
        while (amount < 0) {
            System.out.print("Opening balance: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Opening balance cannot be negative.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return amount;
    }

    public static double readPositiveAmount(Scanner scanner, String prompt) {
        System.out.print(prompt);
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return -1;
            }
            return amount;
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
            return -1;
        }
    }

    public static void printAccountSummary(Account account) {
        System.out.printf("Current account: %s | Balance: P%.2f%n", 
                          account.getAccountType(), account.getBalance());
    }
}