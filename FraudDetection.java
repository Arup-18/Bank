public class FraudDetection {

    public static boolean checkFraud(double amount, double balance, int count) {

        if(amount <= 0) {
            System.out.println("Invalid amount!");
            return true;
        }

        if(amount > 50000) {
            System.out.println("Fraud Alert: Large withdrawal!");
            return true;
        }

        if(amount > balance) {
            System.out.println("Fraud Alert: Insufficient balance attempt!");
            return true;
        }

        if(count >= 3) {
            System.out.println("Fraud Alert: Too many withdrawals!");
            return true;
        }

        return false;
    }
}
