package appmanager;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();

        return new Contacts(result);
    }
}
