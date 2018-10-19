<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, edu.javacourse.first.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> JSP Page </title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Date</th>
                    <th>Husband SurName</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sOrder" items="${ORDER_LIST}">
                    <tr>
                        <th>${sOrder.studentOrderId}</th>
                        <th>${sOrder.studentOrderDate}</th>
                        <th>${sOrder.husband.surName}</th>
                    </tr>
                </c:forEach>
            <t/body>
        </table>
    </body>
</html>