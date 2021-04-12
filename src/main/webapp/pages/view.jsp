<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/resources/style.css" type="text/css" rel="stylesheet">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>

<script>
    function ajax_delete(id) {
        $.post('/delete', {id: id}, function (d) {
            $('#' + id).remove();
        })
    }
</script>


<html>
<head>
    <title>Просмотр записей</title>
</head>
<body>
<%@ include file="bar.jsp" %>
    <table id="table">
        <tr>
            <th>Город</th>
            <th>Описание</th>
            <th>Дата</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${list}" var="list">
            <tr id="${list.id}">
                <td>${list.city}</td>
                <td>${list.description}</td>
                <td>${list.date}</td>
                <td><a href="/edit/${list.id}"><i class="fa fa-pencil" style="color: black"></i></a></td>
                <td><a><i class="fa fa-trash" style="color: black" onclick="ajax_delete(${list.id})"></i></a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
