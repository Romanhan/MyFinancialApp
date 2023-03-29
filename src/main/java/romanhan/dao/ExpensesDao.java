package romanhan.dao;

import org.hibernate.Session;
import romanhan.entity.Expenses;
import romanhan.util.HibernateUtil;

import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.List;

public class ExpensesDao {
    public static void saveExpenses(Expenses expenses) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(expenses);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Expenses getExpenses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Expenses.class, currentMonthAndYear());
        }
    }

    public static void getExpensesTable(DefaultTableModel tableModel) { // Get all data from database and put it to table window
        String currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance, gas,
                electricity, internet, kindergarten, phones, deposit, food, otherExpenses, totalExpenses;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Expenses> expensesList = session.createQuery("from Expenses", Expenses.class).list();

            for (Expenses oneObj : expensesList) {
                currentMonthAndYear = oneObj.getId();
                apartmantLeasing = String.valueOf(oneObj.getApartmentLeasing());
                apartmentBill = String.valueOf(oneObj.getApartmentBill());
                carLeasing = String.valueOf(oneObj.getCarLeasing());
                carCasco = String.valueOf(oneObj.getCarCasco());
                carInsurance = String.valueOf(oneObj.getCarInsurance());
                gas = String.valueOf(oneObj.getGas());
                electricity = String.valueOf(oneObj.getElectricity());
                internet = String.valueOf(oneObj.getInternet());
                kindergarten = String.valueOf(oneObj.getKindergarten());
                phones = String.valueOf(oneObj.getPhones());
                deposit = String.valueOf(oneObj.getDeposit());
                food = String.valueOf(oneObj.getFood());
                otherExpenses = String.valueOf(oneObj.getOtherExpenses());
                totalExpenses = String.valueOf(oneObj.getTotalExpensesForMonth());
                tableModel.addRow(new Object[]{currentMonthAndYear, apartmantLeasing, apartmentBill, carLeasing, carCasco, carInsurance,
                        gas, electricity, internet, kindergarten, phones, deposit, food, otherExpenses, totalExpenses});
            }

        }
    }

    public static String[] getMonthName() { // Write month name on russian in Frame
        return new String[]{"January", "February", "March", "April", "Nay", "June", "Juli",
                "August", "September", "October", "November", "December"};
    }

    public static String currentMonthAndYear() {
        Calendar cal = Calendar.getInstance();
        String currentMonth = getMonthName()[cal.get(Calendar.MONTH)];
        int currentYear = cal.get(Calendar.YEAR);
        return currentMonth + " " + currentYear;
    }
}
