<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome user </title>
<style>
.error
{
color:red;
font-weight:bold;
}
</style>
</head>
<body>
<div align="center"><h1>Welcome to Login page</h1> </div> 
<form:form action="viewwallet" method="post" modelAttribute="customer" >
<tr>
<a href="" >Home</a>
<td>Mobile Number : </td>
<td><form:input path="mobileNo" size="30"/></td>
<td><form:errors path="mobileNo" cssClass="error"/></td>
</tr>
<tr>
<td><input type="submit" value="submit"/>
</td></tr>
</form:form>

</body>
</html>