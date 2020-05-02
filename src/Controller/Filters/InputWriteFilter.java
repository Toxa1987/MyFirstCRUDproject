package Controller.Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class InputWriteFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest req= (HttpServletRequest) servletRequest;
       String URL= req.getRequestURL().toString();
       String method = req.getMethod();
        Date date=new Date();
        String s=req.getServletContext().getRealPath("/WEB-INF/classes/log/InputLog.txt");
      try(  FileWriter fileWriter= new FileWriter(s,true);){
        fileWriter.write(date.toString()+"\n");
        fileWriter.write(method+"\n");
        fileWriter.write(URL+"\n");
      }catch (Exception ex){
          System.out.println(ex+" "+s);
      }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
