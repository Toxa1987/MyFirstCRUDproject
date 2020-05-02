import business.DAO.User;
import business.DAO.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/index")
public class IndexServlet  extends HttpServlet {

    private int recordsPerPage;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page=1;

        if(req.getParameter("page")!=null){
            page=Integer.parseInt(req.getParameter("page"));
        }
        ArrayList<User> users= UserDB.select((page-1)*getRecordsPerPage(),getRecordsPerPage());
        int rows =new UserDB().getRows();
        int pages=(int)Math.ceil((double)rows/(double)getRecordsPerPage());
        req.setAttribute("users",users);
        req.setAttribute("pages",pages);
        req.setAttribute("CurrentPage",page);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Pages
        int page;
        page=Integer.parseInt(req.getParameter("page"));
        // Records on page
        int recordsPerPage;
        if(req.getParameter("recordsPerPage")!=null){
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
            setRecordsPerPage(recordsPerPage);
        }


        int rows =new UserDB().getRows();
        int pages=(int)Math.ceil((double)rows/(double)getRecordsPerPage());
        if( page==0) page=1;
        if(page==pages+1) page=pages;


        // Output
        ArrayList<User> users= UserDB.select((page-1)*getRecordsPerPage(),getRecordsPerPage());
        req.setAttribute("users",users);
        req.setAttribute("pages",pages);
        req.setAttribute("CurrentPage",page);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);


    }
    private int getRecordsPerPage(){
        if (recordsPerPage==0) recordsPerPage=10;
        return recordsPerPage;
    }
    private int setRecordsPerPage(int recPerPage){
        recordsPerPage=recPerPage;
        return recordsPerPage;
    }
}
