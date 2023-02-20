package postgreslibrary.gui.utils;

import java.util.Locale;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class Utils {
    // Tries to convert a String value to an Integer value //
    public static Integer tryParseToInt(String value){
        try{
            return Integer.parseInt(value);
        }
        catch(NumberFormatException e) {
            return null;
        }
    }
    // Tries to convert a String value to an Double value //
    public static Double tryParseToDouble(String value){
        try{
            return Double.parseDouble(value);
        }
        catch(NumberFormatException e){
            return null;
        }
    }
    // Format Double values to TableColumn //
    public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, Integer decimalPlaces){
        tableColumn.setCellFactory(column -> {
            TableCell<T, Double> cell = new TableCell<T, Double>(){
                @Override
                protected void updateItem(Double item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setText(null);
                    }
                    else{
                        Locale.setDefault(Locale.US);
                        setText(String.format("%." + decimalPlaces + "f", item));
                    }
                }
            };
            return cell;
        });
    }

}