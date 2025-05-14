/*
Problem Statement:
Create a simple bank account management system that allows:
1. Creating an account.
2. Depositing and withdrawing money.
3. Viewing account balance.

Ensure proper encapsulation, input validation, and method use.
*/

import java.util.*;

class BankAccount {
    private String holderName;
    private double balance;

    BankAccount(String holderName) {
        this.holderName = holderName;
        this.balance = 0.0;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Rs." + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrew Rs." + amount);
        }
    }

    void showBalance() {
        System.out.println(holderName + "'s Balance: Rs." + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account holder's name: ");
        String name = sc.nextLine();
        BankAccount account = new BankAccount(name);

        int choice;
        do {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Show Balance\n4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}
