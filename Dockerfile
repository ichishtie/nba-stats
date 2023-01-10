#FROM --platform=linux/amd64 amazoncorretto:17-alpine
FROM --platform=linux/arm64v8 arm64v8/amazoncorretto:17
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
COPY build/libs/nba-stats-scraper-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM --platform=linux/amd64 amazoncorretto:17-alpine
#VOLUME /tmp
#COPY build/libs/nba-stats-scraper-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080
