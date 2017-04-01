package dao;

import beans.Accounts.ChapterAccount;
import beans.abstracts.DaoDatabaseInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;
import utils.Security;

import java.util.List;

public class AccountDAODatabase implements DaoDatabaseInterface<ChapterAccount, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public AccountDAODatabase() {

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
    public int persist(ChapterAccount entity) {
        return (int) getCurrentSession().save(entity);
    }

    public void update(ChapterAccount entity) {
        getCurrentSession().persist(entity);
    }

    public ChapterAccount findById(Integer id) {
        ChapterAccount accountChapter = getCurrentSession().get(ChapterAccount.class, id);
        return accountChapter;

    }

    // get account by schoolName : needed for login servlet
    public ChapterAccount findBySchoolName(String schoolName) throws SecurityException {
        if (Security.isSafeFromSQLInjection(schoolName)) {
            return (ChapterAccount) getCurrentSession().createQuery("from ChapterAccount where school_name = '" + schoolName + "'")
                    .uniqueResult();
        } else {
            throw new SecurityException("Possible SQL injection attempt"); // TODO create a log file
        }
    }

    public void delete(ChapterAccount entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ChapterAccount> findAll() {
        return (List<ChapterAccount>) getCurrentSession().createQuery("from ChapterAccount").list();
    }

    public void deleteAll() {

    }
}
