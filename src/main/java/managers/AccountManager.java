package managers;

import beans.Accounts.ChapterAccount;
import dao.AccountDAODatabase;

import java.util.List;

/**
 * Created by Abdel on 2017-03-08.
 */
public class AccountManager {

    private static AccountDAODatabase accountDAO;
    private ChapterAccount accountChapter;

    public AccountManager() {
        accountDAO = new AccountDAODatabase();
    }

    // Manager methods using DAO
    public int persist(ChapterAccount entity) {
        int id;
        accountDAO.openCurrentSessionWithTransaction();
        id = accountDAO.persist(entity);
        accountDAO.closeCurrentSession();
        return id;
    }

    public void update(ChapterAccount entity) {
        accountDAO.openCurrentSessionWithTransaction();
        accountDAO.update(entity);
        accountDAO.closeCurrentSession();
    }

    public ChapterAccount findById(Integer id) {
        accountDAO.openCurrentSessionWithTransaction();
        accountChapter = accountDAO.findById(id);
        accountDAO.closeCurrentSession();
        return accountChapter;
    }

    // get accountChapter by username : needed for login servlet
    public ChapterAccount findByUsername(String username) throws SecurityException {
        accountDAO.openCurrentSessionWithTransaction();
        accountChapter = accountDAO.findBySchoolName(username);
        accountDAO.closeCurrentSession();
        return accountChapter;
    }

    public void delete(ChapterAccount entity) {
        accountDAO.openCurrentSessionWithTransaction();
        accountDAO.delete(entity);
        accountDAO.closeCurrentSession();
    }

    public List<ChapterAccount> findAll() {
        accountDAO.openCurrentSessionWithTransaction();
        List<ChapterAccount> accountChapters = accountDAO.findAll();
        accountDAO.closeCurrentSession();
        return accountChapters;
    }

    public void deleteAll() {
        accountDAO.openCurrentSessionWithTransaction();
        accountDAO.deleteAll();
        accountDAO.closeCurrentSession();
    }

}
