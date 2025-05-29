package project_servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/adminfoodservlet")
public class adminfoodservlet extends HttpServlet {
   
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
   
        String item = request.getParameter("item");
        String price = request.getParameter("price");
       
       
       
        try {                
            Class.forName("oracle.jdbc.driver.OracleDriver");
                     
            Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            PreparedStatement ps = con.prepareStatement
                        ("insert into food values(?,?)");
            ps.setString(1, item);
            ps.setString(2, price);
          
          
            int i = ps.executeUpdate();
           
            if(i > 0) {
                out.print("<h2>You are sucessfully entered<h2>");
                RequestDispatcher rs = request.getRequestDispatcher("admin list.html");
                rs.include(request, response); 
            }
            else{
                out.print("not entered");
                RequestDispatcher rs = request.getRequestDispatcher("admin list.html");
                rs.include(request, response); 
            }
        }
        catch(Exception se) {
           se.printStackTrace();
        }
   
    }
}