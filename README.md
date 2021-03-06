<h3>SETTING UP ACCOUNT SERVICE</h3>

1) To get started, I have Implemented swagger ui for this project to start up the application on local make sure you have created an <code>MSSQL</code> database with name <code>blueharvet</code>. 
In the root directory of the application run <code>mvn spring-boot:run</code>. This will run the Initial migrations, create a customer and set the environment for both account service and transaction service. 
    once application is up and running navigate to http://localhost:7070/accountservice/swagger-ui.html#/ to see the <code>Swagger</code> of the  account service API
   
NOTE: I used <code>Flyway</code> for my database Migrations 

<h5>RUNNING WITH DOCKER</h5>


1) I added MSSQL server</code> images to the <code>Docker-compose.yml</code> file, just incase you don't have them running on your local already.
   to start them up kindly run the following command : <code>docker-compose up -d</code> and <code>docker ps -a</code> to ensure they are actually running.


2) Run the command <code>mvn spring-boot:build-image</code> to build a docker image of the account service.


3) Run the command <code>docker run -it -p7070:7070 accountservice:0.0.1-SNAPSHOT</code> to run the built docker image. This will map the service to port <code>7070</code> on your local machine/server.


4) Service will be running on <code>http://<HOST/SERVER_IP>:7070/accountservice</code>
   NOTE: The <code>HOST/SERVER_IP</code> will be the <code>IP</code> of your local machine/box in this case.

Kindly reach out to if you encounter any issue running this service. <b>Email: </b><code>akobundumichael94@gmail.com</code>