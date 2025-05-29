package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admincheckoutservlet")
public class admincheckoutservlet extends HttpServlet {
	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String roomnum=request.getParameter("roomno");      
    //int d=Integer.parseInt(request.getParameter("roomno"));
    //int payment=Integer.parseInt(request.getParameter("payment"));
    try
    {              
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");                
        PreparedStatement stmt=con.prepareStatement("delete from checkin where roomno=?");                
         stmt.setString(1,roomnum); 
         //stmt.executeUpdate();
         //ResultSet rs=stmt.executeQuery();
         int i=stmt.executeUpdate();
          
         if(i>0){
              
         out.print("<h3>you are successfully checked out</h3>");
        RequestDispatcher rd = request.getRequestDispatcher("admin list.html");
        rd.include(request, response);
    }
         else{
        	 out.print("<h3>invalid room number</h3>");
        	 RequestDispatcher rd = request.getRequestDispatcher("admin list.html");
             rd.include(request, response);
         }
       
         }
        catch(Exception e){
    
            e.printStackTrace();
            } 
    }
               
    }    