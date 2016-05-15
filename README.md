# SampleApp

Technologies used:
- Java with Spring framework.
- Tomcat6 Web Server
- Maven Build Automation Tool

Compiled WAR file name: TopFiveWebApp.war

Steps to access/Brief description of the Web Application:

(1) Deploy the WAR file to Tomcat6 web server.

(2) Start tomcat web service.

(3) Access the Application using the path - http://localhost:8080/topfiveweb/topfivewebvisit

(4) The Application will attempt to clear & re-populate the MySQL database table (topfivewebdb.TOPFIVEWEB) with all the Data found in the data.csv. 
The Application is using Spring Batch for the parsing of CSV and data population into MySQL DB. *NOTE: This behaviour is meant purely for demo purpose.

(5) The Application will then display a list of Top 5 websites ranking based on ALL the websites found in the MySQL database table (topfivewebdb.TOPFIVEWEB).

