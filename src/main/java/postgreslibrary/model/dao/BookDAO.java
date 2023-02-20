package postgreslibrary.model.dao;

import java.util.List;

import org.hibernate.Session;

import postgreslibrary.model.HibernateConfig;
import postgreslibrary.model.entities.Book;

public class BookDAO {
    
    public void addBook(Book book){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        session.close();
    }

    public Book getBook(String isbn){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Book book = session.get(Book.class, isbn);
        session.close();
        return book;
    }

    public List<Book> findAll(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Book> books = session.createQuery("from Book", Book.class).list();
        session.close();
        return books;
    }

    public void updateBook(Book book){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBook(String isbn){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, isbn);
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

}