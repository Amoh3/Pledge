package servlets;

import beans.Accounts.ChapterAccount;
import beans.Chapter;
import beans.constants.RequestParameter;
import managers.AccountManager;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static utils.ParsingUtil.getHTTPResponsePartAsString;
import static utils.ParsingUtil.writeToResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/LoginServlet")
@MultipartConfig
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        AccountManager accountManager = new AccountManager();

        String school = getHTTPResponsePartAsString(request, RequestParameter.SCHOOL);
        String uName = getHTTPResponsePartAsString(request, RequestParameter.USERNAME);
        String pass = getHTTPResponsePartAsString(request, RequestParameter.PASSWORD);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Database/db_spring.xml");
        HibernateUtil hibernateUtil = applicationContext.getBean(HibernateUtil.class);

        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ChapterAccount chapterAccount = (ChapterAccount) session.createQuery("from ChapterAccount where school_name = '" + school + "'")
                .uniqueResult();

        Chapter chapter = null;
        if (chapterAccount != null) {
            int id = chapterAccount.getAccount_id();
            // getting party back from database and checking field values
            chapter = (Chapter) session.createQuery("from Chapter where  accountChapter.id = " + id )
                    .uniqueResult();
        }

        session.close();

        if (chapter != null){
            chapter.getVolunteers().stream().filter(part -> ());

        }




        response.setContentType("text/plain");
        PrintWriter responseWriter = response.getWriter();

        if (login != null && login.getPassword().equals(pass)) {
            writeToResponse(responseWriter, ResponseParameter.RESPONSE_STATUS, ResponseMessage.AUTHENTICATED.toString
                    ());
        } else {
            writeToResponse(responseWriter, ResponseParameter.RESPONSE_STATUS, ResponseMessage
                    .ERROR_INCORRECT_CREDENTIALS.toString());
        }

        responseWriter.flush();
        responseWriter.close();
    }

}
