import java.util.*;

public class DigitalWalletProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Wallet w1 = new Wallet();
        Wallet w2 = new Wallet();

        System.out.println("=== DIGITAL WALLET SYSTEM (LIVE PROTOTYPE) ===");

        System.out.print("\nEnter deposit amount for Wallet 1 (W1): ");
        double depositAmount = sc.nextDouble();
        w1.deposit(depositAmount);

        System.out.println("\n--- Initial Balances ---");
        System.out.println("Initial Balance (W1): Rs. " + w1.getBalance());
        System.out.println("Initial Balance (W2): Rs. " + w2.getBalance());

        System.out.print("\nEnter transfer amount from W1 to W2: ");
        double transferAmount = sc.nextDouble();

        System.out.println("\nProcessing transaction...");
        w1.transferTo(w2, transferAmount);

        System.out.println("\n--- Final Balances After Transaction ---");
        System.out.println("Final Balance (W1): Rs. " + w1.getBalance());
        System.out.println("Final Balance (W2): Rs. " + w2.getBalance());
        
        sc.close();
        System.out.println("\n===============================================");
    }
}

class Wallet {
    private double balance;
    static double cashbackRate = 0.02; 

    public Wallet() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Rs. " + amount + " successfully deposited.");
        } else {
            System.out.println("Deposit Failed: Amount must be positive.");
        }
    }

    public void transferTo(Wallet receiver, double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Transfer amount must be positive.");
            }
            if (this.balance < amount) {
                throw new Exception("Insufficient balance in Sender's Wallet.");
            }

            this.balance -= amount;
            receiver.balance += amount;

            double cashback = amount * cashbackRate;
            this.balance += cashback; 

            System.out.println("Transaction Successful!");
            System.out.println("Cashback rewarded to Sender: Rs. " + cashback);

        } catch (Exception e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }
}