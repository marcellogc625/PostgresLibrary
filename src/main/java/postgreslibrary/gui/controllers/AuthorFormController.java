package postgreslibrary.gui.controllers;

import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.gui.utils.Constraints;
import postgreslibrary.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import postgreslibrary.model.controllers.AuthorController;
import postgreslibrary.model.dao.AuthorDAO;
import postgreslibrary.model.entities.Author;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorFormController implements Initializable, DataChangeListener {

    // Controllers //
    private AuthorController authorController = new AuthorController(new AuthorDAO());
    private MainViewController mvc = new MainViewController();

    private Author author = new Author();

    // List view to exclusive use in edit mode //
    @FXML
    private ListView<Author> authorListView = new ListView<>();

    // Objects for common use //
    @FXML
    private Label id;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtFname;

    @FXML
    private Button btSave;

    @FXML
    private Button btUpdate;

    @FXML
    private Button btCancel;

    public void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
    }

    public List<TextField> textFields(){
        List<TextField> txt = new ArrayList<>();
        txt.add(txtName);
        txt.add(txtFname);
        return txt;
    }

    public void onBtSaveAction(){
        // Checking if fields is empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())){
            author.setName(txtName.getText());
            author.setFullName(txtFname.getText());
        }
        // Asking if the user confirms the new entry //
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure to submit this entry?\n" +
                        "Name: " + author.getName() + "\n" + "Full name: "
                        + author.getFullName()).get() == ButtonType.OK) {
            authorController.saveAuthor(author);
        }
    }

    public void onBtUpdateAction(){
        // Checking if fields is empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())){
            author.setName(txtName.getText());
            author.setFullName(txtFname.getText());
        }
        author.setId(Utils.tryParseToInt(id.getText()));
        // Asking if the user confirms the update //
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure to update this entry?\n" +
                        "Name: " + author.getName() + "\n" + "Full name: "
                        + author.getFullName()).get() == ButtonType.OK) {
            authorController.updateAuthor(author);
            mvc.refreshTable();
        }

    }

    public void onBtCancelAction(){
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    public void loadAuthors(){
        // Populating ListView with authors //
        List<Author> authors = authorController.findAll();
        ObservableList<Author> authorList = FXCollections.observableArrayList();
        for(Author a: authors){
            authorList.add(a);
        }
        authorListView.setItems(authorList);
        // If an object is selected in ListView //
        authorListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue == null) return;
                    id.setText(newValue.getId().toString());
                    txtName.setText(newValue.getName());
                    txtFname.setText(newValue.getFullName());
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading the authors //
        loadAuthors();
        // Verify if authors have changes //
        authorController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadAuthors();
    }
}