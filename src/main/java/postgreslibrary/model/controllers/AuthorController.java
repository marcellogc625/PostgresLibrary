package postgreslibrary.model.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.model.dao.AuthorDAO;
import postgreslibrary.model.entities.Author;

public class AuthorController {
    
    private AuthorDAO authorDAO;

    public AuthorController(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
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

    // Controller methods //
    public List<Author> findAll(){
        try{
            return authorDAO.findAll();
        }
        catch(Exception e){
            Alerts.showAlert("Error in catching all authors data", null, 
            e.getMessage(), AlertType.ERROR);
            return null;
        }
    }

    public Author findAuthorById(Integer id){
        try{
            return authorDAO.getAuthor(id);
        }
        catch(Exception e){
            Alerts.showAlert("Error in catching author data", null, 
            e.getMessage(), AlertType.ERROR);
            return null;
        }
    }

    public void saveAuthor(Author author){
        try{
            authorDAO.addAuthor(author);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Author data updated with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in creating author data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

    public void updateAuthor(Author author){
        try{
            authorDAO.updateAuthor(author);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Author data updated with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in updating author data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }
    
    public void deleteAuthor(Integer id){
        try{
            authorDAO.deleteAuthor(id);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Author data deleted with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in deleting author data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

}
