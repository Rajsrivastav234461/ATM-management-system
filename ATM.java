import java.util.HashMap;
import java.util.Scanner;

public class ATM {

    private static HashMap<Integer, Integer> accounts = new HashMap<Integer, Integer>();
    private static HashMap<Integer, Double> checkingBalances = new HashMap<Integer, Double>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    login(sc);
                    break;
                case 2:
                    createAccount(sc);
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void login(Scanner sc) {
        System.out.print("Enter Customer ID: ");
        int customerID = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        if (authenticate(customerID, pin)) {
            System.out.println("Login successful!");
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("1. Deposit to Checking");
                System.out.println("2. Check Checking Balance");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        depositChecking(sc, customerID);
                        break;
                    case 2:
                        checkCheckingBalance(customerID);
                        break;
                    case 3:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        } else {
            System.out.println("Incorrect customer ID or PIN.");
        }
    }

    private static void createAccount(Scanner sc) {
        System.out.print("Enter Customer ID: ");
        int customerID = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        if (accounts.containsKey(customerID)) {
            System.out.println("Customer ID already exists.");
        } else {
            accounts.put(customerID, pin);
            checkingBalances.put(customerID, 0.0);
            System.out.println("Account created successfully.");
        }
    }

    private static boolean authenticate(int customerID, int pin) {
        if (accounts.containsKey(customerID)) {
            int storedPin = accounts.get(customerID);
            return pin == storedPin;
        } else {
            return false;
        }
    }

    private static void depositChecking(Scanner sc, int customerID) {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else {
            double balance = checkingBalances.get(customerID);
            balance += amount;
            checkingBalances.put(customerID, balance);
            System.out.println("Deposit successful. New checking balance: " + balance);
        }
    }

    private static void checkCheckingBalance(int customerID) {
        double balance = checkingBalances.get(customerID);
        System.out.println("Checking balance: " + balance);
    }

}
