public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateWithdrawalFee() {
        return 0.0;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}