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


@WebServlet("/AdminItemRemoveServlet")
public class AdminItemRemoveServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String item=request.getParameter("item");      
    //int d=Integer.parseInt(request.getParameter("roomno"));
    //int payment=Integer.parseInt(request.getParameter("payment"));
    try
    {              
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");                
        PreparedStatement stmt=con.prepareStatement("delete from food where item=?");                
         stmt.setString(1,item); 
         //stmt.executeUpdate();
         //ResultSet rs=stmt.executeQuery();
         int i=stmt.executeUpdate();
          
         if(i>0){
              
         out.print("<h3>item is successfully removed</h3>");
        RequestDispatcher rd = request.getRequestDispatcher("admin list.html");
        rd.include(request, response);
    }
         else{
        	 out.print("<h3>invalid item name</h3>");
        	 RequestDispatcher rd = request.getRequestDispatcher("admin item remove.html");
             rd.include(request, response);
         }
       
         }
        catch(Exception e){
    
            e.printStackTrace();
            } 
    }
	}

