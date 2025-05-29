package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listservlet")
public class listservlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try{
		String opt=request.getParameter("option");
		//int opt1 = Integer.parseInt(option1);
		
		if(opt.equals("checkin")){
			 pw.print("check in");
			 response.sendRedirect("check in.html");
			}
		if(opt.equals("roomavailability")){
			// pw.print("check room");
			response.sendRedirect("check room.html");
		}
		if(opt.equals("food")){
			 pw.print("food");
			response.sendRedirect("food.jsp");
		}
		if(opt.equals("checkout")){
			 pw.print("check out");
			 response.sendRedirect("check out.html");
		}
		
		if(opt.equals("logout")){
			 response.sendRedirect("main.html");
		}
		
		
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

