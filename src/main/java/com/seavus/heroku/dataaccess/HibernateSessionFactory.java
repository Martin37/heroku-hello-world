package com.seavus.heroku.dataaccess;

import com.seavus.heroku.model.Item;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    public static final String DATABASE_URL = System.getenv("JDBC_DATABASE_URL");

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url", DATABASE_URL)
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }
}
