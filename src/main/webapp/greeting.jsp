<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Приветствие</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");

    String userName = (String) request.getAttribute("userName");
    if (userName == null) userName = "Гость";

    java.time.LocalDateTime currentDateTime = (java.time.LocalDateTime) request.getAttribute("currentDateTime");
    List<String> hobbies = (List<String>) request.getAttribute("hobbies");
%>

<h1>Привет, <%= userName %>!</h1>

<p>Текущее время:
    <% if (currentDateTime != null) { %>
        <%= currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) %>
    <% } else { %>
        Не определено
    <% } %>
</p>

<ul>
    <% if (hobbies != null) {
        for (String hobby : hobbies) { %>
            <li><%= hobby %></li>
    <%   }
       } else { %>
        <li>Хобби не указаны</li>
    <% } %>
</ul>

<a href="<%= request.getContextPath() %>/index.html">Назад</a>
</body>
</html>