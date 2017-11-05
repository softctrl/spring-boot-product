(OK)1o Build a Restful service using JAX­RS to perform CRUD operations on a Product resource using Image as a sub­resource of Product.

(OK)2o Your API classes should perform these operations:
(OK)	1) Create, update and delete products
(OK)	2) Create, update and delete images
(OK)	3) Get all products excluding relationships (child products, images)
(OK)	4) Get all products including specified relationships (child product and/or images)
(OK)	5) Same as 3 using specific product identity
(OK)	6) Same as 4 using specific product identity
(OK)	7) Get set of child products for specific product
(OK)	8) Get set of images for specific product

(OK)	3o Build JPA/Hibernate classes using annotations to persist these objects in the database

Technical Specification:
(OK)	1) Maven must be used to build, run tests and start the application.
(OK)	2) The tests must be started with the mvn test command.
(OK)	3) The application must start with a Maven command: mvn exec:java, mvn jetty:run, mvn spring­boot:run, etc.
(OK)	4) The application must have a stateless API and use a database to store data.
(OK)	5) An embedded in­memory database should be used: either H2, HSQL, SQLite or Derby.
(OK)	6) The database and tables creation should be done by Maven or by the application.
(OK)	7) You must provide BitBucket username. A free BitBucket account can be created at http://bitbucket.org. Once finished, you must give the user ac­recruitment read permission on your repository so that you can be evaluated.
(OK)	8) You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
	­     (OK)How to compile and run the application with an example for each call.
	­     (OK)How to run the suite of automated tests.
	­     (OK)Mention anything that was asked but not delivered and why, and any additional comments.