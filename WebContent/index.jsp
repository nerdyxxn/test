<%@page import="common.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="common.JDBCTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% // 위는 JSP 코드라고 부름! JAVA문법을 따른다! 이처럼 괄호표시 안에 넣을 수 있어 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
JDBCTemplate jdbcTemp = new JDBCTemplate();
List<EmpVO> emplist = jdbcTemp.getEmps();	// getEmps()는 DB에 연결하고 select * from emp라는 sql문의 실행결과를 리턴한다.
%>
	<p>EMP 목록</p>
	<table border="1px">
		<tr>
			<td>empno</td>
			<td>ename</td>
			<td>job</td>
		</tr>
		<%
			// %% : 지금부터는 자바 코드 형태 + jsp 형태 코드로 넣을 수 있어! 주석도 자바주석으로 사용!!!!!!!!!!!!!!!!!!!!!!!!!!!
		for (int i=0; i < emplist.size(); i++ ) {	// 배열이 아니기때문에 length 아니고 size
			EmpVO empvo = emplist.get(i);			// 여기서 empvo는 자바의 변수. 따라서 아래에서 사용하려면 그에 맞게 형식을 맞춰서 작성해야함.
		%>
		<tr>
			<td><%= empvo.getEmpno() %></td>
			<td><%= empvo.getEname() %></td>
			<td><%= empvo.getJob() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>