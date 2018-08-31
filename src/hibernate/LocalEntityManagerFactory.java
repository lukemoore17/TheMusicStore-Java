package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LocalEntityManagerFactory implements ServletContextListener{
    private static EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            emf = Persistence.createEntityManagerFactory("TheMusicStore");
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}
