# Deal Migration


deals-migration is a simple project that uploads large records of deals assessment,uploads in few seconds and creates a summary
and track of successful records and failed or invalid csv row records . It also has an in built plugin [data-generator-maven-plugin](https://github.com/johnsoneyo/data-generator-plugin) that generates a demo
csv file of assumed illegal records and valid records . It is based of java frameworks as ;

```sh
Spring Boot
Maven
Java 8
Flyway DB (Schema Versioning)
Log4j2 (Logging)

```

# Docker Setup
- First pull the image , docker pull johnson3yo/deal-migration
- Create Image of mysql:latest  : docker run --name dealdb2 -e MYSQL_ROOT_PASSWORD=freaks03 -e MYSQL_DATABASE=demomigration -e MYSQL_USER=root -e MYSQL_PASSWORD=freaks03 -d mysql:latest
- Next create an spring container from the image and link to mysql dealdb2 container docker run -p 8085:8080 --name deal-migration --link dealdb2:mysql -d deal-migration
- Access application on port 8085 :smile


To Run project simply git clone https://github.com/johnsoneyo/data-generator-plugin.git 
- cd data-generator-plugin
- mvn clean install

This should install in your local maven repository.

Secondly  git clone https://github.com/johnsoneyo/deals-migration.git

 modify application.properties to change database schema configuration (spring.datasource.url=jdbc:mysql://localhost:3306/demomigration?autoReconnect=true&useSSL=false)
- configure the csvGenerationPath to specify where a generated csv file will be dumped
- configure data-generator plugin to specifiy a start and variance value, by default the 1st 100,000 is configured this way
```plugin 
  <plugin>
                <groupId>com.bloomberg</groupId>
                <artifactId>datagenerator-maven-plugin</artifactId>
                <version>1.0</version>
                <configuration>
                    <csvGenerationPath>/home/johnson3yo/csv/gen/</csvGenerationPath>
                    <start>1</start>
                    <variance>1</variance>
                </configuration>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <!-- Package as an executable jar/war -->
            <plugin>

```
the next 200,000 will be configured as start = 100001,and variance =2 , i.e

- After that run mvn-spring-boot:run to start application , browse to http://localhost:8080/ 
- Have fun uploading :smile:
