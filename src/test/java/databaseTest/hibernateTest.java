package databaseTest;

import beans.Chapter;
import generator.ObjectGenerator;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.text.ParseException;

/**
 * Created by Abdel on 2017-03-04.
 * Class to test database connections and integrity
 */
public class hibernateTest {

    @Test
    public void testDB() throws ParseException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Database/db_spring.xml");
        HibernateUtil hibernateUtil = applicationContext.getBean(HibernateUtil.class);

        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // creating a new party and saving it to DB
        Chapter chapterToSave = ObjectGenerator.generateChapter(null,null);

        Integer chapter_id = (Integer) session.save(chapterToSave);
        session.getTransaction().commit();

        // getting party back from database and checking field values
        Chapter savedChapter = session.get(Chapter.class, chapter_id);

        session.close();

        assert (savedChapter != null);

    }


}
