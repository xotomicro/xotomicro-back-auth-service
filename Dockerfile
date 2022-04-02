FROM adoptopenjdk/openjdk11:ubi

COPY /target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT if [[ "${ENV}" = "development" ]] ; then ["./mvnw", "spring-boot:run", "-Djwt.token.secret-key=${SECRET_KEY}", "-Dspring-boot.run.profiles=${PROFILE}"] ; else ["java","-Dspring.profiles.active=${PROFILE}","-jar","app.jar"] ; fi