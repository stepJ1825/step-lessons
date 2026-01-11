package by.step.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@WebServlet("/greet")
public class GreetingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Устанавливаем кодировку
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            name = "Гость";
        }

        // Добавляем атрибуты в запрос
        request.setAttribute("userName", name);
        request.setAttribute("currentDateTime", LocalDateTime.now());
        request.setAttribute("hobbies", Arrays.asList("Чтение", "Программирование", "Путешествия"));

        // Передаём управление JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/greeting.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Устанавливаем кодировку и для GET
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // Поддержка GET (например, прямой заход)
        doPost(request, response);
    }
}