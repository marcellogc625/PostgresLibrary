package postgreslibrary.gui.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import postgreslibrary.model.entities.Author;
import postgreslibrary.model.entities.Book;
import postgreslibrary.model.entities.Publisher;

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
    public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, Integer decimalPlaces) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Double> cell = new TableCell<T, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } 
                    else {
                        Locale.setDefault(Locale.US);
                        setText(String.format("%." + decimalPlaces + "f", item));
                    }
                }
            };
            return cell;
        });
    }
    // Auxiliary method to formatIsbnColumn //
    private static String formatIsbn(String isbn) {
        StringBuilder sb = new StringBuilder(isbn);
        sb.insert(12, '-');
        sb.insert(6, '-');
        sb.insert(4, '-');
        sb.insert(3, '-');
        return sb.toString();
    }
    // Format ISBN value to column //
    public static <T> void formatIsbnColumn(TableColumn<T, String> tableColumn) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, String> cell = new TableCell<T, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(formatIsbn(item));
                    }
                }
            };
            return cell;
        });
    }
    // Unformatting ISBN value to DB //
    public static String isbnFieldToString(String isbn) {
        String unformattedIsbn = isbn.replace("-", "");
        return unformattedIsbn;
    }
    // Author comparator //
    public static Comparator<Author> authorComparator() {
        return new Comparator<Author>() {
            @Override
            public int compare(Author a1, Author a2) {
                return a1.getName().compareTo(a2.getName());
            }
        };
    }
    // Book comparator //
    public static Comparator<Book> bookComparator() {
        return new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        };
    }
    // Publisher comparator //
    public static Comparator<Publisher> publisherComparator() {
        return new Comparator<Publisher>() {
            @Override
            public int compare(Publisher p1, Publisher p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
    }
    // Load external window method //
    public static void loadExternalWindow(String path, String titleWindow) {
        try {
            Stage stage = new Stage();
            URL url = Paths.get(path).toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titleWindow);
            stage.show();
        } catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}