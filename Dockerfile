# В качестве родительского образа используется образ ubuntu-server с предустановленными openjdk8, mysql, googleChrome
FROM romanyakit/somesite:v4

# Копирование файла somesite3.jar в контейнер
COPY /target/somesite3.jar /home/somesite3.jar


# Запуск приложения
CMD java -jar /home/somesite3.jar
