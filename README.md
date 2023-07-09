# Web-Application

Проект задумывался как сайт-агрегатор на котором могли бы предлагать свои услуги сервисы по ремонту оборудования для майнинга.
На сайте реализован механизм регистрации с использованием rEcaptcha и высылкой регистрационного кода на электронную почту.
С помощью инструмента для миграции FlyWay осуществляется генерация сущностей в БД. 
В приложении реализован сбор статистики in-memory.

Краткий демонстрационный ролик на YouTube по функциональности сайта доступен по ссылке: https://youtu.be/X_xSrZIGGFM


# Установка и настройка на локальной машине разработчика (dev).
На локальной машине разработчика потребуется наличие запущенной MySQL, а так же JDK 11.0.15 или новее и Maven.

1. Клонируйте репозиторий на свой локальный компьютер
2. В конфигурационном файле <b>src/main/resources/common.properties</b> задайте свой ключ сайта <b>recaptcha.secret</b> для подключения reCaptcha от google
3. В конфигурационном файле <b>src/main/resources/mail.properties</b> задайте хост, логин и пароль своего почтового аккаунта
4. С помошью maven соберите проект. (ВАЖНО! Соберите с использованием профиля сборки <b>dev</b> )
5. Запускайте <b>target/somesite3.jar</b> любым удобным сопсобом


# Установка и настройка в контейнере (prod).
Для запуска потребуется наличие инстумента сборки Maven и контейнеризатор приложений Docker

1. Клонируйте репозиторий на свой локальный компьютер
2. В конфигурационном файле <b>src/main/resources/common.properties</b> задайте свой ключ сайта <b>recaptcha.secret</b> для подключения reCaptcha от google
3. В конфигурационном файле <b>src/main/resources/mail.properties</b> задайте хост, логин и пароль своего почтового аккаунта
4. С помошью maven соберите проект. (ВАЖНО! Соберите с использованием профиля сборки <b>prod</b> ) Проверьте что сборка появилась <b>target/somesite3.jar</b> 
5. Выполните <b>_docker compose up_</b> в директории проекта


# Ньюансы
Путь для изображений сайта подгружается из переменной <path_to_picture>  и зависит от сборки.
