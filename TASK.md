### ЗАДАЧА
1. Используя класс (желательно самый сложный) из задания
по послойной архитектуре
создать JSONShema.
2. Класс поместить в пакет [src/main/java/by/step/model](src/main/java/by/step/model)
3. Файл JSONShema поместить в [src/main/resources/schema](src/main/resources/schema)
4. В JsonSchemaValidator указать ссылку на схему из п.3
5. В [TaskController](src/main/java/by/step/controller/TaskController.java).validationTask 
заменить переменную BookFullDto на переменную своего типа.
6. Запустить приложение.
7. В Postman сформировать HTTP POST запрос и передать
валидный и невалидный JSON.