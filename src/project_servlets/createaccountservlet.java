package project_servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

 

@WebServlet("/createaccountservlet")
public class createaccountservlet extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    
        String Firstname = request.getParameter("fname");
        String Lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String DateOfBirth = request.getParameter("dob");
        
        
        try {
        
            // loading drivers for oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //creating connection with the database 
            Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:XE","system","system");

 

            PreparedStatement ps = con.prepareStatement ("insert into account1 values(?,?,?,?,?,?)");

 

            ps.setString(1, Firstname);
            ps.setString(2, Lastname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, gender);
            ps.setString(6,DateOfBirth);
           
            int i = ps.executeUpdate();
            
            if(i > 0) {
                out.print("<h2>You are sucessfully registered<h2>");
                RequestDispatcher rs = request.getRequestDispatcher("login.html");
                rs.include(request, response);
            }
            else{
            	out.print("not registred");
            	RequestDispatcher rs = request.getRequestDispatcher("create account.html");
                rs.include(request, response);
            }
        }
        catch(Exception se) {
           se.printStackTrace();
        }
    
    }
}