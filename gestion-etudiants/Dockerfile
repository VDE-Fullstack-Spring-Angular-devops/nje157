FROM maven:3.9.9-eclipse-temurin-17 AS build
COPY pom.xml .
COPY src/ src/
RUN mvn -f pom.xml -Pdev clean package -DskipTests

FROM eclipse-temurin:17-jre AS run
RUN useradd admin
USER admin
COPY --from=build /target/gestion-etudiant.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]