package postgreslibrary.model.dao;

import java.util.List;

import org.hibernate.Session;

import postgreslibrary.model.HibernateConfig;
import postgreslibrary.model.entities.Author;

public class AuthorDAO {
    
    public void addAuthor(Author author){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(author);
        session.getTransaction().commit();
        session.close();
    }

    public Author getAuthor(Integer id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public List<Author> findAll(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Author> authors = session.createQuery("from Author", Author.class).list();
        session.close();
        return authors;
    }

    public void updateAuthor(Author author){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(author);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAuthor(Integer id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Author author = session.get(Author.class, id);
        session.remove(author);
        session.getTransaction().commit();
        session.close();
    }

}