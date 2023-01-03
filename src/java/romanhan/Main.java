package romanhan;

import romanhan.controller.Expenses;
import romanhan.model.User;
import romanhan.view.View;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Expenses expenses = new Expenses(user);
        Runnable startApp = () -> {
            View view = new View(expenses, user);
            view.addComponentsToPane();
        };
        Thread newThread = new Thread(startApp);
        newThread.start();
    }
}