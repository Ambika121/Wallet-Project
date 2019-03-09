<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="" >Home</a>
<form action="fundtransfered" method="get">
<tr>
<td>Enter Source Mobile Number : </td>
<td><input type="number" name="sourceMobileNo" size="30"/></td>
</tr>
<tr>
<td>Enter Destination Mobile Number : </td>
<td><input type="number" name="destinationMobileNo" size="30"/></td>
</tr>
<tr>
<td> Enter Amount to Transfer : </td>
<td><input type="number" name="balance" size="30"/></td>
</tr>
<tr>
<td><input type="submit" value="submit"/>
</td></tr>
</form>
</body>
</html>