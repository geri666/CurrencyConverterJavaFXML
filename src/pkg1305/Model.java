package pkg1305;

import java.io.IOException;
import java.math.BigDecimal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Gergö Szijarto
 */
public class Model {

    // Method that shows an fxml-page
    public void showPage(String pageNameString) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("FXML" + pageNameString + ".fxml"));
        Parent root1 = (Parent) fXMLLoader.load();
        Stage stage = new Stage();
        stage.setTitle(pageNameString);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    // Method that converts the users amount into differect currencies
    public String convert(String amount, Currency firstCurrency, Currency secondCurrency) throws IOException {
        BigDecimal toConvert = new BigDecimal(0);
        String result;

        try {
            // Checking if entry is a number
            toConvert = new BigDecimal(amount);

            if (firstCurrency == secondCurrency) {
                return amount;
            }

        } catch (NumberFormatException e) {
            // Return an error message if the entry is not a number
            noNumber();
        }

        ExchangeRate er = new ExchangeRate(firstCurrency.toString(), secondCurrency.toString());

        return er.convert(toConvert).toString();
    }

    public void noNumber() {
        // Pop-up-window that shows when anything but a number got entered
        JOptionPane.showMessageDialog(null,
                "Please enter a number.",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
    }

    public void formNotFull() {
        // Pop-up-window that shows when a not complete form gets submitted
        JOptionPane.showMessageDialog(null,
                "Please enter an amount and start your starting- and ending currency to start the conversion.",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
    }

}
