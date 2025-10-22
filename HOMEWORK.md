### Задание:

Написать SQL скрипты:
1. Создать таблицы в БД (с наличием primary key, с наличием foreignkey) 
по назначенному заданию из книги "Java From EPAM"
2. Наполнить таблицы тестовыми данными.
3. Написать SQL запросы для выполнения подзаданий 

----------------------------
### Для сдачи необходимо сбросить все скрипты в одном sql файле
----------------------------
### Пример выполнения п.3
Задача: Вывести даты, когда в заданном регионе шел снег и температура была ниже заданной отрицательной.
Ответ:
        select w."date"  from weather w
        join precipitation p on w.precipitation = p.id
        join my_region mr on w.my_region = mr.id
        where mr."name" = 'minsk region'
        and p.precipitation_name = 'SNOW'
        and w.temperature < 0