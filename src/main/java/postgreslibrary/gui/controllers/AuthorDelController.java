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
import postgreslibrary.model.controllers.AuthorController;
import postgreslibrary.model.dao.AuthorDAO;
import postgreslibrary.model.entities.Author;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorDelController implements Initializable, DataChangeListener {

    private AuthorController authorController = new AuthorController(new AuthorDAO());

    @FXML
    private ListView<Author> authorListView = new ListView<>();

    @FXML
    private Button btCancel;

    @FXML
    private Button btDelete;

    public void onBtCancelAction(){
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    public void onBtDeleteAction(){
        if(authorListView.getSelectionModel().getSelectedItem() == null){
            Alerts.showAlert("Attention", null, "You first need select an author",
                    Alert.AlertType.WARNING);
        }
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure to delete this entry?").get() == ButtonType.OK) {
            authorController.deleteAuthor(authorListView.getSelectionModel().getSelectedItem().getId());
        }
    }

    public void loadAuthors(){
        List<Author> authors = authorController.findAll();
        ObservableList<Author> authorList = FXCollections.observableArrayList();
        // Populating authorList //
        for(Author a: authors){
            authorList.add(a);
        }
        authorListView.setItems(authorList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading the authors //
        loadAuthors();
        // Verify authors //
        authorController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadAuthors();
    }
}
