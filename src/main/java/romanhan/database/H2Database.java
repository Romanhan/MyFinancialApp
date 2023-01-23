package romanhan.database;

import romanhan.controller.Expenses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    public static void startWithDatabase(Expenses expenses) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement statement = connection.createStatement()) {
            try { // If database not exists, create
                System.out.println("Creating database...");
                String createTable = "CREATE TABLE my_financial_app (current_month VARCHAR(15) UNIQUE, user_budget INT, apartment_leasing INT, apartment_bill INT, " +
                        "car_leasing INT, car_casco INT, car_insurance INT, gas INT, electricity INT, internet INT, kindergarten INT, " +
                        "phones INT, deposit INT, food INT, total_expenses INT)";
                statement.execute(createTable);
                System.out.println("Database created.");
                String month = "INSERT INTO my_financial_app (current_month) VALUES ('" + currentMonthAndYear() + "')";
                statement.execute(month);
                System.out.println("Month inserted");
            } catch (SQLException exception) {
                // If database exists, read all data from it
                try { // Checking if it's already new month, creating new column
                    String month = "INSERT INTO my_financial_app (current_month) VALUES ('" + currentMonthAndYear() + "')";
                    statement.execute(month);
                    System.out.println("New month Inserted");
                } catch (SQLException ex) { // If not, reading current month
                    System.out.println("Reading data from database...");
                    String startStatement = "SELECT * FROM my_financial_app WHERE current_month = '" + currentMonthAndYear() + "'";
                    ResultSet resultSet = statement.executeQuery(startStatement);
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
            }
        } catch (SQLException exception) {
            System.err.println("SQLException : " + exception);
        }
    }

    public static void saveToDatabase(Expenses expenses) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement statement = connection.createStatement()) {
            String query = "UPDATE my_financial_app SET user_budget = " + expenses.getBudget() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET apartment_leasing = " + expenses.getApartmentLeasing() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET apartment_bill = " + expenses.getApartmentBill() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_leasing = " + expenses.getCarLeasing() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_casco = " + expenses.getCarCasco() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET car_insurance = " + expenses.getCarInsurance() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET gas = " + expenses.getGas() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET electricity = " + expenses.getElectricity() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET internet = " + expenses.getInternet() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET kindergarten = " + expenses.getKindergarten() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET phones = " + expenses.getPhones() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET deposit = " + expenses.getDeposit() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET food = " + expenses.getFood() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            query = "UPDATE my_financial_app SET total_expenses = " + expenses.getTotalExpensesForMonth() + " WHERE current_month = '" + currentMonthAndYear() + "'";
            statement.executeUpdate(query);
            System.out.println("Data saved.");
        } catch (SQLException ex) {
            System.err.println("SQL Save data Exception : " + ex);
        }
    }

    public static void readFromDatabase() {
        String[] columnNames = {"Дата", "Лизинг квартиры", "Счета за квартиру",
                "Лизинг машины", "Каско машины", "Страховка машины", "Бензин", "Электричество", "Интернет", "Садик",
                "Телефоны", "Никитин депозит", "Еда", "Всего потрачено"};

        JFrame jFrame = new JFrame("Database Search Result");
        jFrame.setLayout(new BorderLayout());
        jFrame.setBounds(500, 220, 1200, 400);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);

        JTable jTable = new JTable();
        jTable.setModel(tableModel);

        // Set column width
        jTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(145);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(9).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(10).setPreferredWidth(90);
        jTable.getColumnModel().getColumn(11).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(12).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(13).setPreferredWidth(150);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(jTable.getBounds());

        jFrame.add(jScrollPane);
        jFrame.setVisible(true);

        String currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance, gas,
                electricity, internet, kindergarten, phones, deposit, food, totalExpenses;

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
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
                totalExpenses = rs.getString("total_expenses");
                tableModel.addRow(new Object[]{currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance,
                        gas, electricity, internet, kindergarten, phones, deposit, food, totalExpenses});
            }
        } catch (SQLException ex) {
            System.err.println("SQLException : " + ex);
        }
    }
}
