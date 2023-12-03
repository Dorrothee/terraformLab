# Use an official OpenJDK runtime as a parent image
FROM openjdk:17
# Set the working directory in the container
WORKDIR /app
# Copy the compiled Java application JAR file into the container at /app
COPY target/lab3-1.0-SNAPSHOT.jar /app/PriorityQueueNote.jar
# Specify the command to run your application
CMD ["java", "-jar", "PriorityQueueNote.jar"]