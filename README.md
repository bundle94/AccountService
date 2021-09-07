<h3>SETTING UP ACCOUNT SERVICE</h3>
1) I added MSSQL server</code> images to the <code>Docker-compose.yml</code> file, just incase you don't have them running on your local already.
   to start them up kindly run the following command : <code>docker-compose up -d</code> and <code>docker ps -a</code> to ensure they are actually running.


2) Run the command <code>mvn spring-boot:build-image</code> to build a docker image of the account service.


3) Run the command <code>docker run -it -p7070:7070 accountservice:0.0.1-SNAPSHOT</code> to run the built docker image. This will map the service to port <code>7070</code> on your local machine/server.


4) Service will be running on <code>http://<HOST/SERVER_IP>:7070/accountservice
   NOTE: The <code>HOST/SERVER_IP</code> will be the <code>IP</code> of your local machine/box in this case.

Kindly reach out to if you encounter any issue running this service. <b>Email: </b><code>akobundumichael94@gmail.com</code>