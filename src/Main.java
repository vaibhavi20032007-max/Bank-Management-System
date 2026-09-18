import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        int choice = 0;

        do {
            try {
                System.out.println("\n================================");
                System.out.println("      BANK MANAGEMENT SYSTEM");
                System.out.println("================================");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Account Details");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter Account Number: ");
                        String accountNumber = sc.nextLine();

                        if (findAccount(accounts, accountNumber) != null) {
                            System.out.println("Account number already exists.");
                            break;
                        }

                        System.out.print("Enter Account Holder Name: ");
                        String name = sc.nextLine();

                        if (accountNumber.isEmpty() || name.isEmpty()) {
                            System.out.println(
                                    "Account number and name cannot be empty.");
                            break;
                        }

                        BankAccount newAccount =
                                new BankAccount(accountNumber, name);

                        accounts.add(newAccount);

                        System.out.println(
                                "Account created successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        String depositAccount = sc.nextLine();

                        BankAccount accountForDeposit =
                                findAccount(accounts, depositAccount);

                        if (accountForDeposit != null) {
                            System.out.print("Enter amount to deposit: ");
                            double amount = sc.nextDouble();

                            try {
                                accountForDeposit.deposit(amount);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }

                            sc.nextLine();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        String withdrawAccount = sc.nextLine();

                        BankAccount accountForWithdraw =
                                findAccount(accounts, withdrawAccount);

                        if (accountForWithdraw != null) {
                            System.out.print("Enter amount to withdraw: ");
                            double amount = sc.nextDouble();

                            try {
                                accountForWithdraw.withdraw(amount);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }

                            sc.nextLine();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        String searchAccount = sc.nextLine();

                        BankAccount account =
                                findAccount(accounts, searchAccount);

                        if (account != null) {
                            account.displayAccountDetails();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 5:
                        System.out.println(
                                "Thank you for using Bank Management System!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }

            } catch (java.util.InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter the correct type of value.");

                sc.nextLine();
            }

        } while (choice != 5);

        sc.close();
    }

    public static BankAccount findAccount(
            ArrayList<BankAccount> accounts,
            String accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }
}
