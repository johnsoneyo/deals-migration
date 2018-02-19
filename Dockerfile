FROM openjdk:8
ADD target/deals-migration-1.0.jar deals-migration-1.0.jar
EXPOSE  8085
ENTRYPOINT ["java","-jar","deals-migration-1.0.jar"]
