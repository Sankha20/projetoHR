package General;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class Tools {

    public static boolean confirm(String msg) {
        int response;
        response = JOptionPane.showConfirmDialog(null, msg, "Confirmar", JOptionPane.YES_NO_OPTION);

        return (response == 0);
    }

    public static void alert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Então...", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Ops...", JOptionPane.ERROR_MESSAGE);
    }

    public static int parseInt(String src) {
        int result = -1;

        try {
            result = Integer.parseInt(src);
        } catch (NumberFormatException e) {
            System.out.println("Tools > parseInt.\n" + e);
        }

        return result;
    }

    public static BigDecimal parseMoney(String s) {
        BigDecimal d = new BigDecimal(-1);

        try {
            d = new BigDecimal(s);
        } catch (NumberFormatException e) {
            System.out.println("Tools > parseMoney.\n" + e);
        }

        return d;
    }

    public static String moneyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    public static String parseNumber(String s) {
        s = s.replaceAll(",", ".");

        if (!s.matches("[0-9.,]+")) {
            s = s.replaceAll("\\D+", "");

            int stringLength = s.length();
            int minusTwo = stringLength - 2;

            if (minusTwo > 0) {
                s = s.substring(0, minusTwo);
            }
        }

        return s;
    }
}
