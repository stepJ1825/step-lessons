package by.step.util;

/**
 * Простой утилитный класс для экранирования строк в JSON.
 * Аналог удалённого org.apache.tomcat.util.json.JSONFilter.
 */
public final class JSONFilter {

    private JSONFilter() {
        // Утилитный класс — инстанцировать нельзя
    }

    /**
     * Экранирует строку для безопасного включения в JSON-значение (в двойных кавычках).
     *
     * @param input входная строка (может быть null)
     * @return экранированная строка, пригодная для вставки между кавычками в JSON
     */
    public static String escape(String input) {
        if (input == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20 || (c >= 0x7F && c <= 0x9F) || (c >= 0x2000 && c <= 0x20FF)) {
                        // Экранируем управляющие символы и другие потенциально проблемные символы
                        sb.append("\\u").append(String.format("%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
}