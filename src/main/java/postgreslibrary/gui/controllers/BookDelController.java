package postgreslibrary.gui.controllers;

import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import postgreslibrary.model.controllers.BookController;
import postgreslibrary.model.dao.BookAuthorDAO;
import postgreslibrary.model.dao.BookDAO;
import postgreslibrary.model.entities.Book;
import postgreslibrary.model.entities.BookAuthor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookDelController implements Initializable, DataChangeListener {

    // Controllers //
    private BookController bookController = new BookController(new BookDAO(), new BookAuthorDAO());
    private MainViewController mvc = new MainViewController();

    @FXML
    private ListView<Book> bookListView;

    @FXML
    private Button btCancel;

    @FXML
    private Button btDelete;

    public void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
    }

    public void onBtCancelAction() {
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    public void onBtDeleteAction(){
        try{
            if(!bookListView.getSelectionModel().getSelectedItem().equals(null)){
                String isbn = bookListView.getSelectionModel().getSelectedItem().getIsbn();
                for(BookAuthor ba: bookController.findAllBookAuthors()){
                    if(ba.getId().getIsbn().equals(isbn)){
                        if(Alerts.showConfirmation("Confirm operation", 
                        "Are you sure to delete this entry?").get() == ButtonType.OK){
                            bookController.deleteBook(isbn, ba.getId());
                            mvc.refreshTable();
                        }
                    }
                }
            }
        }
        catch (NullPointerException e) {
            Alerts.showAlert("Attention", null, "You first need select an book.",
                    Alert.AlertType.WARNING);
        }
    }

    public void loadBooks(){
        // Loading ListView //
        List<Book> books = bookController.findAllBooks();
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        // Populating bookList and setting in ListView //
        for (Book b : books) {
            bookList.add(b);
        }
        bookListView.setItems(bookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading the books //
        loadBooks();
        // Verify book changes //
        bookController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadBooks();
    }

}