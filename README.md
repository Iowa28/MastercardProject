# Тестовое задание "Разработка Системы Управления Банковскими Картами"

## Локальный запуск
Локальный запуск через docker-compose:
1. Сборка jar-файла: <br/>*mvn clean package -DskipTests*
2. Создание docker-образа: <br/>*docker build -t mastercard .*
3. Запуск: <br/>*docker-compose up*

После запуска приложение должно быть доступно по адресу:<br/>
*http://localhost:8080*<br/>
Swagger:<br/>
*http://localhost:8080/swagger-ui/index.html*

## Локальный запуск (без docker-compose)
Для запуска вручную необходимо передать 3 параметра: <br/>
*DB_URL* - адрес к локально запущенной бд <br/>
*DB_USERNAME* - логин к бд <br/>
*DB_PASSWORD* - пароль к бд <br/>

## Данные админа
В бд по-умолчанию создается пользователь с ролью администратора.<br/>
Почта: *admin@mail.com*<br/>
Пароль: *admin*
