<%--
  Created by IntelliJ IDEA.
  User: Parker
  Date: 2020/2/6
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>$test$</title>
</head>
<body>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>doc_type</td>
        <td>doc_number</td>
        <td>gender</td>
        <td>chechin_date</td>
        <td>chechout_date</td>
    </tr>
    <c:forEach items="${checkin}" var="s" varStatus="st">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.documentType}</td>
            <td>${s.documentNumber}</td>
            <td>${s.gender}</td>
            <td>${s.checkinDate}</td>
            <td>${s.checkoutDate}</td>
            <td>${s.face}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
