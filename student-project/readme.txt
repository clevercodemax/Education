JDBC - Java Database Connectivity
driver для подключения к базам данных на основе переходного периода

1. JDBC -> ODBC (Open DataBase Connectivity) бридж-стандарт
2. Native JDBC чисто на Java подключение (С, Asm)
3. JDBC Server
4. JDBC - Pure Java

1. Загрузить JDBC драйвер - реализация java.sql.Driver для опред.типа базы данных
2. Получить соединение - реализация java.sql.Connection
3. Создать запрос - реализация Statement, PreparedStatement, CallableStatement
4. Исполнить запрос - ResultSet
5. Выбрать данные из ResultSet
6. Освободить ресурсы - close

String name, curator, spec;
String sql =
INSERT INTO st_group (groupName, curator, speciality) Values ()

INSERT INTO so_student_order (
student_order_date,
h_surname, h_givenname, h_patronymic, h_date_of_birth,
h_passport_seria, h_passport_number, h_date_issue, h_date_expire,
w_surname, w_givenname, w_patronymic, w_date_of_birth,
w_passport_seria, w_passport_number, w_date_issue, w_date_expire)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

INSERT INTO so_student_order_child (
student_order_id, c_surname, c_givenname, c_patronymic,
c_date_of_birth, c_birth_document)
VALUES (?, ?, ?, ?, ?, ?)

DATE
TIME
TIMESTAMP
DATETIME - дата/время без часового пояса

Transaction (commit/rollback)

1. Создать приложение web c определенной структурой каталогов
2. Собрать все в архив - .war (Web Archive)
3. Установить web-приложение на web-server

API - Application Programming Interface

GET /web-ui-1.0/simple?name1=value1&name1=value2 HTTP/1.1
Host:
Content-type: html

1. Web-Server преобразует текст в набор объектов
2. Web-Server должен найти Web-компонент
3. Web-Server передает управление web-компоненту

Виды компонентов
1. Servlet - class HttpServlet
LifeCycle:
- init
- service
- destroy

Как найти компонент
1. ContextRoot (ContextPath) - строка под которой регистрируется приложение
2. url-pattern


1. web.xml - класс <-> url-pattern
2. аннотация


http://localhost:8080/web-ui-1.0/simple
http://localhost:8080/web-ui-1.0/simple?name=Anton - с параметром


MVC - Model, View, Controller
