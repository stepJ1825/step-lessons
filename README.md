Часть 1. Тестовые вопросы.
◯ - 1 вариант ответа
□ - 1 или более вариантов ответа
https://forms.gle/UT9ysKLkqBqDBW8C6

Часть 2. Вопросы, которые нужно ответить текстом.
Плюс нужно решить одну из двух задач в конце.
https://forms.gle/bijUWYexhygh5nPy5

class Box<T> {
  T value;
  public Box (T value) {
    this.value = value;
  }
}
public static void main(String[] args) {
  Box<? super String> b1 = new Box<> (123);
  Box<? extends String> b2 = new Box<>("123");
  Box<? extends Number> b3 = new Box<>(123);
  Box<? super Number> b4 = new Box<>(123L);
}