package br.com.login.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() throws Exception{

        if (sessionFactory == null){

            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        else {

            sessionFactory.close();
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;

    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    public static Session getSession() throws Exception {
        Session sessao = null;
        sessao = getSessionFactory().openSession();
        return sessao;

    }
}
