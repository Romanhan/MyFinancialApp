package romanhan.exception;

import javax.swing.*;

public class NotEnoughBalanceException extends Exception {
    public NotEnoughBalanceException(JFrame jFrame) {
        JOptionPane.showMessageDialog(jFrame, "Не достаточный баланс", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
