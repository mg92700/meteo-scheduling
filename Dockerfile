# Utilisez une image de base Java 21 basée sur Debian
FROM openjdk:21-jdk

# Définir l'argument pour le fichier JAR
ARG JAR_FILE=target/*.jar

# Copier le fichier JAR dans l'image Docker
COPY ${JAR_FILE} app.jar

# Exposer le port que votre application utilise
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
