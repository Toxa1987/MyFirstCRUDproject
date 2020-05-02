
import business.DAO.User;
import business.DAO.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String login =req.getParameter("login");
            String password =req.getParameter("password");
            String email =req.getParameter("email");

            User user=new User(login,password,email);
        UserDB.insert(user);
        resp.sendRedirect(req.getContextPath()+"/index");
    }catch (Exception ex){
            getServletContext().getRequestDispatcher("/create.jsp").forward(req,resp);
            System.out.println(1);
        }
    }
}
