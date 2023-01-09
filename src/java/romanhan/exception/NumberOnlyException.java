package romanhan.exception;

import javax.swing.*;

public class NumberOnlyException extends Exception {
    public NumberOnlyException(JFrame jFrame) {
        super();
        JOptionPane.showMessageDialog(jFrame, "Можно вводить только целые числа", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
