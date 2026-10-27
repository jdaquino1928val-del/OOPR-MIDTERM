public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateWithdrawalFee() {
        return 10.0;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}