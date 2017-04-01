package dao;

import beans.Chapter;
import beans.abstracts.DaoDatabaseInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Abdel on 2017-03-08.
 * Chapter
 */
public class ChapterDAODatabase implements DaoDatabaseInterface<Chapter, Integer> {
    private Session currentSession;

    private Transaction currentTransaction;

    public ChapterDAODatabase() {

    }

    // private getter for session
    private Session getCurrentSession() {
        return currentSession;
    }

    // Using Spring to inject Session factory
    private SessionFactory getSessionFactory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Database/db_spring.xml");
        HibernateUtil hibernateUtil = context.getBean("hibernateUtil", HibernateUtil.class);
        return hibernateUtil.getSessionFactory();
    }

    // Methods to open and close sessions/transactions
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    // DB DAO interface methods
    public int persist(Chapter entity) {
        return (int) getCurrentSession().save(entity);
    }

    public void update(Chapter entity) {
        getCurrentSession().persist(entity);
    }

    public Chapter findById(Integer id) {
        Chapter chapter = getCurrentSession().get(Chapter.class, id);
        return chapter;

    }

    public void delete(Chapter entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Chapter> findAll() {
        return (List<Chapter>) getCurrentSession().createQuery("from Chapter ").list();
    }

    public void deleteAll() {

    }
}
