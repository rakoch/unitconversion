# Unit Conversion Project with REST Endpoints [& eventually a Web Project or a separate front-end container]
## Assumptions
- you can install & have some knowledge with JDK, Maven, Docker Desktop, and an IDE like Eclipse

## Dependencies
- OpenJdk 11 OR Amazon Corretto 11 JDK
- maven 4.0+
- Docker desktop installed 
- other things listed in pom.xml including an string-to-expression library to execute math functions

## Things to note
- The project is a Unit Conversion containerized Spring Boot service with the intent to support teachers entering in student Unit Conversion answers and receiving answers back.
- This is an exercise in Spring Boot, JPA, docker, docker-compose, and eventually React & kubernetes.
- Right now the app start-up recreates all tables and drops all data .
- The unit conversion spring boot app build is dependent on a mysql container to be up and running since the maven build creates the tables right now and run tests.
- The test infrastructure is set up but a lot of effort has not gone into testing yet.


## TODO
- make better input mechanism - cli that accepts spreadsheet or front end web ui that allows spreadsheet import or table entry component
- introduce authentication/login
- more/better tests
- remove db uname/passwords from config files and set from environment
- javadoc needs to be completed along with code comments and cleanup
- probably should move some classes around into subpackages to clean things up from that perspective.
- status reporting for validation and conversion among different abstractions could use cleaned up - used the same result status for the most part and this got messy - maybe should have independent status objects for different abstraction layers.
- don't rely on exceptions for validation - typically a bad practice from a performance & resource perspective but I took some shortcuts due to the amount of time spent
- decision to use Command factory pattern outside of Spring for cleaner code caused some complexity in that I had to write a SpringContext class which made the test also dependent on Spring application context - so need to investigate best practice/cleaner way 
- Could cache unit conversion commands retrieved from command factory and re-use rather than always get a new one from factory based on the unit pair.
- updated docker and the check for verifying db up before starting application container is not working all of the time now. Needs to be investigated.
- used database which was not clear if necessary or not but wanted to show docker/container and database use, the intent being to make it more expandable 
- re-think use of Enums - typically like to use enums instead of passing strings around but decided to partially expose Strings as input parameters given the requirements (wanted to be able to report invalid for any bad string units rather than just prevent bad strings from being used)
- Use of Enums and Database tables somewhat representing them makes things messy/complicated to a degree - see UnitConversionFactory enum-to-string and enum-to-model cache mess - maybe should not have made unit types in db or maybe should have just skipped the enums 
- expose endpoints/api to update unit tables and conversion tables

## How to remove all previous docker containers and images (assuming you can do this in your local environment)
- `docker stop $(docker ps -aq)`
- `docker system prune -a` or `docker container prune` & `docker image prune -a`


## How to Run
- Either use docker-compose or start container(s) manually
- Have to download source from [https://github.com/rakoch/unitconversion.git](https://github.com/rakoch/unitconversion.git)
- use master branch
- open a terminal or command shell
- cd to the project root for all options below--e.g., `cd ~/workspace/unitconversion`
- Make sure docker desktop is running and cleaned out of this project's containers and images
- in terminal:  `docker pull mysql`

### You have several options on how to run and Test local rest End points
#### Use docker-compose to start everything 
<b>Build Unit Conversion App - TODO make this part of deploy</b>

- get mysql: `docker pull mysql`
- start test db: `docker run --name mysql -p 127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- build app: `mvn install`

<b>Remove all previous containers and images (assuming only working on this app)</b>

- `docker stop $(docker ps -aq)`
- `docker system prune -a` or `docker container prune` & `docker image prune -a`

<b>Run docker-compose</b>

- NOTE this takes several minutes because the app does not start until mysql health check succeeds.
 `docker-compose up --build -d`
- Test with swagger: [http://localhost:8080/unitconversion/swagger-ui.html](http://localhost:8080/unitconversion/swagger-ui.html)

#### Run app as Java in Eclipse AFTER starting mysql container
- remove any previous mysql and unit conversion docker containers and images
- `docker run --name mysql -p  127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- Run from within eclipse by using ‘Run as -> Java Application’ on the UnitconversionApplication.java
- Test with swagger: [http://localhost:8080/unitconversion/swagger-ui.html](http://localhost:8080/unitconversion/swagger-ui.html)

#### Run app from command line using spring-boot:run AFTER starting mysql container
- remove any previous mysql and unit conversion docker containers and images
- `docker run --name mysql -p  127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- start the app with this command: ./mvnw spring-boot:run
- Test with swagger: [http://localhost:8080/unitconversion/swagger-ui.html](http://localhost:8080/unitconversion/swagger-ui.html)

#### Run app in docker container using DockerFile
- remove any previous mysql and unit conversion docker containers and images
- `docker run --name mysql -p  127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- cd to the project root--e.g., ~/workspace/unitconversion
- `mvn install`
- Test run of application if you want: java -jar target\unitconversion-0.0.1-SNAPSHOT.jar
- `docker build  -t unitconversion:v1 .`
- `docker run -it -p8080:8080 unitconversion:v1`
- Test with swagger: [http://localhost:8080/unitconversion/swagger-ui.html](http://localhost:8080/unitconversion/swagger-ui.html)

#### Run in docker container using Buildpack through spring-boot:build-image
- remove any previous mysql and unit conversion docker containers and images
- `docker run --name mysql -p  127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- Deploy to Docker using Spring Boot Buildpack
- `mvnw spring-boot:build-image`
- `docker run -it -p8080:8080 unitconversion:0.0.1-SNAPSHOT`
- Test with swagger: [http://localhost:8080/unitconversion/swagger-ui.html](http://localhost:8080/unitconversion/swagger-ui.html)



### In Swagger
- in the browser enter the link for openapi/swagger ui and try out the endpoint (click try it, then blue execute button): http://localhost:8080/swagger-ui.html

## How to run Integration Tests (No real tests yet)
- Have to download source from https://github.com/rakoch/unitconversion.git
- use master branch
- open a terminal or command shell
- cd to the project root--e.g., `cd ~/workspace/unitconversion`
- remove any previous mysql and unit conversion docker containers and images
- `docker run --name mysql -p  127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD="password" mysql &`
- type in command: `mvn test`
- should see results: Tests run: ?, Failures: 0, Errors: 0, Skipped: 0

## Notes on initial project creation
- use spring initilzr  - web, actuator, devtools - see pom.xml
- generate
- unzip generated zip file and place in workspace
- cd to directory and `git init`create or copy .gitignoregit add .
- git commit -m "first commit"
- go to github and create a new private repo with nothing in it (name unitconversion)
- At the top of your GitHub repository's Quick Setup page, click  to copy the remote repository URL.
- git remote add origin https://github.com/rakoch/unitconversion.git
- git remote -v
- git push origin master
