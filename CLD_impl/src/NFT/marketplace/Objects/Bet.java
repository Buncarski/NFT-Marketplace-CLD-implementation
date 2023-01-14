package NFT.marketplace.Objects;

public class Bet {
    private double amount;
    private RegisteredUser better;

    public Bet(double amount, RegisteredUser better) {
        this.amount = amount;
        this.better = better;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public RegisteredUser getBetter() {
        return better;
    }

    public void setBetter(RegisteredUser better) {
        this.better = better;
    }
}
