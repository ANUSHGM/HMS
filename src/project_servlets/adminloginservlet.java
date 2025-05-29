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


@WebServlet("/adminloginservlet")
public class adminloginservlet extends HttpServlet {

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	 
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
    String str1=request.getParameter("email");
    String str2=request.getParameter("password");
    
    PreparedStatement pstmt=con.prepareStatement("select * from admin where email=? and password=?");
        
    pstmt.setString(1, str1);
    pstmt.setString(2, str2);
    
    ResultSet rs=pstmt.executeQuery();
    
    if(rs.next()){
        HttpSession hs=request.getSession();
        hs.setAttribute("email",str1);
        hs.setAttribute("password", str2);
        
        out.print("<h3>you are successfully logedin</h3>");
       RequestDispatcher rd=request.getRequestDispatcher("admin list.html");
        rd.include(request, response);
            
    }
    else{
    	out.print("invalid login");
        RequestDispatcher rd=request.getRequestDispatcher("admin login.html");
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