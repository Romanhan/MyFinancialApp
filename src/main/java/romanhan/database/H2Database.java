package romanhan.database;

import romanhan.controller.Expenses;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Calendar;

public class H2Database {
    public static String[] getMonthName() {
        return new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    }

    public static String currentMonthAndYear() {
        Calendar cal = Calendar.getInstance();
        String currentMonth = getMonthName()[cal.get(Calendar.MONTH)];
        int currentYear = cal.get(Calendar.YEAR);
        return currentMonth + " " + currentYear;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException("Can not create connection", e);
        }
    }

    public static void startWithDatabase(Expenses expenses) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String actualMonth = "INSERT INTO my_financial_app (current_month) VALUES ('" + currentMonthAndYear() + "')";
            // If database not exists, create
            String createTable = "CREATE TABLE IF NOT EXISTS my_financial_app (current_month VARCHAR(15) UNIQUE, user_budget INT, apartment_leasing INT, apartment_bill INT, " +
                    "car_leasing INT, car_casco INT, car_insurance INT, gas INT, electricity INT, internet INT, kindergarten INT, " +
                    "phones INT, deposit INT, food INT, other_expenses INT, total_expenses INT)";
            statement.execute(createTable);
            try { // Checking if it's already new month, creating new column
                statement.execute(actualMonth);
            } catch (SQLException ex) { // If database exists, reading from database
                String readFromDatabase = "SELECT * FROM my_financial_app WHERE current_month = '" + currentMonthAndYear() + "'";
                ResultSet rs = statement.executeQuery(readFromDatabase);
                while (rs.next()) {
                    expenses.setBudget(rs.getInt("user_budget"));
                    expenses.setApartmentLeasing(rs.getInt("apartment_leasing"));
                    expenses.setApartmentBill(rs.getInt("apartment_bill"));
                    expenses.setCarLeasing(rs.getInt("car_leasing"));
                    expenses.setCarCasco(rs.getInt("car_casco"));
                    expenses.setCarInsurance(rs.getInt("car_insurance"));
                    expenses.setGas(rs.getInt("gas"));
                    expenses.setElectricity(rs.getInt("electricity"));
                    expenses.setInternet(rs.getInt("internet"));
                    expenses.setKindergarten(rs.getInt("kindergarten"));
                    expenses.setPhones(rs.getInt("phones"));
                    expenses.setDeposit(rs.getInt("deposit"));
                    expenses.setFood(rs.getInt("food"));
                    expenses.setOtherExpenses(rs.getInt("other_expenses"));
                    expenses.setTotalExpensesForMonth(rs.getInt("total_expenses"));
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToDatabase(Expenses expenses) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String updateQuery = "UPDATE my_financial_app SET user_budget = " + expenses.getBudget() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET apartment_leasing = " + expenses.getApartmentLeasing() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET apartment_bill = " + expenses.getApartmentBill() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET car_leasing = " + expenses.getCarLeasing() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET car_casco = " + expenses.getCarCasco() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET car_insurance = " + expenses.getCarInsurance() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET gas = " + expenses.getGas() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET electricity = " + expenses.getElectricity() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET internet = " + expenses.getInternet() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET kindergarten = " + expenses.getKindergarten() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET phones = " + expenses.getPhones() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET deposit = " + expenses.getDeposit() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET food = " + expenses.getFood() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET other_expenses = " + expenses.getOtherExpenses() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
            updateQuery = "UPDATE my_financial_app SET total_expenses = " + expenses.getTotalExpensesForMonth() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(updateQuery);
        } catch (SQLException ex) {
            System.err.println("SQL Save data ex : " + ex);
        }
    }

    public static void readTableDataFromDatabase(DefaultTableModel tableModel) {
        String currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance, gas,
                electricity, internet, kindergarten, phones, deposit, food, otherExpenses, totalExpenses;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sqlRequest = "SELECT * FROM my_financial_app";
            ResultSet rs = statement.executeQuery(sqlRequest);
            while (rs.next()) {
                currentMonthAndYear = rs.getString("current_month");
                apartmantLeasing = rs.getString("apartment_leasing");
                apartmentBill = rs.getString("apartment_bill");
                carLeasing = rs.getString("car_leasing");
                carCasco = rs.getString("car_casco");
                carInsurance = rs.getString("car_insurance");
                gas = rs.getString("gas");
                electricity = rs.getString("electricity");
                internet = rs.getString("internet");
                kindergarten = rs.getString("kindergarten");
                phones = rs.getString("phones");
                deposit = rs.getString("deposit");
                food = rs.getString("food");
                otherExpenses = rs.getString("other_expenses");
                totalExpenses = rs.getString("total_expenses");

                tableModel.addRow(new Object[]{currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance,
                        gas, electricity, internet, kindergarten, phones, deposit, food, otherExpenses, totalExpenses});
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("SQLException e : " + e);
        }
    }
}
