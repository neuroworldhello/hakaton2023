# Устанавливаем базовый образ с JDK 17
FROM openjdk:17-jdk-bullseye

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем сборку проекта в контейнер
COPY build/libs/*.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "app.jar"]
