package com.newsum.contactmgr;

import com.newsum.contactmgr.model.Contact;
import com.newsum.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App {
    //Hold a reusable reference to SessionFactory
    //private static final SessionFactory sessionFactory = buildSessionFactory();

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
        System.out.print(contact);
    }
}
