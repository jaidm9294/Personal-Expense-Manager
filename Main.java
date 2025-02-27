package com.financetracker;

import com.financetracker.service.ExpenseManager;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n-- Personal Expense Manager --");
            System.out.println("1. Add Category");
            System.out.println("2. List Categories");
            System.out.println("3. Add Expense");
            System.out.println("4. List Expenses");
            System.out.println("5. Monthly Expenses");
            System.out.println("6. Yearly Expenses");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ExpenseManager.addCategory();
                    break;
                case 2:
                    ExpenseManager.listCategories();
                    break;
                case 3:
                    ExpenseManager.addExpense();
                    break;
                case 4:
                    ExpenseManager.listExpenses();
                    break;
                case 5:
                    ExpenseManager.monthlyExpense();
                    break;
                case 6:
                    ExpenseManager.yearlyExpense();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}

