package romanhan;

import romanhan.controller.Expenses;
import romanhan.model.User;
import romanhan.view.View;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Expenses expenses = new Expenses(user);

        File file = new File("C:\\tmp\\userData.ser");
        if (file.exists()) {
            User user1;
            Expenses expenses1;

            try (FileInputStream fileIn = new FileInputStream("C:\\tmp\\userData.ser");
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                user1 = (User) in.readObject();
                expenses1 = (Expenses) in.readObject();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("UserData class not found");
                c.printStackTrace();
                return;
            }
            View view = new View(expenses1, user1);
            view.addComponentsToPane();
        } else {
            View view = new View(expenses, user);
            view.addComponentsToPane();
        }
    }
}