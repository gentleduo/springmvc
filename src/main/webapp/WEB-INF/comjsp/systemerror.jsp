<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统内部错误页面</title>
</head>
<body>
	<H2>系统内部错误:${exception.getClass().getSimpleName()}</H2>
	<hr />
	<P>错误描述：</P>
	${exception.getMessage()}
	<P>错误信息：</P>
	<%
		Exception e = (Exception) request.getAttribute("exception");
		e.printStackTrace(new java.io.PrintWriter(out));
	%>
	<%--在使用el表达式时,不能使用关键字或与保留字，所以下面的写法会抛异常:
	${exception.printStackTrace(new java.io.PrintWriter(out));} --%>
</body>
</html>