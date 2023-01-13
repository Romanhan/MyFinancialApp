package romanhan;

import romanhan.controller.Expenses;

import java.sql.*;
import java.util.Calendar;

public class MySQLDatabase {
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    public static Connection connectToDatabase() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println("SQL Connect exception : " + exception);
            throw new RuntimeException(exception);
        }
        return connection;
    }

    public static void startWithDatabase(Expenses expenses) {
        try (Statement statement = connectToDatabase().createStatement()) {
            try { // If database not exists, create
                statement.execute("CREATE DATABASE my_financial_app");
                statement.execute("USE my_financial_app");
                statement.execute("CREATE TABLE user_data (month INT, user_budget INT," +
                        "apartment_leasing INT, apartment_bill INT, car_leasing INT, car_casco INT," +
                        "car_insurance INT, gas INT, electricity INT, internet INT, kindergarten INT," +
                        "phones INT, deposit INT, food INT, total_expenses INT)");

                int currentMonth = getCurrentMonth();
                String month = "INSERT INTO user_data (month) VALUE (" + currentMonth + ")";
                statement.execute(month);
                System.out.println("Creating database...");
            } catch (SQLException exception) {
                // If database exists, read all data from it
                System.out.println("Reading data from database...");
                statement.execute("USE my_financial_app");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_data");
                while (resultSet.next()) {
                    expenses.setBudget(resultSet.getInt("user_budget"));
                    expenses.setApartmentLeasing(resultSet.getInt("apartment_leasing"));
                    expenses.setApartmentBill(resultSet.getInt("apartment_bill"));
                    expenses.setCarLeasing(resultSet.getInt("car_leasing"));
                    expenses.setCarCasco(resultSet.getInt("car_casco"));
                    expenses.setCarInsurance(resultSet.getInt("car_insurance"));
                    expenses.setGas(resultSet.getInt("gas"));
                    expenses.setElectricity(resultSet.getInt("electricity"));
                    expenses.setInternet(resultSet.getInt("internet"));
                    expenses.setKindergarten(resultSet.getInt("kindergarten"));
                    expenses.setPhones(resultSet.getInt("phones"));
                    expenses.setDeposit(resultSet.getInt("deposit"));
                    expenses.setFood(resultSet.getInt("food"));
                    expenses.setTotalExpensesForMonth(resultSet.getInt("total_expenses"));
                }
            }
        } catch (SQLException exception) {
            System.err.println("SQLException : " + exception);
        }
    }

    public static void saveToDatabase(Expenses expenses) {
        try (Statement statement = connectToDatabase().createStatement()) {
            int month = getCurrentMonth();

            statement.execute("USE my_financial_app");
            String query = "UPDATE user_data SET user_budget = " + expenses.getBudget() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET apartment_leasing = " + expenses.getApartmentLeasing() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET apartment_bill = " + expenses.getApartmentBill() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET car_leasing = " + expenses.getCarLeasing() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET car_casco = " + expenses.getCarCasco() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET car_insurance = " + expenses.getCarInsurance() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET gas = " + expenses.getGas() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET electricity = " + expenses.getElectricity() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET internet = " + expenses.getInternet() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET kindergarten = " + expenses.getKindergarten() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET phones = " + expenses.getPhones() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET deposit = " + expenses.getDeposit() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET food = " + expenses.getFood() + " WHERE month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE user_data SET total_expenses = " + expenses.getTotalExpensesForMonth() + " WHERE month = " + month;
            statement.executeUpdate(query);
            System.out.println("Data saved.");
        } catch (SQLException e) {
            System.err.println("SQL Save data Exception : " + e);
        }
    }
}
