1. Установить TCP/IP соединение
2. Посылать HTTP-запросы
 - get
 - post
 - put
 - delete
 - head
 - trace
 - option
 - connect


GET /start?page=18&size=50&locale=ru HTTP/1.1
User-Agent: Mozilla
Accept: xml

POST /start HTTP/1.1
Content-length:25
Accept: xml

page=18&size=50&locale=ru


1. Команда
2. Имя ресурса
3. Версия протокола
4. Headers
5. Параметры
- в командной строке - GET
- в теле запроса - POST, PUT


URI URL URN
URI - Uniform resource id - http://host:port/start
URL - Uniform resource locator - http://host:port
URN - Uniform resource name - /start

1. Tomcat - Web-Server - Servlet + JSP
2. WildFly - Application Server - Java EE
3. IBM WebSphere - Application Server - Java EE
4. Oracle WebLogic - - Application Server - Java EE

Установить и запустить TomCat
1. Скачать zip
2. Установить (проверить наличие) переменной среды JAVA_HOME

http://localhost:8080/
