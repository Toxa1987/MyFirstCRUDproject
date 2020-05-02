
import business.DAO.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int id=Integer.parseInt(req.getParameter("id"));
            UserDB.delete(id);
            resp.sendRedirect(req.getContextPath()+"/index");
        }
        catch (Exception ex)
        {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req,resp);

        }
    }
}
