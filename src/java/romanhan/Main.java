package romanhan;

import romanhan.controller.Expenses;
import romanhan.model.User;
import romanhan.view.View;

import java.io.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Expenses expenses = new Expenses(user);

        File file = new File("C:\\MyFinancialApp\\" + currentMonthAndYear() + ".ser"); //If file exist, use saved before data
        if (file.exists()) {
            User user1;
            Expenses expenses1;

            try (FileInputStream fileIn = new FileInputStream("C:\\MyFinancialApp\\" + currentMonthAndYear() + ".ser");
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                user1 = (User) in.readObject();
                expenses1 = (Expenses) in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                return;
            }
            View view = new View(expenses1, user1);
            view.addComponentsToPane();
        } else {
            View view = new View(expenses, user);
            view.addComponentsToPane();
        }
    }

    public static String currentMonthAndYear() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        Calendar cal = Calendar.getInstance();
        String currentMonth = monthNames[cal.get(Calendar.MONTH)];
        int currentYear = cal.get(Calendar.YEAR);
        return currentMonth + " " + currentYear;
    }
}