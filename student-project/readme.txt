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
