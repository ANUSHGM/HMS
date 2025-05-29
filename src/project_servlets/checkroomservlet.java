package project_servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

@WebServlet("/checkroomservlet")
public class checkroomservlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        response.setContentType("text/html");
       
        
        String roomnum=request.getParameter("roomno");      
    //int d=Integer.parseInt(request.getParameter("roomno"));
    //int payment=Integer.parseInt(request.getParameter("payment"));
    try
    {              
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");                
        PreparedStatement stmt=con.prepareStatement("select * from checkin where roomno=?");                
         stmt.setString(1,roomnum); 
         stmt.executeQuery();
         ResultSet rs=stmt.executeQuery();
         PrintWriter out=response.getWriter();
          if(rs.next()){
              rs.getString(1);
              rs.getString(2);
              rs.getString(3);
              rs.getString(4);
              rs.getString(5);
              out.print("<h3>room is already booked</h3>");
              RequestDispatcher rd = request.getRequestDispatcher("list.html");
              rd.include(request, response);
          }
        
    else
    {
        out.println("<h3>room is available u can book now</h3>");   
        RequestDispatcher rd = request.getRequestDispatcher("list.html");
        rd.include(request, response);
    }
    }
        catch(Exception e){
    
            e.printStackTrace();
            } 
    }
               
    }    