package romanhan.exception;

import javax.swing.*;

public class NotEnoughBalance extends Throwable {
    public NotEnoughBalance() {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                "Не достаточный баланс", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
