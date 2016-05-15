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
The Application is using Spring Batch for the parsing of CSV and data population into MySQL DB.

(5) The Application will then display a list of Top 5 websites ranking based on ALL the websites found in the MySQL database table (topfivewebdb.TOPFIVEWEB).

Assignment Question & Status Report
===================================

You are given a data (data.csv file) that consists of total visits for each websites based on a weekly dates. 
Your assignment is to create a web application to render 'Top 5 Websites Rankings' report where:

(1) The report should clearly shows the top 5 websites based on the selected date.
> The Report will generate the (distinct) Top 5 websites based on All the records in the CSV.
> No date selection functionality is available (due to time-constraint)

(2) User should be able to change the date and the report will be updated based on the selected date.
> No date selection functionality is available (due to time-constraint)
