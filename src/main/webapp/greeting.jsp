<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Приветствие</title>
</head>
<body>
<h1>Привет, <c:out value="${userName}" />!</h1>

<p>Текущее время:
  <fmt:formatDate value="${currentDateTime}" pattern="dd.MM.yyyy HH:mm:ss" />
</p>

<ul>
  <c:forEach var="hobby" items="${hobbies}">
    <li><c:out value="${hobby}" /></li>
  </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/index.html">Назад</a>
</body>
</html>