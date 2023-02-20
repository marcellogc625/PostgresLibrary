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

public class BookController {

    private BookDAO bookDAO;
    private BookAuthorDAO bookAuthorDAO;

    public BookController(BookDAO bookDAO, BookAuthorDAO bookAuthorDAO){
        this.bookDAO = bookDAO;
        this.bookAuthorDAO = bookAuthorDAO;
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
        }
        catch(Exception e){
            Alerts.showAlert("Error in updating book data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }

    public void deleteBook(String isbn){
        try{
            bookDAO.deleteBook(isbn);
            bookAuthorDAO.deleteBookAuthor(isbn);
            notifyListeners();
        }
        catch(Exception e){
            Alerts.showAlert("Error in deleting book data", null, 
            e.getMessage(), AlertType.ERROR);
        }
    }
    
}