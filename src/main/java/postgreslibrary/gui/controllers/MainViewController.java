package postgreslibrary.gui.controllers;

import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.gui.utils.Utils;
import postgreslibrary.gui.wrappers.BookWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import postgreslibrary.model.controllers.*;
import postgreslibrary.model.dao.*;
import postgreslibrary.model.entities.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    // Controller //
    private final BookController bookController = new BookController(new BookDAO(), new BookAuthorDAO());

    public MainViewController() {
        
    }

    // Add menu context //
    @FXML
    private MenuItem addAuthor;

    @FXML
    private MenuItem addBook;

    @FXML
    private MenuItem addPublisher;

    // Edit menu context //

    @FXML
    private MenuItem editAuthor;

    @FXML
    private MenuItem editBook;

    @FXML
    private MenuItem editPublisher;

    // Delete menu context //

    @FXML
    private MenuItem deleteAuthor;

    @FXML
    private MenuItem deleteBook;

    @FXML
    private MenuItem deletePublisher;

    @FXML
    private MenuItem aboutMenu;

    @FXML
    private MenuItem refreshTable;

    // Book table context //
    @FXML
    public TableView<BookWrapper> bookTable = new TableView<>();

    @FXML
    private TableColumn<BookWrapper, String> nameColumn = new TableColumn<>("Book Title");
    
    @FXML
    private TableColumn<BookWrapper, String> isbnColumn = new TableColumn<>("ISBN");

    @FXML
    private TableColumn<BookWrapper, Double> priceColumn = new TableColumn<>("Price");

    @FXML
    private TableColumn<BookWrapper, Integer> seqNoColumn = new TableColumn<>("Sequence");

    @FXML
    private TableColumn<BookWrapper, String> authorColumn = new TableColumn<>("Author");

    @FXML
    private TableColumn<BookWrapper, String> publisherColumn = new TableColumn<>("Publisher");

    // TableView list //
    ObservableList<BookWrapper> tableData = FXCollections.observableArrayList();

    public void loadTableView(){
        List<Book> books = bookController.findAllBooks();
        List<BookAuthor> bookAuthors = bookController.findAllBookAuthors();
        // Creating wrapper object //
        for(Book b: books){
            for(BookAuthor ba: bookAuthors){
                if(ba.getId().getIsbn().equals(b.getIsbn())){
                    BookWrapper bookWrapper = new BookWrapper();
                    bookWrapper.setBookTitle(b.getTitle());
                    bookWrapper.setBookIsbn(b.getIsbn());
                    bookWrapper.setBookPrice(b.getPrice());
                    bookWrapper.setBookPublisher(b.getPublisher().getName());
                    bookWrapper.setBookSeqNo(ba.getSeqNo());
                    bookWrapper.setBookAuthor(ba.getAuthor().getName());
                    // Adding BookWrapper to list //
                    tableData.add(bookWrapper);
                }
            }
        }
        // Setting tableData as default list //
        bookTable.setItems(tableData);
        // Sort table by Book Title column //
        bookTable.getSortOrder().add(nameColumn);
    }

    public void refreshTable() {
        tableData.clear();
        bookTable.refresh();
        loadTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setting the Book Title column //
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        nameColumn.setReorderable(false);
        nameColumn.setResizable(false);
        // Setting the ISBN column //
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bookIsbn"));
        isbnColumn.setReorderable(false);
        isbnColumn.setResizable(false);
        Utils.formatIsbnColumn(isbnColumn);
        // Setting the Price column //
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        priceColumn.setReorderable(false);
        priceColumn.setResizable(false);
        // Formatting Price column //
        Utils.formatTableColumnDouble(priceColumn, 2);
        // Setting the Sequence column //
        seqNoColumn.setCellValueFactory(new PropertyValueFactory<>("bookSeqNo"));
        seqNoColumn.setReorderable(false);
        seqNoColumn.setResizable(false);
        // Setting the Author column //
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        authorColumn.setReorderable(false);
        authorColumn.setResizable(false);
        // Setting the Publisher column //
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("bookPublisher"));
        publisherColumn.setReorderable(false);
        publisherColumn.setResizable(true);
        // Loading the tableView//
        loadTableView();
    }

    // Opens create author window //
    @FXML
    public void onCreateAuthorAction(){
        Utils.loadExternalWindow("src/main/java/postgreslibrary/gui/views/AuthorCreate.fxml",
            "Create author");
    }
    // Opens create book window //
    @FXML
    public void onCreateBookAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/postgreslibrary/gui/views/BookCreate.fxml"));
            Parent root = loader.load();
            BookFormController bfc = loader.getController();
            bfc.setMainViewController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Create book");
            stage.show();
        } 
        catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), AlertType.ERROR);
        }
    }
    // Opens create publisher window //
    @FXML
    public void onCreatePublisherAction(){
        Utils.loadExternalWindow("src/main/java/postgreslibrary/gui/views/PublisherCreate.fxml", 
            "Create publisher");
    }
    // Opens edit author window //
    @FXML
    public void onEditAuthorAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/postgreslibrary/gui/views/AuthorEdit.fxml"));
            Parent root = loader.load();
            AuthorFormController afc = loader.getController();
            afc.setMainViewController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Edit author");
            stage.show();
        } 
        catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), AlertType.ERROR);
        }
    }
    // Opens edit book window //
    @FXML
    public void onEditBookAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/postgreslibrary/gui/views/BookEdit.fxml"));
            Parent root = loader.load();
            BookFormController bfc = loader.getController();
            bfc.setMainViewController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Edit book");
            stage.show();
        } 
        catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), AlertType.ERROR);
        }
    }
    // Opens edit publisher window //
    @FXML
    public void onEditPublisherAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/postgreslibrary/gui/views/PublisherEdit.fxml"));
            Parent root = loader.load();
            BookFormController bfc = loader.getController();
            bfc.setMainViewController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Edit publisher");
            stage.show();
        } 
        catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), AlertType.ERROR);
        }
    }
    // Opens delete author window //
    @FXML
    public void onDelAuthorAction(ActionEvent evt){
        Utils.loadExternalWindow("src/main/java/postgreslibrary/gui/views/AuthorDel.fxml", 
            "Delete author");
    }
    // Opens delete book window //
    @FXML
    public void onDelBookAction(ActionEvent evt){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/postgreslibrary/gui/views/BookDel.fxml"));
            Parent root = loader.load();
            BookDelController bdc = loader.getController();
            bdc.setMainViewController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Edit book");
            stage.show();
        } 
        catch (IOException e) {
            Alerts.showAlert(null, "Error in opening window:", e.getMessage(), AlertType.ERROR);
        }
    }
    // Opens delete publisher window //
    @FXML
    public void onDelPublisherAction(ActionEvent evt){
        Utils.loadExternalWindow("src/main/java/postgreslibrary/gui/views/PublisherDel.fxml", 
            "Delete publisher");
    }

    @FXML
    public void onAboutMenuAction(ActionEvent evt){
        Alerts.showAlert(null, null, "Public Library DB - version 1.00\n" +
                "\nCreated by Marcello G. Costa - 2023", AlertType.INFORMATION);
    }

    @FXML
    public void onRefreshTableAction(){
        refreshTable();
    }

}