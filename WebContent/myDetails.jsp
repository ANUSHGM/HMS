<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%
 String email = (String)session.getAttribute("email");
System.out.print(email);
/* String email = "manoj@gmail.com"; */
System.out.println("Email:"+request.getParameter("email"));
PreparedStatement statement = null;
Connection connection = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSS/MyProfile.css">
<script src="JavaScript/About.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<meta charset="ISO-8859-1">
<title>My Details</title>
</head>
<body>

<%
try {
  
    Class.forName("oracle.jdbc.driver.OracleDriver");
    System.out.println("Driver loaded successfully..");
    connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","system" ,"system");
    System.out.println("Connection established successfully...");
    String sql = "select * from checkin1 where email=?";
    statement=connection.prepareStatement(sql);
    statement.setString(1,email);
   ResultSet rs = statement.executeQuery();
   while(rs.next()){
%>
<div class="n" ></div>
<label class="n1"><%=rs.getString("firstname") %></label><br><br>
<label class="n2"><i style='font-size:24px' class='far'>&#xf0e0;</i> <%=rs.getString("mailid") %></label><br><br>
<label class="n3"><i style='font-size:24px' class='far'>&#xf073;</i> <%=rs.getString("dateofbirth") %></label><br><br>
<label class="n4"><i style='font-size:24px' class='far'>&#xf2bd;</i> <%=rs.getString("gender") %></label><br><br>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</body>
</html>