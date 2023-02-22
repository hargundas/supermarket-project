# Use an official Java runtime as a parent image
FROM openjdk:11
  # Set environment variables
ENV INVENTORY_FILE inventory.csv
ENV COMMANDS_FILE input.txt

  # Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app
#COPY bin /app/bin
#COPY function_spec /app/function_spec


  # Run Maven to build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

  # Set the startup command to run the project

COPY target/supermarket-project-1.0.0.jar /app/supermarket-project-1.0.0.jar
CMD ["java", "-cp", "/app/supermarket-project-1.0.0.jar", "Supermarket", "$INVENTORY_FILE", "$COMMANDS_FILE"]


# to build docker image
# docker build -t inventory-market .
# to run
# docker run -it --entrypoint /bin/bash inventory-market