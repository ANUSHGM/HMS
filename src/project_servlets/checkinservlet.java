package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkinservlet")
public class checkinservlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession hs=null;
		try {
			System.out.println("from check in try");
				String roomno = request.getParameter("room no");
				String fullname= request.getParameter("full name");
				String age = request.getParameter("age");
				/*String email = request.getParameter("email");
				String email = (String)hs.getAttribute("email");
				System.out.println(email);*/
				String mobileno = request.getParameter("mobile number");
				String payment = request.getParameter("payment"); 
				
				CheckInPojo cp = new CheckInPojo();
				cp.setRoomNo(roomno);
				cp.setFullName(fullname);
				cp.setAge(age);
				//cp.setEmail(email);
				cp.setMobileNumber(mobileno);
				cp.setPayment(payment);
       
            
            // loading drivers for oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //creating connection with the database 
            Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:XE","system","system");

 

            PreparedStatement ps = con.prepareStatement ("insert into checkin values(?,?,?,?,?)");

            ps.setString(1, cp.getRoomNo());
            ps.setString(2, cp.getFullName());
            ps.setString(3, cp.getAge());
           // ps.setString(4, cp.getEmail());
            ps.setString(4, cp.getMobileNumber());
            ps.setString(5, cp.getPayment());
           
            int i = ps.executeUpdate();
            
            if(i > 0) {
            	 hs = request.getSession();
            	hs.setAttribute("roomno",1);
            	hs.setAttribute("fullname",2);
            	hs.setAttribute("age",3);
            	//hs.setAttribute("email",4);
            	hs.setAttribute("mobileno",4);
            	hs.setAttribute("payment",5);
            	
                pw.println("<marquee><h3>You are sucessfully checked in<h3></marquee>");
               RequestDispatcher rs = request.getRequestDispatcher("list.html");
               rs.include(request, response);
            }
            else{
            	pw.print("<marquee><h4>invalid details<h4><marquee>");
            	//RequestDispatcher rs = request.getRequestDispatcher("check in.html");
               // rs.include(request, response);
            }
        }
        catch(Exception se) {
            se.printStackTrace();
        }
    
    }

       
	}

