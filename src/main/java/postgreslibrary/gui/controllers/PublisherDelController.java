package postgreslibrary.gui.controllers;

import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import postgreslibrary.model.controllers.PublisherController;
import postgreslibrary.model.dao.PublisherDAO;
import postgreslibrary.model.entities.Publisher;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class PublisherDelController implements Initializable, DataChangeListener {

    private PublisherController publisherController = new PublisherController(new PublisherDAO());

    @FXML
    private ListView<Publisher> publisherListView = new ListView<>();

    @FXML
    private Button btCancel;

    @FXML
    private Button btDelete;

    public void onBtCancelAction(){
        Stage stage = (Stage) btCancel.getScene().getWindow();
        stage.close();
    }

    public void onBtDeleteAction(){
        if(publisherListView.getSelectionModel().getSelectedItem() == null){
            Alerts.showAlert("Attention", null, "You need to select an publisher before delete",
                    Alert.AlertType.WARNING);
        }
        if(Alerts.showConfirmation("Confirm operation",
                "Are you sure you want to delete this entry?").get() == ButtonType.OK){
            publisherController.deletePublisher
                    (publisherListView.getSelectionModel().getSelectedItem().getId());
        }
    }

    public void loadPublishers(){
        List<Publisher> publishers = publisherController.findAll();
        Collections.sort(publishers, Utils.publisherComparator());
        ObservableList<Publisher> publishersList = FXCollections.observableArrayList();
        // Populating ObservableList //
        for(Publisher p: publishers){
            publishersList.add(p);
        }
        publisherListView.setItems(publishersList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loading publishers //
        loadPublishers();
        // Adding listeners //
        publisherController.addListener(this);
    }

    @Override
    public void onDataChanged() {
        loadPublishers();
    }
}