package com.newsum.contactmgr;

import com.newsum.contactmgr.model.Contact;
import com.newsum.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class App {
    //Hold a reusable reference to SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        //Create a standard service registry object
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main( String[] args )
    {
        Contact contact = new ContactBuilder("Micah","Newsum")
                .withEmail("micahnew90@yahoo.com")
                .withPhone(5622346959L)
                .build();

        int id = save(contact);

        //Display list of contacts
        fetchAllContacts().stream().forEach(System.out::println);

        //Get the persisted object
        contact = findById(id);

        //Update the contact
        contact.setFirstName("Beatrice");

        // Persist the changes
        update(contact);

        // Display list of contacts after the update
        fetchAllContacts().stream().forEach(System.out::println);

        contact = findById(33);
        if (contact != null) {
            delete(contact);
        }
    }

    private static List<Contact> fetchAllContacts()
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Create criteria
        Criteria criteria = session.createCriteria(Contact.class);

        //Get a list of Contact objects according to criteria object
        List<Contact> contacts = criteria.list();

        session.close();
        return contacts;
    }

    private static Contact findById(int id)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Retrieve persistent object (or null if not found)
        Contact contact = session.get(Contact.class,id);

        //Close session
        session.close();

        //Retrieve the object
        return contact;
    }

    private static void update(Contact contact)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to update the contact
        session.update(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close session
        session.close();
    }

    private static int save(Contact contact)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to save teh contact
        int id = (int) session.save(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
        return id;
    }

    private static void delete(Contact contact)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to save teh contact
        session.delete(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
    }
}
