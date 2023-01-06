package romanhan.exception;

import javax.swing.*;

public class NotEnoughBalance extends Throwable {
    public NotEnoughBalance(JFrame jFrame) {
        JOptionPane.showMessageDialog(jFrame,"Не достаточный баланс", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
