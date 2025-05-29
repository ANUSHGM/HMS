<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator,java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%@ page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.DriverManager,java.util.HashMap"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FOOD</title>
</head>
<body>
<style>
body {
   /*   background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeMAY3Z-205kWN7XF5u2F8GdNrSs6-RvcMOw&usqp=CAU");  
    background-repeat:no-repeat;*/
  background-color:#78725b; 
	text-align:center;
}
</style>
product<br>
<%
Map<String,Integer> m;

 
try{
    m=(Map)session.getAttribute("fooditems");
    Set<String> s=m.keySet();
    Iterator<String> i=s.iterator();
    while(i.hasNext()){
    String key=i.next();
    out.print(key+" <br>");
    }
//out.println("old session");
}
catch(Exception e){
    m=new HashMap<String,Integer>();
    session.setAttribute("fooditems",m);
//    out.println(" new session");
}

 

if(request.getParameter("items")!=null){
    String items=request.getParameter("items");    
    String[] arr=items.split(",");
    Integer price=Integer.valueOf(arr[1]);
    m.put(arr[0],price);
out.println(items);    
}
if(request.getParameter("bill")!=null){
    request.setAttribute("fooditems",m);
    RequestDispatcher r=request.getRequestDispatcher("display.jsp");
    r.forward(request, response);
out.println("bill");    
}
%>
<body>
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeMAY3Z-205kWN7XF5u2F8GdNrSs6-RvcMOw&usqp=CAU" alt="food">
<form   method="post">
 
 Items : <br>
<% Class.forName("oracle.jdbc.driver.OracleDriver");              
                Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:XE","system","system");                
                PreparedStatement stmt=con.prepareStatement("select * from food"); 
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    
               %>

       
           <input type="radio" name="items" value="<%=rs.getString(1)+","+rs.getString(2)%>"> <%=rs.getString(1)+":"+rs.getString(2)%> <br>
                     
          <%  } %>
          <input type="submit" name="add" value="add" >
           <input type="submit" name="bill" value="bill" >
          </form>
         
       
</html>