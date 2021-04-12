<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/resources/style.css" type="text/css" rel="stylesheet">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html>
<head>
    <title>${title}</title>
</head>
<body>
<%@ include file="bar.jsp" %>
<form action="/add" method="post">
<input type="hidden" name="id" value="${record.id}">

        <table style="height: 80px">
            <th>
                <input style="height: 40px" name="city" value="${record.city}" required/>
            </th>
            <th>
                <textarea name="description" required>${record.description}</textarea>
            </th>

        </table>
<br>
    <button class="button">Сохранить</button>
</form>

</body>
</html>
