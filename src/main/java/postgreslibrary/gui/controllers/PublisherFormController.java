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
import postgreslibrary.model.controllers.PublisherController;
import postgreslibrary.model.dao.PublisherDAO;
import postgreslibrary.model.entities.Publisher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PublisherFormController implements Initializable, DataChangeListener {

    // Controllers //
    private PublisherController publisherController = new PublisherController(new PublisherDAO());
    private MainViewController mvc = new MainViewController();
    
    private Publisher publisher = new Publisher();

    // FXML for exclusive use in edit mode //
    @FXML
    private ListView<Publisher> publisherListView = new ListView<>();

    @FXML
    private Button btUpdate;

    // Objects for common use //
    @FXML
    private Label id;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUrl;

    @FXML
    private Button btCancel;

    // FXML for exclusive use in save mode //
    @FXML
    private Button btSave;

    private List<TextField> textFields() {
        List<TextField> txtField = new ArrayList<>();
        txtField.add(txtName);
        txtField.add(txtUrl);
        return txtField;
    }

    public void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
    }

    // Save button action //
    public void onBtSaveAction(){
        // Checking if the fields are empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())){
            publisher.setName(txtName.getText());
            publisher.setUrl(txtUrl.getText());
        }
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure to submit this entry?\n" +
                        "Name: " + publisher.getName() + "\n" + "URL: "
                        + publisher.getUrl()).get() == ButtonType.OK) {
            publisherController.savePublisher(publisher);
        }
    }

    // Update button action //
    public void onBtUpdateAction(){
        // Checking if fields are empty //
        if (Constraints.areAllTextFieldsEmpty(textFields())){
            publisher.setName(txtName.getText());
            publisher.setUrl(txtUrl.getText());
        }
        publisher.setId(Utils.tryParseToInt(id.getText()));
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure to update this entry?\n" +
                        "Name: " + publisher.getName() + "\n" + "URL: "
                        + publisher.getUrl()).get() == ButtonType.OK) {
            publisherController.updatePublisher(publisher);
            mvc.refreshTable();
        }
    }

    // Cancel button action //
    public void onBtCancelAction(){
        Stage stage = (Stage)btCancel.getScene().getWindow();
        stage.close();
    }

    public void loadPublishers(){
        List<Publisher> publishers = publisherController.findAll();
        ObservableList<Publisher> publisherList = FXCollections.observableArrayList();
        // Populating publisherList with Publishers //
        for(Publisher p: publishers){
            publisherList.add(p);
        }
        publisherListView.setItems(publisherList);
        // If an object is selected in ListView //
        publisherListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue == null) return;
                    id.setText(newValue.getId().toString());
                    txtName.setText(newValue.getName());
                    txtUrl.setText(newValue.getUrl());
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading publishers //
        loadPublishers();
        // Adding listener to View //
        publisherController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadPublishers();
    }

}