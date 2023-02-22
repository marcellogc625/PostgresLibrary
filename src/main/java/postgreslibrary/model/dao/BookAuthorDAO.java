package postgreslibrary.model.dao;

import java.util.List;

import org.hibernate.Session;

import postgreslibrary.model.HibernateConfig;
import postgreslibrary.model.entities.BookAuthor;
import postgreslibrary.model.entities.BookAuthorId;

public class BookAuthorDAO {

    public void addBookAuthor(BookAuthor bookAuthor){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(bookAuthor);
        session.getTransaction().commit();
        session.close();
    }

    public List<BookAuthor> findAll(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<BookAuthor> bookAuthors = session.createQuery("from BookAuthor", BookAuthor.class).list();
        session.close();
        return bookAuthors;
    }

    public void updateBookAuthor(BookAuthor bookAuthor){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(bookAuthor);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBookAuthor(BookAuthorId id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        BookAuthor bookAuthor = session.get(BookAuthor.class, id);
        session.remove(bookAuthor);
        session.getTransaction().commit();
        session.close();
    }

}