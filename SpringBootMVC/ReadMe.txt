Run below command against the pom.xml to build the jar and the coverage report.
mvn clean install

Coverage report will be available after running the above command in the below path:
\target\site\jacoco\index.html

Run the below command to up the application.
mvn spring-boot:run

Upload URL:
http://localhost:8080/

REST Services:
http://localhost:8080/getAllValidData - Get all the valid data from the database.
http://localhost:8080/getAllValidDataByFileName (Request Body: <File Name> (Including extension)) - Get all the valid data by filename from the database.
http://localhost:8080/getAllInvalidData - Get all the invalid data from the database.
http://localhost:8080/getAllInvalidDataByFileName - (Request Body: <File Name> (Including extension)) - Get all the invalid data by filename from the database.
http://localhost:8080/getAllCountDeal - Get all count deal from the database.

To know the entity and database structure, check package:
com.springboot.mvc.entity

Note: For performance optimization Java 8's native mapping feature has been used to map file to objects, regx expression is not been used as it is less efficient.
Running this application on a powerful server can make a big difference.