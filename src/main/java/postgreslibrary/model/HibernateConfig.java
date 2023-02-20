package postgreslibrary.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import postgreslibrary.model.entities.Author;
import postgreslibrary.model.entities.Book;
import postgreslibrary.model.entities.BookAuthor;
import postgreslibrary.model.entities.Publisher;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    static{
        try{
            Properties properties = getProperties();
            Configuration configuration = new Configuration();
            configuration.setProperties(properties);

            // Adding annotated classes //
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(BookAuthor.class);
            configuration.addAnnotatedClass(Publisher.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fs = new FileInputStream("src/main/resources/hibernate.properties");
        properties.load(fs);
        fs.close();
        return properties;
    }
    
}