package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {

    public loginservlet() {
        super();
        
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	 
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
        String s1=request.getParameter("email");
        String s2=request.getParameter("password");
        
        PreparedStatement pstmt=con.prepareStatement("select * from account1 where mailid=? and password=?");
        pstmt.setString(1, s1);
        pstmt.setString(2, s2);
        
        ResultSet rs=pstmt.executeQuery();
        
        PrintWriter pw=response.getWriter();
        if(rs.next()){
            HttpSession hs=request.getSession();
            hs.setAttribute("mailid",s1);
            hs.setAttribute("password", s2);
            
            out.print("<h4>you are successfully logedin<h4>");
            RequestDispatcher rd=request.getRequestDispatcher("list.html");
            rd.include(request, response);
                
        }
        else{
        	out.print("invalid login");
            RequestDispatcher rd=request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
        
    } 
    catch (ClassNotFoundException e) {
        
        e.printStackTrace();
    } 
    catch (SQLException e) {
      
        e.printStackTrace();
    }
    }

 

}