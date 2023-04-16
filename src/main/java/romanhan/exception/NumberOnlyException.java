package romanhan.exception;

import javax.swing.*;

public class NumberOnlyException extends NumberFormatException {
    public NumberOnlyException(JFrame jFrame) {
        JOptionPane.showMessageDialog(jFrame, "You can only enter positive digits", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
