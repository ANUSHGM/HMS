package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminlistservlet")
public class adminlistservlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try{
		String opt1=request.getParameter("option");
		//int opt1 = Integer.parseInt(option1);
		
		if(opt1.equals("admincheckout")){
			 pw.print("check out");
			 response.sendRedirect("admin check out.html");
		}
		if(opt1.equals("viewcustomers")){
			 pw.print("details of customers");
			response.sendRedirect("viewCustomer.jsp");
		}
		if(opt1.equals("viewfood")){
			 pw.print("view food");
			response.sendRedirect("viewFood.jsp");
		}
		if(opt1.equals("food")){
			 pw.print("food");
			response.sendRedirect("admin food.html");
		}
		if(opt1.equals("removefood")){
			 pw.print("remove food");
			response.sendRedirect("admin item remove.html");
		}
		if(opt1.equals("logout")){
			 response.sendRedirect("main.html");
		}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}