package pkg1305;

import java.io.IOException;
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

    // method that shows an fxml-page
    public void showPage(String pageNameString) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("FXML" + pageNameString + ".fxml"));
        Parent root1 = (Parent) fXMLLoader.load();
        Stage stage = new Stage();
        stage.setTitle(pageNameString);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    // method that converts the users amount into differect currencies
    public String convert(String amount, Currency firstCurrency, Currency secondCurrency) {
        double toConvert = 0;
        String result;

        try {
            // checking if entry is a number
            toConvert = Double.parseDouble(amount);

            if (firstCurrency == secondCurrency) {
                return amount;
            }

        } catch (NumberFormatException e) {
            // return an error message if the entry is not a number
            noNumber();

        }

        switch (firstCurrency) {
            case CHF:
                switch (secondCurrency) {
                    case USD:
                        result = String.valueOf(toConvert * 1.0734);
                        return result;
                    case EUR:
                        result = String.valueOf(toConvert * 0.9018);
                        return result;
                }
            case EUR:
                switch (secondCurrency) {
                    case USD:
                        result = String.valueOf(toConvert * 1.1846);
                        return result;
                    case CHF:
                        result = String.valueOf(toConvert * 1.0990);
                        return result;
                }
            case USD:
                switch (secondCurrency) {
                    case EUR:
                        result = String.valueOf(toConvert * 0.8361);
                        return result;
                    case CHF:
                        result = String.valueOf(toConvert * 0.9220);
                        return result;
                }

        }
        return null;
    }

    public void noNumber() {
        // pop-up-window that shows when anything but a number got entered
        JOptionPane.showMessageDialog(null,
                "Please enter a number.",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
    }

    public void formNotFull() {
        // pop-up-window that shows when a not complete form gets submitted
        JOptionPane.showMessageDialog(null,
                "Please enter an amount and start your starting- and ending currency to start the conversion.",
                "Warning!",
                JOptionPane.WARNING_MESSAGE);
    }

}
