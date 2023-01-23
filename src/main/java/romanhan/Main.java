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

 /*
 // Start program from file

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
  */
}