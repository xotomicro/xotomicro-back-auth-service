FROM adoptopenjdk/openjdk11:ubi

COPY /target/*.jar app.jar
EXPOSE 8082

CMD java -Djwt.token.secret-key=TEST -Dspring.profiles.active=docker -jar app.jar