<%@page import="bitcamp.myapp.vo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>학생(JSP + MVC2 + EL)</h1>

<div><a href='form'>새 학생</a></div>

<table border='1'>
<tr>
  <th>번호</th> <th>이름</th> <th>전화</th> <th>재직</th> <th>전공</th>
</tr>
<jsp:useBean id="students" scope="request" type="java.util.List<Student>"/>
<% 
    for (Student student : students) {
      pageContext.setAttribute("student", student);
      %>
  <tr>
      <td>${student.no}</td> 
      <td><a href='view?no=${student.getNo}'>${student.name}</a></td> 
      <td>${student.tel}</td> 
      <td>${student.isWorking() ? "예" : "아니오"}</td> 
      <td><%=getLevelText(student.getLevel())%></td>
  </tr>
<% 
    }
%>
</table>

<form action='list' method='get'>
  <input type='text' name='keyword' value=''${param.keyword}'>
  <button>검색</button>
</form>

</body>
</html>


