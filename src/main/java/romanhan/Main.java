package romanhan;

import romanhan.controller.Expenses;
import romanhan.database.H2Database;
import romanhan.model.User;
import romanhan.view.View;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Expenses expenses = new Expenses(user);

        H2Database.startWithDatabase(expenses);

        View view = new View(expenses);
        view.addComponentsToPane();
    }
}