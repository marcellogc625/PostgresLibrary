package postgreslibrary.model.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import postgreslibrary.gui.listeners.DataChangeListener;
import postgreslibrary.gui.utils.Alerts;
import postgreslibrary.model.dao.BookAuthorDAO;
import postgreslibrary.model.dao.BookDAO;
import postgreslibrary.model.entities.Book;
import postgreslibrary.model.entities.BookAuthor;
import postgreslibrary.model.entities.BookAuthorId;

public class BookController {

    private BookDAO bookDAO;
    private BookAuthorDAO bookAuthorDAO;

    public BookController(BookDAO bookDAO, BookAuthorDAO bookAuthorDAO){
        this.bookDAO = bookDAO;
        this.bookAuthorDAO = bookAuthorDAO;
    }
    // Listener methods //
    public List<DataChangeListener> listeners = new ArrayList<>();

    private void notifyListeners(){
        for(DataChangeListener dcl: listeners){
            dcl.onDataChanged();
        }
    }

    public void addListener(DataChangeListener dcl){
        listeners.add(dcl);
    }
    // BookAuthor findAll() method //
    public List<BookAuthor> findAllBookAuthors(){
        try{
            return bookAuthorDAO.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // Book findAll() method //
    public List<Book> findAllBooks(){
        try{
            return bookDAO.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // Combinated methods //
    public void saveBook(Book book, BookAuthor bookAuthor){
        try{
            bookDAO.addBook(book);
            bookAuthorDAO.addBookAuthor(bookAuthor);
            notifyListeners();
            Alerts.showAlert("Success", null, "Book data created with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in creating book data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

    public void updateBook(Book book, BookAuthor bookAuthor){
        try{
            bookDAO.updateBook(book);
            bookAuthorDAO.updateBookAuthor(bookAuthor);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Book data updated with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in updating book data", null, 
            e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void deleteBook(String isbn, BookAuthorId id){
        try{
            bookAuthorDAO.deleteBookAuthor(id);
            bookDAO.deleteBook(isbn);
            notifyListeners();
            Alerts.showAlert("Success", null, 
            "Book data deleted with success.", AlertType.INFORMATION);
        }
        catch(Exception e){
            Alerts.showAlert("Error in deleting book data", null, 
            e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
}