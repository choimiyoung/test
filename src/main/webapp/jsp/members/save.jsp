<%@ page import="hello.servlet.basic.domain.member.Member" %>
<%@ page import="hello.servlet.basic.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: Choi miyoung
  Date: 2023-01-15
  Time: 오후 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  //request.response 사용가능
  MemberRepository memberRepository = MemberRepository.getInstance();
  System.out.println("MemberSaveServlet.service");
  String username = request.getParameter("username");

  //문자 -> 숫자 타입으로 변환
  int age = Integer.parseInt( request.getParameter("age")) ;

  Member member = new Member(username,age);
  memberRepository.save(member);

%>


<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>id=<%=member.getUsername()%></li>
  <li>id=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
