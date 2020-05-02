
import business.DAO.User;
import business.DAO.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

              int id=Integer.parseInt(req.getParameter("id"));
            String login=req.getParameter("login");
            String password=req.getParameter("password");
            String email=req.getParameter("email");
            User user=new User(id,login,password,email);
            UserDB.update(user);
                      resp.sendRedirect(req.getContextPath()+"/index");
        }
        catch (Exception ex)
        {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int id=Integer.parseInt(req.getParameter("id"));
            User user= UserDB.selectOne(id);
            if(user!=null){
                req.setAttribute("user",user);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(req,resp);
            }
            else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(req,resp);
            }
    }catch (Exception ex){
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req,resp);

        }
        }
}
