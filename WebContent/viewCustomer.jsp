<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
 <%
PreparedStatement statement = null;
Connection connection = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Customers</title>
</head>
<body>

<% 
try {
	
    Class.forName("oracle.jdbc.driver.OracleDriver");
    connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","system" ,"system");
    String sql = "select * from checkin";
    statement=connection.prepareStatement(sql);
    int r = statement.executeUpdate();
    System.out.println(r);
    if(r>0){
   ResultSet rs = statement.executeQuery();
   System.out.println(rs);
   %>

   <table id="customers">
<tr>
<th>Room No</th>
<th>Full Name</th>
<th>Age</th>
<th>Mobile Number</th>
<th>Payment</th>
</tr>
<a href="admin check out.html">CHECK OUT</a>
<% 
   while(rs.next()){
%>
<tr>
<td><%=rs.getString("roomno") %></td>
<td><%=rs.getString("fullname") %></td>
<td><%=rs.getString("age") %></td>
<td><%=rs.getString("mobilenumber") %></td>
<td><%=rs.getString("payment") %></td>
</tr><br>
<%
}
    }
    else{
%>
<h2>NO ROOMS ARE BOOKED</h2>
<%    
}
connection.close();
}
catch (Exception e)
{
e.printStackTrace();
}
%>
</body>
</html>