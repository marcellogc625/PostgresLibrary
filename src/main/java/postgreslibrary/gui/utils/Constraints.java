package postgreslibrary.gui.utils;

import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Constraints {
    // Makes TextField to only accepts Integer values //
    public static void setTextFieldInteger(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && !newValue.matches("\\d*")){
                txt.setText(newValue);
            }
        });
    }
    // Makes TextField to only accepts Double values //
    public static void setTextFieldDouble(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && !newValue.matches("\\d*([.]\\d*)?")){
                txt.setText(oldValue);
            }
        });
    }
    // Automatically adds a point to TextField (Double values only) //
    public static void addDecimalMask(TextField txt){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*([.]\\d*)?")){
                txt.setText(oldValue);
            }
        });
    }
    // Set TextField max length //
    public static void setTextFieldMaxLength(TextField txt, Integer max){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.length() > max){
                txt.setText(oldValue);
            }
        }); 
    }
    // Set TextField min length //
    public static void addMinLengthChecker(TextField txt, Integer min){
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue.length() < min){
                Alerts.showAlert("Warning", null, "" +
                "The input must have at least " + min + " characters.", AlertType.WARNING);
            }
        });
    }
    // Check if TextFields are empty //
    public static boolean areAllTextFieldsEmpty(List<TextField> txtFields){
        for(TextField txt: txtFields){
            if(txt.getText().isEmpty() || txt.getText() == null){
                Alerts.showAlert("Warning", "Empty field", "Fill all the fields.", AlertType.WARNING);
                return false;
            }
        }
        return true;
    }

}
