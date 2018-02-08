# Training Ecommerce Project
Training Ecommerce Project is the training base web application through which we are going to train our developers and make them comfortable with the technologies we are going to use in our real Ecommerce Project.

#### Tools and Technologies
- Java 8
- Angular 2
- Node 6
- Spring Boot 1.5
- Spring MVC 4
- Tomcat 8
- MySQL 5.6
- Maven 3.5
- Git 2.13

## Project Setup

### 1. _Cloning the Project_
1. Download git from _[here](https://git-scm.com/downloads)_ and install it on your operating system.
2. Execute following command in your cmd/terminal to clone the project from github to your local disk.
```sh
    $ git clone https://github.com/nisum-inc/training-ecommerce-project.git
```
3. After cloning, ***training-ecommerce-project*** folder will be created in your current working directory.
4. Switch to branch 

```sh
Git checkout -b sprint-9 origin/sprint-9 
```

### 2. _Setup Server Application_
To run the server application follow the following instructions.
##### 1. MySQL:
1. Download and install MySQL from [here](https://www.mysql.com/downloads/)
2. Open your cmd/terminal and execute following command to login in to mysql command line.
```sh
    $ mysql -u your_username -p
```
3. Now create a database named ***"ecommerce_app"***, execute following command to do that.
```sh
    $ create database ecommerce_app;
```
##### 2. Cassandra Installation on Windows:
pre-requsites
1.make sure JAVA_HOME added in enviroment variables with correct path
2.download cassandra from http://cassandra.apache.org/download/
3.set the CASSANDRA_HOME to enviroment variables 
4.set the %CASSANDRA_HOME%\bin to PATH variable
example ***"CASSANDRA_HOME  : E:\Cassendra\apache-cassandra-3.11.0"***
go to cmd run command
to test if cassandra is running properly
```sh
C:\>cassandra
```

##### 3.Using cqlsh with Cassandra:

1.Install python 2.7 on your system
https://www.python.org/download/releases/2.7/

2.Set  PYTHON2 to enviroment variables 
3.Set  %PYTHON2% to PATH variable
example ***" PYTHON2 : C:\Python27"*** 

4.Add PYTHON_SCRIPTS to enviroment variables also add PYTHON_SCRIPTS to PATH variable.
example PYTHON_SCRIPTS : C:\Python27\Scripts
5.Download pyreadline 2.1 from  https://pypi.python.org/pypi/pyreadline
6.Download the MSI Windows installer version 
run cqlsh using 
```sh
C:\>cqlsh
```
After running the cqlsh. 

7.Now create a keyspace named ***"ecommerce_app"***, execute following command to do that.

```sh
CREATE KEYSPACE ecommerce_app WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
```

##### 4.Cassandra  Migration:

1.Add .cql script files in db/cassandra folder for migration.
File name should be in this format :

V<major_version>_<minor_version>__<file_name>.cql 

2.When creating scripts for the cassandra tables dont append the keyspace name with the table.

3.Make sure key_space mentioned in the application.properties file is created in the cassandra 
and cassandra is up and	running.

4.If you are running the application after taking the pull from git 
with migration related update for the first time drop your existing keyspace mentioned in application.properties and recreate it again.

5.If in any case your script migration fails you will also have to drop the keyspace and recreate it again.

execute the following command
```sh
DROP KEYSPACE ecommerce_app ;
```

##### 5. Maven:
1. Download and install Maven from [here.](https://maven.apache.org/download.cgi)
2. Open your cmd/terminal and navigate to the the folder ***training-ecommerce-project/app-server*** and enter following command.
```sh
    $ mvn install
```
3. You have to wait for few minutes till maven downloads and setup the project.
4. Now execute the following command to run the project.
```sh
   $ mvn spring-boot:run
```

### 6. Setup Client Application
To run the client application follow the following instructions.
#### 1. Node and NPM:
1. Download and install node from [here](https://nodejs.org/en/download/), ***npm*** should be install with node too.
2. To test that node is installed successfully execute following command in your cmd/terminal, it will show you the node version.
```sh
    $ node -v
```
3. To test that npm is installed successfully execute following command in your cmd/terminal, it will show you the npm version.
```sh
    $ npm -v
```

#### 7. Backbone with Handlebars

please install the following to run the client side application built on backbone and handlebars

Bower:
```sh
  $ npm install -g bower
```
Application Node Modules:
```sh
  $ npm install
```
Client Side Libraries
```sh
  $ bower install
```
	
#### 8. Running the Server
Use the following command to run the node server with the help of ExpressJs
```sh
  $ grunt
```
OR
```sh
  $ node app.js
```

Open your browser and goto ***[localhost:3000](http://localhost:3000)***, You should see a Home page of application.