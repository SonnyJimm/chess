FROM maven:3.8.7-openjdk-18-slim AS build
# Create a new app directory in the container for the application files
RUN mkdir /app
# Copy the compiled executable files/folders from host machine filesystem to the container/image's filesystem
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM maven:3.8.7-openjdk-18-slim
# Define the command to run the app when the contain is started
WORKDIR /app

COPY --from=build /app/target/chessGame-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "./chessGame-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080

