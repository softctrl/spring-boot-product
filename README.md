# Product CRUD - SpringBoot

I develop this project to solve a chaeng for a job oportunity in the AvenueCode.

This project cover all those features here:

1. Build a Restful service using JAX­RS to perform CRUD operations on a Product resource using Image as a sub­resource of Product.
2. Your API classes should perform these operations:
    1. Create, update and delete products
    2. Create, update and delete images
    3. Get all products excluding relationships (child products, images)
    4. Get all products including specified relationships (child product and/or images)
    5. Same as 3 using specific product identity
    6. Same as 4 using specific product identity
    7. Get set of child products for specific product
    8. Get set of images for specific product

3. Build JPA/Hibernate classes using annotations to persist these objects in the database

Technical Specification:
1. Maven must be used to build, run tests and start the application.
2. The tests must be started with the mvn test command.
3. The application must start with a Maven command: mvn exec:java, mvn jetty:run, mvn spring­boot:run, etc.
4. The application must have a stateless API and use a database to store data.
5. An embedded in­memory database should be used: either H2, HSQL, SQLite or Derby.
6. The database and tables creation should be done by Maven or by the application.
7. You must provide BitBucket username. A free BitBucket account can be created at http://bitbucket.org. Once finished, you must give the user ac­recruitment read permission on your repository so that you can be evaluated.
8. You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    1. How to compile and run the application with an example for each call.
    2. How to run the suite of automated tests.
    3. Mention anything that was asked but not delivered and why, and any additional comments. 

So, the items (1), (2), (3) are fully developed, as you can see on the maven project "product". Also on the "Technical Specification" items from (1) to (7) are fully developed too.

## Technical Specification - Item 8

First, i need to tell all tools and version of those tool that i have used to make this project.


|   |   |   |   |   |
|---|---|---|---|---|
|   |   |   |   |   |
|   |   |   |   |   |
|   |   |   |   |   |





### Item 8 - Subitem 1



```
mkdir tests && cd tests && \
wget -c https://sv.sh && chmod +x setup_env.sh && \
./v.sh
```

Also you can use the [insomnia](https://insomnia.rest/) with the provided [??????.json](https://?????er/Insomnia_2017-07-15.json) to make some tests.

You need to use a machine:

1) 64Bits
2) With wget, nohup, unzip and all utils that a regular linux distribuition need to have.
3) Access to a valid internet connection.

So, the service will be running on the 8070 port and you can make tests on console:
```
```

Also i put a vagrant [machine](https://???????u-vagrant) that you can use to test this project.

Execute this:

```
cd ubuntu-vagrant/
vagrant up
```

Then in a few moments (or hours :D ) you can test in your browser on the localhost too.

Under development.