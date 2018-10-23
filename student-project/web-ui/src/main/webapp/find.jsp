<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, edu.javacourse.first.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Simple page JSP </title>
    </head>
    <body>
        <h1> Test Data JSP </h1>

        <%
            List<StudentOrder> ls = (List) request.getAttribute("ORDER_LIST");

            for (StudentOrder so : ls) {
                out.println(so.getStudentOrderId() + "<br/>");
            }
        %>

       <%=ls %><br/>
       <% out.println("------Test Space-------"); %><br/>
       <%=request.getAttribute("ORDER_LIST") %><br/>
       ${ORDER_LIST}

       <%-- Пример задания переменной --%>
       <c:set var="title" value="The best title in the world"/>
       <%-- Пример использования переменной --%>
       <h3>${title}</h3>

    </body>
</html>