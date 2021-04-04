/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.currencyconverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 *
 * @author Gergö Szijarto
 */
public class FXMLDocumentController implements Initializable {

    // declaration of gui-elements
    private Label label;
    @FXML
    private Button btnConvert;
    @FXML
    private TextField tFResult;
    @FXML
    private ComboBox<Currency> cBConvertFrom;
    @FXML
    private ComboBox<Currency> cBConvertTo;
    @FXML
    private MenuItem mIReset;
    @FXML
    private MenuItem mIExit;
    @FXML
    private MenuItem mIAbout;
    @FXML
    public TextField tFAmount;

    Model m = new Model();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // add item to comboboxes
        cBConvertFrom.getItems().addAll(
                Currency.USD,
                Currency.CAD,
                Currency.AUD,
                Currency.GBP,
                Currency.SEK,
                Currency.EUR,
                Currency.CHF,
                Currency.HUF,
                Currency.JPY);

        cBConvertTo.getItems().addAll(
                Currency.USD,
                Currency.CAD,
                Currency.AUD,
                Currency.GBP,
                Currency.SEK,
                Currency.EUR,
                Currency.CHF,
                Currency.HUF,
                Currency.JPY);

    }

    @FXML
    private void convert(ActionEvent event) throws IOException {
        // check if the form is filled out completely 
        if (tFAmount.getText().length() > 0 && cBConvertFrom.getValue() != null && cBConvertTo.getValue() != null) {
            tFResult.setText(m.convert(tFAmount.getText(), cBConvertFrom.getValue(), cBConvertTo.getValue()));
        } else {
            // else return an error message
            m.formNotFull();
        }

    }

    @FXML
    private void reset(ActionEvent event) {
        // resets all components
        tFAmount.setText(null);
        cBConvertFrom.setValue(null);
        cBConvertTo.setValue(null);
        tFResult.setText(null);
    }

    @FXML
    private void exit(ActionEvent event) {
        // exits application when clicked in menu bar
        System.exit(0);
    }

    @FXML
    private void about(ActionEvent event) throws IOException {
        // shows help-page when clicked in menu bar
        m.showPage("Help");
    }

}
