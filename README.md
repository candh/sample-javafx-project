# sample-javafx-project (Phonebook)
This is a sample maven project mainly just for guidance on how to structure a simple javafx project. This does not goes into how would you deploy your app. Although that does gets a lot easier if its structured right.
There are a lot of ideas out there, and honestly, some of them are over engineered. This, in my opinion hits the sweet spot.

![phonebook-screenshot](https://i.postimg.cc/xdFGgPzB/Screen-Shot-2019-03-29-at-5-12-38-PM.png)

# Compile
You can compile and execute via many different strategies. If you just want to compile, you can do

```bash
mvn compile
```
# Run
I recommend doing this:

```bash
mvn compile exec:java -Dexec.mainClass="com.phonebook.App" -T4 -Dmaven.test.skip=true
```

This doesn't create jar file which I think takes some seconds, and also don't want. Also speeds up the process utilizing multiple threads.
If you want to create the jar file, you can do. 
```bash
mvn clean package
```
You can run just tests with:

```bash
mvn test
```
Another way to do compile and run the project is to use the [`javafx-maven-plugin`](https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc/7.2.1.jre8). You'll have to include that in the pom.xml. This project includes it, just to show you where to put it.

Then you can compile and run using:

```bash
mvn jfx:run
```

# Dependencies
This projects connects to a Microsoft SQL Server and fetches data. The JDBC Driver for that is available in the [maven repository](https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc).
To install it, I just had to add it as a runtime dependency in pom.xml.

# Why Maven? 
Maven is a build system for Java. Couple of reasons why I used Maven:

  + Maven binary is smaller than Gradle.
  + intelliJ builds make the CPU hot. After just two or three builds within 2 or 3 minutes of each other, the CPU gets hot and the fans start to spin. I don't like fans spinning for such a trivial job.
  + It's easy to learn. Trust me. You just need to spend 2 or 3 hours reading about it and then your whole java life will be much easier.
  + Can't get better than the Maven Repository.

# Project Structure
The project structure is really simple. Maven does most of the scaffolding. Then you need 5 directories inside your main pacakge.

  + config — for private static final things
  + controllers — JavaFX controllers
  + dao — Data Access Objects. Takes care of the nitty gritty of SQL Queries.
  + models — for the M in MVC. Represents Entities in your database. Or not, up to you.
  + services — for SQL connections and other services

And 3 directories inside your resources directory (fxml, css and images), which you do have to create. Although maven takes care of copying the resources to the target directory itself. 

# TODOS
  + Finish the implementation of all the methods in the DAO
  + Searching contacts
  + Button to delete the contacts
