package postgreslibrary.model.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.model.dao.PublisherDAO;
import postgreslibrary.model.entities.Publisher;

public class PublisherController {

    private PublisherDAO publisherDAO;

    public PublisherController(PublisherDAO publisherDAO){
        this.publisherDAO = publisherDAO;
    }
    // Listener methods //
    private List<DataChangeListener> listeners = new ArrayList<>();

    private void notifyListeners(){
        for(DataChangeListener dcl: listeners){
            dcl.onDataChanged();
        }
    }

    public void addListener(DataChangeListener dcl){
        listeners.add(dcl);
    }

    public List<Publisher> findAll(){
        try{
            return publisherDAO.findAll();
        }
        catch(Exception e){
            Alerts.showAlert("Error in catching all publishers data", null, 
            e.getMessage(), AlertType.ERROR);
            return null;
        }
    }
    
    public Publisher findPublisherById(Integer id){
        try{
            return publisherDAO.getPublisher(id);
        }
        catch(Exception e){
            Alerts.showAlert("Error in catching publisher data", null, 
            e.getMessage(), AlertType.ERROR);
            return null;
        }
    }

    public void savePublisher(Publisher publisher){
        try{
            publisherDAO.addPublisher(publisher);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Publisher data saved with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in saving publisher data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

    public void updatePublisher(Publisher publisher){
        try{
            publisherDAO.updatePublisher(publisher);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Publisher data updated with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in updating publisher data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

    public void deletePublisher(Integer id){
        try{
            publisherDAO.deletePublisher(id);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Publisher data data with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in deleting publisher data", null, 
            "You need to delete all books from this publisher before delete this entry.", AlertType.ERROR);
        }
    }
    
}