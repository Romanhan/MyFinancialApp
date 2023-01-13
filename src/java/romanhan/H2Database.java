package romanhan;

import romanhan.controller.Expenses;

import java.sql.*;
import java.util.Calendar;

public class H2Database {
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    public static Connection connectToDatabase() {
        Connection connection;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println("SQL Connect exception : " + exception);
            throw new RuntimeException(exception);
        }
        return connection;
    }

    public static void startWithDatabase(Expenses expenses) {
        try (Statement statement = connectToDatabase().createStatement()) {
            try { // If database not exists, create
<<<<<<< HEAD
=======
                //statement.execute("CREATE TABLE my_financial_app");
                //statement.execute("USE my_financial_app");
>>>>>>> origin/database
                System.out.println("Creating database...");
                String createConnection = "CREATE TABLE my_financial_app (current_month INT, user_budget INT, apartment_leasing INT, apartment_bill INT, " +
                        "car_leasing INT, car_casco INT, car_insurance INT, gas INT, electricity INT, internet INT, kindergarten INT, " +
                        "phones INT, deposit INT, food INT, total_expenses INT)";
                statement.execute(createConnection);
                System.out.println("Database created.");
                int currentMonth = getCurrentMonth();
                String month = "INSERT INTO my_financial_app (current_month) VALUES (" + currentMonth + ")";
                statement.execute(month);
                System.out.println("Month inserted");
            } catch (SQLException exception) {
                // If database exists, read all data from it
                System.out.println("Reading data from database...");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM my_financial_app");
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
            try {
                connectToDatabase().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveToDatabase(Expenses expenses) {
        try (Statement statement = connectToDatabase().createStatement()) {
            int month = getCurrentMonth();

            String query = "UPDATE my_financial_app SET user_budget = " + expenses.getBudget() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET apartment_leasing = " + expenses.getApartmentLeasing() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET apartment_bill = " + expenses.getApartmentBill() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_leasing = " + expenses.getCarLeasing() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_casco = " + expenses.getCarCasco() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_insurance = " + expenses.getCarInsurance() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET gas = " + expenses.getGas() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET electricity = " + expenses.getElectricity() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET internet = " + expenses.getInternet() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET kindergarten = " + expenses.getKindergarten() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET phones = " + expenses.getPhones() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET deposit = " + expenses.getDeposit() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET food = " + expenses.getFood() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET total_expenses = " + expenses.getTotalExpensesForMonth() + " WHERE current_month = " + month;
            statement.executeUpdate(query);
            System.out.println("Data saved.");
        } catch (SQLException e) {
            System.err.println("SQL Save data Exception : " + e);
            try {
                connectToDatabase().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
