package com.financetracker.service;

import com.financetracker.dao.DatabaseUtil;
import com.financetracker.model.Expense;

import java.sql.*;
import java.util.Scanner;

public class ExpenseManager {
    private static Scanner scanner = new Scanner(System.in);

    public static void addCategory() {
        System.out.print("Enter category name: ");
        String categoryName = scanner.next();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO categories (category_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, categoryName);
            statement.executeUpdate();
            System.out.println("Category added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
    }

    public static void listCategories() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM categories";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\nCategories:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing categories: " + e.getMessage());
        }
    }

    public static void addExpense() {
        listCategories();
        System.out.print("Select category ID: ");
        int categoryId = scanner.nextInt();

        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter expense description: ");
        scanner.nextLine();  // Consume newline left by nextInt()
        String description = scanner.nextLine();

        System.out.print("Enter expense date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO expenses (category_id, amount, description, date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, categoryId);
            statement.setDouble(2, amount);
            statement.setString(3, description);
            statement.setDate(4, Date.valueOf(date));
            statement.executeUpdate();
            System.out.println("Expense added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    public static void listExpenses() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT e.id, c.category_name, e.amount, e.description, e.date FROM expenses e " +
                    "JOIN categories c ON e.category_id = c.id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("\nExpenses:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("category_name") +
                        " | " + resultSet.getDouble("amount") + " | " + resultSet.getString("description") +
                        " | " + resultSet.getDate("date"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing expenses: " + e.getMessage());
        }
    }

    public static void monthlyExpense() {
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT SUM(amount) FROM expenses WHERE MONTH(date) = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Total expense for the month: " + resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            System.out.println("Error calculating monthly expense: " + e.getMessage());
        }
    }

    public static void yearlyExpense() {
        System.out.print("Enter year (YYYY): ");
        int year = scanner.nextInt();

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT SUM(amount) FROM expenses WHERE YEAR(date) = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Total expense for the year: " + resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            System.out.println("Error calculating yearly expense: " + e.getMessage());
        }
    }
}
