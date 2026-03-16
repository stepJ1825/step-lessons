package by.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WordCounterUtils {
    private WordCounterUtils() {
    }

    public static final String REQUEST_JSON = """
            [
              {
                "name": "firstName",
                "word": "first",
                "type": "firstType"
              },
              {
                "name": "secondName",
                "word": "second",
                "type": "secondType"
              },
              {
                "name": "thirdName",
                "type": "thirdType"
              },
              {
                "name": "fourthName",
                "word": "",
                "type": "fourthType"
              },
              {
                "name": "fifthName",
                "word": "fifth",
                "type": "fifthType"
              },
              {
                "name": "sixthName",
                "word": "sixth",
                "type": "sixthType"
              },
              {
                "name": "seventhName",
                "word": "  ",
                "type": "seventhType"
              },
              {
                "name": "eighthName",
                "word": "",
                "type": "eighthType"
              }
            ]
            """;
}

class JSONArray {

    private final List<Object> myArrayList;

    public JSONArray() {
        this.myArrayList = new ArrayList<>();
    }

    public JSONArray(JSONTokener x) {
        this();
        if (x.nextClean() != '[') {
            throw new IllegalArgumentException("A JSONArray text must start with '['");
        }
        if (x.nextClean() != ']') {
            x.back();
            for (; ; ) {
                if (x.nextClean() == ',') {
                    x.back();
                    this.myArrayList.add(null);
                } else {
                    x.back();
                    this.myArrayList.add(x.nextValue());
                }
                switch (x.nextClean()) {
                    case ';', ',':
                        if (x.nextClean() == ']') {
                            return;
                        }
                        x.back();
                        break;
                    case ']':
                        return;
                    default:
                        throw new IllegalArgumentException("Expected a ',' or ']'");
                }
            }
        }
    }

    public JSONArray(String source) {
        this(new JSONTokener(source));
    }

    public Object get(int index) {
        return this.myArrayList.get(index);
    }

    public JSONObject getJSONObject(int index) {
        return (JSONObject) get(index);
    }

    public String join(String separator) {
        int len = this.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i += 1) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public String toString() {
        return '[' + this.join(",") + ']';
    }

    String toString(int indentFactor, int indent) {
        int len = this.length();
        if (len == 0) {
            return "[]";
        }
        int i;
        StringBuilder sb = new StringBuilder("[");
        if (len == 1) {
            sb.append(JSONObject.valueToString(this.myArrayList.get(0)));
        } else {
            int newIndent = indent + indentFactor;
            sb.append('\n');
            for (i = 0; i < len; i += 1) {
                if (i > 0) {
                    sb.append(",\n");
                }
                sb.append(" ".repeat(Math.max(0, newIndent)));
                sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
            }
            sb.append('\n');
            for (i = 0; i < indent; i += 1) {
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

class JSONObject {

    private final Map<Object, Object> map;

    public JSONObject() {
        this.map = new HashMap<>();
    }

    public JSONObject(JSONTokener x) {
        this();
        char c;
        String key;

        if (x.nextClean() != '{') {
            throw new IllegalArgumentException("A JSONObject text must begin with '{'");
        }
        for (; ; ) {
            c = x.nextClean();
            switch (c) {
                case 0:
                    throw new IllegalArgumentException("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    x.back();
                    key = x.nextValue().toString();
            }

            c = x.nextClean();
            if (c == '=') {
                if (x.next() != '>') {
                    x.back();
                }
            } else if (c != ':') {
                throw new IllegalArgumentException("Expected a ':' after a key");
            }
            this.map.put(key, x.nextValue());

            switch (x.nextClean()) {
                case ';', ',':
                    if (x.nextClean() == '}') {
                        return;
                    }
                    x.back();
                    break;
                case '}':
                    return;
                default:
                    throw new IllegalArgumentException("Expected a ',' or '}'");
            }
        }
    }

    public JSONObject(Object bean) {
        this();
        this.populateMap(bean);
    }

    public String getString(String key) {
        return this.map.get(key).toString();
    }

    public int length() {
        return this.map.size();
    }

    private void populateMap(Object bean) {
        Class<?> klass = bean.getClass();

        boolean includeSuperClass = klass.getClassLoader() != null;

        Method[] methods = includeSuperClass
                ? klass.getMethods()
                : klass.getDeclaredMethods();
        for (Method value : methods) {
            try {
                if (Modifier.isPublic(value.getModifiers())) {
                    populateMapExtracted(bean, value);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    private void populateMapExtracted(Object bean, Method value) throws InvocationTargetException, IllegalAccessException {
        String name = value.getName();
        String key = "";
        if (name.startsWith("get")) {
            if ("getClass".equals(name) ||
                    "getDeclaringClass".equals(name)) {
                key = "";
            } else {
                key = name.substring(3);
            }
        } else if (name.startsWith("is")) {
            key = name.substring(2);
        }
        if (!key.isEmpty() &&
                Character.isUpperCase(key.charAt(0)) &&
                value.getParameterTypes().length == 0) {
            if (key.length() == 1) {
                key = key.toLowerCase();
            } else if (!Character.isUpperCase(key.charAt(1))) {
                key = key.substring(0, 1).toLowerCase() +
                        key.substring(1);
            }

            Object result = value.invoke(bean, (Object[]) null);
            if (result != null) {
                this.map.put(key, wrap(result));
            }
        }
    }

    public static String quote(String string) {
        if (string == null || string.isEmpty()) {
            return "\"\"";
        }
        char c = 0;
        int i;
        int len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = getAChar(string, c, i, sb);
        }
        sb.append('"');
        return sb.toString();
    }

    private static char getAChar(String string, char c, int i, StringBuilder sb) {
        char b;
        String hhhh;
        b = c;
        c = string.charAt(i);
        switch (c) {
            case '\\', '"' -> sb.append('\\').append(c);
            case '/' -> {
                if (b == '<') {
                    sb.append('\\');
                }
                sb.append(c);
            }
            case '\b' -> sb.append("\\b");
            case '\t' -> sb.append("\\t");
            case '\n' -> sb.append("\\n");
            case '\f' -> sb.append("\\f");
            case '\r' -> sb.append("\\r");
            default -> {
                if (c < ' ' || (c >= '\u0080' && c < '\u00a0') ||
                        (c >= '\u2000' && c < '℀')) {
                    hhhh = "000" + Integer.toHexString(c);
                    sb.append("\\u").append(hhhh.substring(hhhh.length() - 4));
                } else {
                    sb.append(c);
                }
            }
        }
        return c;
    }

    public static Object stringToValue(String string) {
        double d;
        char b = string.charAt(0);
        if ((b >= '0' && b <= '9') || b == '.' || b == '-' || b == '+') {
            if (string.indexOf('.') > -1 ||
                    string.indexOf('e') > -1 || string.indexOf('E') > -1) {
                d = Double.parseDouble(string);
                if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                    return d;
                }
            } else {
                long myLong = Long.parseLong(string);
                if (myLong == (int) myLong) {
                    return (int) myLong;
                } else {
                    return myLong;
                }
            }

        }
        return string;
    }

    public String toString() {
        try {
            Iterator<Object> keys = this.map.keySet().iterator();
            StringBuilder sb = new StringBuilder("{");

            while (keys.hasNext()) {
                if (sb.length() > 1) {
                    sb.append(',');
                }
                Object o = keys.next();
                sb.append(quote(o.toString()));
                sb.append(':');
                sb.append(valueToString(this.map.get(o)));
            }
            sb.append('}');
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String valueToString(Object value) {
        return quote(value.toString());
    }

    public static Object wrap(Object object) {
        Package objectPackage = object.getClass().getPackage();
        String objectPackageName = objectPackage != null
                ? objectPackage.getName()
                : "";
        if (objectPackageName.startsWith("java.") ||
                objectPackageName.startsWith("javax.") ||
                object.getClass().getClassLoader() == null
        ) {
            return object.toString();
        }
        return new JSONObject(object);
    }
}

class JSONTokener {

    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader) {
        this.reader = reader.markSupported()
                ? reader
                : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(String s) {
        this(new StringReader(s));
    }

    public void back() {
        if (this.usePrevious || this.index <= 0) {
            throw new IllegalArgumentException("Stepping back two steps is not supported");
        }
        this.index -= 1;
        this.character -= 1;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public char next() {
        int c;
        if (this.usePrevious) {
            this.usePrevious = false;
            c = this.previous;
        } else {
            try {
                c = this.reader.read();
            } catch (IOException exception) {
                throw new IllegalArgumentException(exception);
            }

            if (c <= 0) { // End of stream
                this.eof = true;
                c = 0;
            }
        }
        this.index += 1;
        if (this.previous == '\r') {
            this.line += 1;
            this.character = c == '\n' ? 0 : 1;
        } else if (c == '\n') {
            this.line += 1;
            this.character = 0;
        } else {
            this.character += 1;
        }
        this.previous = (char) c;
        return this.previous;
    }

    public String next(int n) {
        if (n == 0) {
            return "";
        }
        char[] chars = new char[n];
        int pos = 0;

        while (pos < n) {
            chars[pos] = this.next();
            if (this.end()) {
                throw new IllegalArgumentException("Substring bounds error");
            }
            pos += 1;
        }
        return new String(chars);
    }

    public char nextClean() {
        for (; ; ) {
            char c = this.next();
            if (c == 0 || c > ' ') {
                return c;
            }
        }
    }

    public String nextString(char quote) {
        char c;
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            c = this.next();
            switch (c) {
                case 0, '\n', '\r' -> throw new IllegalArgumentException("Unterminated string");
                case '\\' -> {
                    c = this.next();
                    switch (c) {
                        case 'b' -> sb.append('\b');
                        case 't' -> sb.append('\t');
                        case 'n' -> sb.append('\n');
                        case 'f' -> sb.append('\f');
                        case 'r' -> sb.append('\r');
                        case 'u' -> sb.append((char) Integer.parseInt(this.next(4), 16));
                        case '"', '\'', '\\', '/' -> sb.append(c);
                        default -> throw new IllegalArgumentException("Illegal escape.");
                    }
                }
                default -> {
                    if (c == quote) {
                        return sb.toString();
                    }
                    sb.append(c);
                }
            }
        }
    }

    public Object nextValue() {
        char c = this.nextClean();
        String string;

        switch (c) {
            case '"', '\'':
                return this.nextString(c);
            case '{':
                this.back();
                return new JSONObject(this);
            case '[':
                this.back();
                return new JSONArray(this);
            default:
        }

        StringBuilder sb = new StringBuilder();
        while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
            sb.append(c);
            c = this.next();
        }
        this.back();

        string = sb.toString().trim();
        if (string.isEmpty()) {
            throw new IllegalArgumentException("Missing value");
        }
        return JSONObject.stringToValue(string);
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}

@interface Service {
}

@interface Slf4j {
}
