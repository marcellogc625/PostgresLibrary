package postgreslibrary.model.dao;

import java.util.List;

import org.hibernate.Session;

import postgreslibrary.model.HibernateConfig;
import postgreslibrary.model.entities.Publisher;

public class PublisherDAO {
    
    public void addPublisher(Publisher publisher){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(publisher);
        session.getTransaction().commit();
        session.close();
    }

    public Publisher getPublisher(Integer id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Publisher publisher = session.get(Publisher.class, id);
        session.close();
        return publisher;
    }

    public List<Publisher> findAll(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Publisher> publishers = session.createQuery("from Publisher", Publisher.class).list();
        session.close();
        return publishers;
    }

    public void updatePublisher(Publisher publisher){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(publisher);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePublisher(Integer id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Publisher publisher = session.get(Publisher.class, id);
        session.remove(publisher);
        session.getTransaction().commit();
        session.close();
    }

}
