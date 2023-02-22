package postgreslibrary.gui.controllers;

import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Constraints;
import postgreslibrary.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import postgreslibrary.model.controllers.*;
import postgreslibrary.model.dao.*;
import postgreslibrary.model.entities.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookFormController implements Initializable, DataChangeListener {

    // Entities controllers //
    private final AuthorController authorController = new AuthorController(new AuthorDAO());
    private final BookController bookController = new BookController(new BookDAO(), new BookAuthorDAO());
    private final PublisherController publisherController = new PublisherController(new PublisherDAO());
    // Entities //
    private Book book = new Book();
    private BookAuthor bookAuthor = new BookAuthor();
    // MainView controller //
    private MainViewController mvc = new MainViewController();
    // Listener object //
    public final List<DataChangeListener> dataChangeListeners = new ArrayList<>();
    // FXML //
    @FXML
    private ListView<Book> bookListView = new ListView<>();

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<Author> comboBoxAuthors;

    @FXML
    private ComboBox<Publisher>  comboBoxPublishers;

    @FXML
    private TextField txtSeqNo;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;

    @FXML
    private Button btCancel;

    private void notifyDataChangeListeners() {
        for (DataChangeListener dcl : dataChangeListeners) {
            dcl.onDataChanged();
        }
    }

    public List<TextField> textFields(){
        List<TextField> txt = new ArrayList<>();
        txt.add(txtName);
        txt.add(txtIsbn);
        txt.add(txtPrice);
        txt.add(txtSeqNo);
        return txt;
    }

    public void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
    }

    public void onBtSaveAction(){
        // Checking if the fields are empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())) {
            // Constraint to define minimum length of TextField //
            Constraints.addMinLengthChecker(txtIsbn, 13);

            String title = txtName.getText();
            String isbn = txtIsbn.getText();
            Double price = Utils.tryParseToDouble(txtPrice.getText());
            Author author = comboBoxAuthors.getSelectionModel().getSelectedItem();
            Publisher publisher = comboBoxPublishers.getSelectionModel().getSelectedItem();
            Integer seqNo = Utils.tryParseToInt(txtSeqNo.getText());

            // Setting book object attributes //
            book.setTitle(title);
            book.setIsbn(isbn);
            book.setPrice(price);
            book.setPublisher(publisher);
            // Setting bookAuthor object attributes //
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setId(new BookAuthorId());
            bookAuthor.getId().setIsbn(isbn);
            bookAuthor.getId().setAuthor_id(author.getId());
            bookAuthor.setAuthor(author);
            bookAuthor.setBook(book);
            bookAuthor.setSeqNo(seqNo);
            // Saving objects  //
            bookController.saveBook(book, bookAuthor);
            notifyDataChangeListeners();
            mvc.refreshTable();
        }

    }

    public void onBtUpdateAction(){
        // Checking if the fields are empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())) {
            // Constraint to define minimum length of TextField //
            Constraints.addMinLengthChecker(txtIsbn, 13);
            
            String title = txtName.getText();
            String isbn = txtIsbn.getText();
            Double price = Utils.tryParseToDouble(txtPrice.getText());
            Author author = comboBoxAuthors.getSelectionModel().getSelectedItem();
            Publisher publisher = comboBoxPublishers.getSelectionModel().getSelectedItem();
            Integer seqNo = Utils.tryParseToInt(txtSeqNo.getText());

            // Setting book object //
            for(BookAuthor ba: bookController.findAllBookAuthors()){
                if(ba.getId().getIsbn().equals(isbn)){
                    book = bookListView.getSelectionModel().getSelectedItem();
                    book.setTitle(title);
                    book.setPrice(price);
                    book.setPublisher(publisher);
                    // Setting bookAuthor object //
                    bookAuthor.setId(ba.getId());
                    bookAuthor.getId().setIsbn(isbn);
                    bookAuthor.getId().setAuthor_id(author.getId());
                    bookAuthor.setAuthor(author);
                    bookAuthor.setBook(book);
                    bookAuthor.setSeqNo(seqNo);
                }
            }
            // Update book data //
            bookController.updateBook(book, bookAuthor);
            notifyDataChangeListeners();
            mvc.refreshTable();
        }
    }

    public void onBtCancelAction(){
        Stage stage = (Stage)btCancel.getScene().getWindow();
        stage.close();
    }

    public void loadBooks() {
        List<Book> books = bookController.findAllBooks();
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        // Populating bookList and setting in ListView //
        for (Book b : books) {
            bookList.add(b);
        }
        bookListView.setItems(bookList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        // Constraints to define max length of TextFields //
        Constraints.setTextFieldMaxLength(txtIsbn, 13);
        Constraints.setTextFieldMaxLength(txtPrice, 12);
        Constraints.setTextFieldMaxLength(txtSeqNo, 3);
        // Constraints to set TextField only accepts double values //
        Constraints.setTextFieldDouble(txtPrice);
        // Constraints to set TextField only accepts Integer values
        Constraints.setTextFieldInteger(txtSeqNo);
        Constraints.setTextFieldInteger(txtIsbn);
        Constraints.addDecimalMask(txtPrice);

        // Loading comboBoxes //
        List<Author> authors = authorController.findAll();
        List<Publisher> publishers = publisherController.findAll();
        ObservableList<Author> authorList = FXCollections.observableArrayList();
        ObservableList<Publisher> publisherList = FXCollections.observableArrayList();

        // Populating authorList and setting in ComboBox //
        for(Author a: authors){
            authorList.add(a);
        }
        comboBoxAuthors.setItems(authorList);
        // Populating publisherList and setting in ComboBox //
        for(Publisher p: publishers){
            publisherList.add(p);
        }
        comboBoxPublishers.setItems(publisherList);

        // Checking if bookListView exists //
        if(bookListView != null) {
            loadBooks();
            // If a book is selected in bookListView /
            bookListView.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if(newValue == null) return;
                        txtName.setText(newValue.getTitle());
                        txtIsbn.setText(newValue.getIsbn());
                        txtIsbn.setEditable(false);
                        txtIsbn.setDisable(true);
                        txtPrice.setText(newValue.getPrice().toString());
                        comboBoxPublishers.setValue(newValue.getPublisher());
                        // for-each loop to set Author and SeqNo //
                        for(BookAuthor ba: bookController.findAllBookAuthors()){
                            if(newValue.getIsbn().equals(ba.getId().getIsbn())){
                                comboBoxAuthors.setValue(ba.getAuthor());
                                txtSeqNo.setText(ba.getSeqNo().toString());
                            }
                        }
                    });
        }
        // Adding listener //
        bookController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadBooks();
    }
}