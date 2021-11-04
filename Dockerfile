FROM openjdk:17
EXPOSE 8080
ADD target/pet-project.jar pet-project.jar
ENTRYPOINT ["java","-jar","/pet-project.jar"]
