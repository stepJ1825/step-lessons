package by.step.servlets;

import by.step.util.CookieFilter;
import by.step.util.HTMLFilter;
import by.step.util.JSONFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Example servlet showing request headers
 */
@WebServlet("/headers")
public class RequestHeaderExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (prefersJSON(request.getHeader("Accept"))) {
            renderJSON(request, response);
        } else {
            renderHTML(request, response);
        }
    }

    /**
     * Returns true if the client appears to prefer a JSON response, false otherwise. Note that this method is not very
     * pedantic and uses only a very lazy algorithm for checking whether JSON is "preferred".
     *
     * @param acceptHeader The value of the HTTP "Accept" header from the client.
     * @return true if the client appears to prefer a JSON response, false otherwise.
     */
    protected boolean prefersJSON(String acceptHeader) {
        if (null == acceptHeader) {
            return false;
        }
        // mime/type, mime/type;q=n, ...

        // Don't bother with the q-factor.
        // This is not expected to be 100% accurate or spec-compliant
        String[] accepts = acceptHeader.split(",");
        for (String accept : accepts) {
            if (accept.contains("application/json")) {
                return true;
            }

            // text/html, application/html, etc.
            if (accept.contains("html")) {
                return false;
            }
        }
        return false;
    }

    protected void renderHTML(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. Попробовать получить язык из параметра ?lang=...
        String langParam = request.getParameter("lang");
        Locale userLocale;

        if (langParam != null && !langParam.trim().isEmpty()) {
            // Сохраняем выбор в сессии
            HttpSession session = request.getSession();
            session.setAttribute("userLang", langParam);

            // Определяем Locale
            if ("ru".equals(langParam)) {
                userLocale = new Locale("ru", "RU");
            } else if ("en".equals(langParam)) {
                userLocale = Locale.ENGLISH;
            } else {
                userLocale = request.getLocale(); // fallback
            }
        } else {
            // 2. Иначе — смотрим сессию
            HttpSession session = request.getSession(false);
            String savedLang = (session != null) ? (String) session.getAttribute("userLang") : null;

            if (savedLang != null) {
                if ("ru".equals(savedLang)) {
                    userLocale = new Locale("ru", "RU");
                } else if ("en".equals(savedLang)) {
                    userLocale = Locale.ENGLISH;
                } else {
                    userLocale = request.getLocale();
                }
            } else {
                // 3. Иначе — используем локаль браузера
                userLocale = request.getLocale();
            }
        }

        // Загружаем ресурсы для выбранной локали
        ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", userLocale);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = rb.getString("requestheader.title");

        out.println("<!DOCTYPE html><html>");
        out.println("<head><meta charset=\"UTF-8\" />");
        out.println("<title>" + HTMLFilter.filter(title) + "</title>");
        out.println("</head><body bgcolor=\"white\">");

        // Ссылки переключения языка
        out.println("<div style=\"float: right; margin-bottom: 10px;\">");
        out.println("<a href=\"?lang=en\" style=\"margin-right: 10px;\">English</a>");
        out.println("<a href=\"?lang=ru\">Русский</a>");
        out.println("</div>");

        String cp = request.getContextPath();
        out.println("<a href=\"" + cp + "/reqheaders.html\">");
        out.println("<img src=\"" + cp + "/images/code.gif\" height=\"24\" width=\"24\" align=\"right\" border=\"0\" alt=\"view code\">");
        out.println("</a>");
        out.println("<a href=\"" + cp + "/index.jsp\">");
        out.println("<img src=\"" + cp + "/images/return.gif\" height=\"24\" width=\"24\" align=\"right\" border=\"0\" alt=\"return\">");
        out.println("</a>");

        out.println("<h3>" + HTMLFilter.filter(title) + "</h3>");
        out.println("<table border=\"0\"><tbody>");

        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String headerName = e.nextElement();
            String headerValue = request.getHeader(headerName);
            out.println("<tr><td bgcolor=\"#CCCCCC\">" + HTMLFilter.filter(headerName) + "</td>");
            out.println("<td>" + HTMLFilter.filter(headerValue) + "</td></tr>");
        }

        out.println("</tbody></table>");
        out.println("</body></html>");
    }

    protected void renderJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.append('[');
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String headerName = e.nextElement();
            String headerValue = request.getHeader(headerName);

            out.append("{\"").append(JSONFilter.escape(headerName)).append("\":\"");


            if (headerName.toLowerCase(Locale.ENGLISH).contains("cookie")) {
                HttpSession session = request.getSession(false);
                String sessionId = null;
                if (session != null) {
                    sessionId = session.getId();
                }
                out.append(JSONFilter.escape(CookieFilter.filter(headerValue, sessionId)));
            } else {
                out.append(JSONFilter.escape(headerValue));
            }
            out.append("\"}");

            if (e.hasMoreElements()) {
                out.append(',');
            }
        }

        out.print("]");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}

