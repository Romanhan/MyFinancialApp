package romanhan.exception;

import javax.swing.*;

public class NumberOnlyException extends NumberFormatException {
    public NumberOnlyException(JFrame jFrame) {
        JOptionPane.showMessageDialog(jFrame, "Можно вводить только целые положительные числа", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
