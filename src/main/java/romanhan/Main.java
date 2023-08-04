package romanhan;

import romanhan.dao.ExpensesDao;
import romanhan.entity.Expenses;
import romanhan.view.View;

public class Main {
    public static void main(String[] args) {
        Expenses expenses = Expenses.getExpenses();
        View view;
        try {
            view = new View(ExpensesDao.getExpenses());
            view.addComponentsToPane();
        } catch (Exception ex) {
            expenses.clearAllData();
            view = new View(expenses);
            view.addComponentsToPane();
        }
    }
}