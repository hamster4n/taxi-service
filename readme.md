<h2> Taxi Service

<h3> Description of the project.

System Service Taxi. The client registers in the system and can create an order for Taxi, indicating the address and type of car. The program calculates the cost of the trip depending on the distance, customer discounts and / or promotions. The system supports the Loyalty program - the discount is calculated depending on the amount of the spent amount. Promotional offers may also apply. After confirmation of the Order by the client, the System is looking for a free taxi corresponding to the type of car. After that, the Client is informed whether Taxi has been found and what is the waiting time. 


<h3>How to start:

1. Must be installed Tomcat
2. Must be installed Maven
3. Must be installed PostgreSQL
4. Create the database "best_taxi" (with user "postgres " and password "qwerty")
5. Run for project 

        mvn flyway:migrate


   this way your database is populated with tables and test data.
6. Execute

        mvn package


7. File "taxi-service-1.0-SNAPSHOT.war" rename to "besttaxi.war" and deploy to tomcat.


<h3>How to use:

An unregistered user can familiarize himself with the starting information on the company's main page, view the list of available auto models, register and log in.

The user who successfully logged in has the default role of USER. User can additionally fill out his profile and get a permanent discount of + 1% for this. User can also view the history of their trips. 
Order cars as follows:
- go to the page "Order now!"
- on the map, specify the point "start address" and "finish address". An alternative option is to write the address in the input field on the map.
- In the form to the right of the map, the addresses and the distance of the route will be displayed.
- it is necessary to choose the type of vehicle comfort (in the test data Economy class cars are occupied)
- if there are no free cars, you will receive a notification about it
- in the case of free cars - a car of a suitable comfort class with the shortest arrival time will be offered
- Click "Order now" to confirm the order.

The user with the role "ADMIN" has access to the additional two pages: "users" and "cars". In the current version of the program on these pages all users and all machines are displayed respectively. In subsequent versions, the functionality of editing users and cars will be added.


<h3>What technologies were used:

1. JSP + JSTL.
2. Servlets.
3. JDBC.
4. Log4J.
5. JUnit.
6. Mockito.
7. Apache commons.
8. PostgreSQL.
9. FlyWay.
10. Bootstrap.
