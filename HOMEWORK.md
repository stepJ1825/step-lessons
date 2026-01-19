<details>
<summary>Задание 1: Управление задачами (To-Do List) (Дима Гасперчук)</summary>

Цель: Реализовать консольное приложение для управления задачами с сохранением в JSON.

Требования:
- Модель: Task (id, title, completed: boolean, createdAt: LocalDateTime).
- Интерфейс TaskRepository с методами: getAll(), add(Task), markCompleted(int id).
- Реализация TaskRepositoryJSON — читает/пишет в tasks.json.
- Сервис TaskService с логикой: список невыполненных задач, подсчёт завершённых.
- Контроллер TaskController с методом listPendingTasks().

В application.properties:
- task.file.path=src/main/resources/tasks.json

В XML:
- Подключить PropertySourcesPlaceholderConfigurer.
- Внедрить путь к файлу через сеттер.
- Использовать init-method для инициализации Scanner.
</details>

<details>
<summary>Задание 2: Каталог фильмов (Анастасия Тарасова)</summary>
Цель: Приложение для поиска фильмов по жанру и году выпуска.

Требования:

- Модель: Movie (id, title, year, genre, rating).
- Интерфейс MovieRepository с методами: findByGenre(String), findByYearRange(int start, int end).
- Реализация MovieRepositoryJSON — работает с movies.json.
- Сервис MovieService — фильтрация, сортировка по рейтингу.
- Контроллер MovieController — вывод фильмов ужасов, выпущенных после 2000 г.

В application.properties:
- movie.data.file=src/main/resources/movies.json
- app.rating.threshold=7.0

В XML:
- Внедрить путь к файлу и порог рейтинга (double) через сеттеры.
- Использовать коллекцию (например, List<String> допустимых жанров) через <list>.
</details>

<details>
<summary>Задание 3: Система учёта студентов (Дима Дикун)</summary>
Цель: Управление списком студентов и их оценками.

Требования:

- Модели: Student (id, name, email), Grade (studentId, subject, score).
- Интерфейсы: StudentRepository, GradeRepository.
- Реализации: StudentRepositoryJSON, GradeRepositoryJSON.
- Сервис AcademicService — методы: getAverageScore(String studentName), getTopStudents(int limit).
- Контроллер AcademicController — вывод среднего балла студента.

В application.properties:
- data.files=src/main/resources/students.json,src/main/resources/grades.json

В XML:
- Использовать SpEL для разделения data.files на два пути.
- Создать два репозитория с разными dataPath.
- Внедрить оба репозитория в AcademicService через конструктор.
</details>

<details>
<summary>Задание 4: Погодное приложение (симуляция) (Иван Свидинский)</summary>
Цель: Симуляция получения погоды из "внешнего источника" с кэшированием.

Требования:
- Модель: WeatherData (city, temperature, timestamp).
- Интерфейс WeatherProvider с методом getWeather(String city).
- Реализация MockWeatherProvider — возвращает фиктивные данные (можно хранить в Map).
- Интерфейс WeatherCache с методами put(String city, WeatherData), get(String city).
- Реализация SimpleWeatherCache — на основе HashMap.
- Сервис WeatherService — сначала проверяет кэш, потом провайдер.
- Контроллер WeatherController — вывод погоды в Минске.

В application.properties:
- weather.cities=Minsk,Gomel,Brest
- cache.enabled=true

В XML:
- Внедрить список городов через <list>.
- Использовать factory-method для создания LocalDateTime.now() как бина.
- Настроить WeatherService через конструктор (WeatherProvider, WeatherCache).
</details>

<details>
<summary>Задание 5: Интернет-магазин (каталог товаров) (Илья Иваныгин)</summary>
Цель: Поиск и фильтрация товаров по цене и категории.

Требования:
- Модель: Product (id, name, category, price, inStock: boolean).
- Интерфейс ProductRepository с методами: findByCategory(String), findCheapProducts(double maxPrice).
- Реализация ProductRepositoryJSON — работает с products.json.
- Сервис CatalogService — поиск, подсчёт товаров в наличии.
- Контроллер CatalogController — вывод всех электроники до 500 руб.

В application.properties:
- product.file=src/main/resources/products.json
- currency.symbol=BYN

В XML:
- Внедрить путь к файлу и символ валюты через сеттеры.
- Создать бин CurrencyFormatter с конструктором (String symbol).
- Внедрить CurrencyFormatter в контроллер.
- Использовать destroy-method (например, close()) — даже если он пустой.
</details>

---------------------------------
Как сдавать.
1. Запушить в репозиторий (можно в новый) ветку 'develop' без решения.
2. Решение оформить в отдельной ветке 'feature/xml-config', ответвившись от 'develop'.
3. Запушить решение.
4. Создать пул-реквест 'feature/xml-config'>'develop'. <br> В настройках github необходимо установить запрет слияния веток без аппрува.
5. Сбросить пул-реквест в группу в ТГ и получить аппрув или замечания.
6. После получения аппрува выполнить слияние.