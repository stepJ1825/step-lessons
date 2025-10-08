## java.util.Date
| Класс/Тип       | Описание                                  | Пример использования         |
|-----------------|-------------------------------------------|------------------------------|
| `LocalDateTime` | Дата + время без привязки к зоне          | Расписание событий           |
| `LocalDate`     | Только дата                               | День рождения, праздник      |
| `LocalTime`     | Только время                              | Начало встречи               |
| `ZonedDateTime` | Дата + время + часовой пояс               | Международные события        |
| `Instant`       | Технические метки времени                 | Логирование, API             |
| `Duration`      | Продолжительность в секундах/наносекундах | Измерение интервалов времени |
| `Period`        | Разница между датами в годах/месяцах/днях | Расчет возраста              |

### Важно помнить:

* java.util.Date — неизменяемый, но не потокобезопасный в контексте форматирования.
* java.time.* — полностью неизменяемые и потокобезопасные.
* Все конвертации проходят через Instant — это "мост" между старым и новым API.
* При конвертации в/из LocalDateTime обязательно указывайте часовую зону, даже если используете системную.*

## java.util.Date
| Из какого типа  | В какой тип     | Код преобразования                                               |
|-----------------|-----------------|------------------------------------------------------------------|
| `Calendar`      | `Instant`       | `((GregorianCalendar) cal).toInstant()`                          |
| `Instant`       | `LocalDateTime` | `LocalDateTime.ofInstant(instant, zone)`                         |
| `LocalDateTime` | `Instant`       | `ldt.atZone(zone).toInstant()`                                   |
| `Instant`       | `Calendar`      | `GregorianCalendar.from(ZonedDateTime.ofInstant(instant, zone))` |
| `LocalDateTime` | `Calendar`      | `GregorianCalendar.from(ldt.atZone(zone))`                       |

### Важно помнить:

* Не все подклассы Calendar поддерживают прямую конвертацию. На практике почти всегда используется GregorianCalendar.
* GregorianCalendar — единственный стандартный подкласс Calendar, который реализует методы для работы с ZonedDateTime.
* При конвертации сохраняются и дата/время, и часовой пояс.

### Что почитать (посмотреть):
* [Как понять время? Java Date & Time API](https://www.youtube.com/watch?v=5QzLsYQRt0I)
* [Презентация из видео](https://drive.google.com/file/d/1vx0oE30EHOEp5_0kTKOjkajjO1nkAAOv/view?pli=1)




