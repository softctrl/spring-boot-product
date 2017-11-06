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
7. You must provide BitBucket username. A free BitBucket account can be created at http://bitbucket.org. Once finished, you must give the user ac-­recruitment read permission on your repository so that you can be evaluated.
8. You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    1. How to compile and run the application with an example for each call.
    2. How to run the suite of automated tests.
    3. Mention anything that was asked but not delivered and why, and any additional comments. 

So, the items (1), (2), (3) are fully developed, as you can see on the maven project "product". Also on the "Technical Specification" items from (1) to (7) are fully developed too.

## Technical Specification - Item 8

First, i need to tell all tools and version of those tool that i have used to make this project.


| Development Tool | Version | URL |
| --------------------- | --------------------- | --------------------- |
|Eclipse Java EE IDE for Web Developers.|Oxygen Release (4.7.0)|https://www.eclipse.org|
|Apache Maven|3.5.0|https://maven.apache.org/|
|Oracle Java SE 8|1.8.0_25|http://www.oracle.com/technetwork/java/javase/downloads/index.html|

So, to make some tests you only need the Oracle Java JDK, and the Apache Maven on your environment.

Assuming that you already have configured your environment we can go to the next items.

First you need to make a git clone for a local folder on your computer. 

```
git clone https://ac-­recruitment@bitbucket.org/softctrl/avenue_code.git && \
cd avenue_code/product && \
echo "## Done" 
```

### Item 8 - Subitem 1 - How to compile and run the application with an example for each call.

Now that you already have the source code and you are inside the project folder. You just need to execute the command bellow to compile and the run the project:
 
```
mvn install && \
mvn spring-boot:run 
```

Now the project is up and fully operational.

So, the database has no data at that moment, so you need first to put some data into the project.

On another terminal you can execute cUrl commands as follow:

1. Insert one Product:

```
curl --request POST \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
	"name": "Cesta Basica",
	"description": "Cesta Basica BH",
	"images":[
		{"type":"jpg"}
	]
}'
```

2. Insert another product that have a parent product:

```
curl --request POST \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
	"name": "Feijao",
	"description": "Feijao Carioca 5kg",
	"images":[
		{"type":"jpg"}
	],
  "parentProduct":{
		"id": 1
	}	
	
}'
```

That item you can execute as many as you want, just change the Json data to any value that you want.

Now that you have data on the database you can do as follow:

a. Create Products, we just make this a few steps ago :D :

```
curl --request POST \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
		"id": 1,
		"name": "Cesta Basica",
		"description": "Cesta Basica BH",
		"images": [
			{
				"id": 1,
				"type": "jpg"
			}
		],
		"products": [
			{
				"id": 2,
				"name": "Feijao",
				"description": "Feijao Carioca 5kg",
				"images": [
					{
						"id": 2,
						"type": "jpg"
					}
				]
			}
		]
}'

```

b. Update Products:

```
curl --request PUT \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
	"id":1,
	"name": "Cesta Nao Tao Basica",
	"description": "Cesta Nao Tao Basica BH"
}'
```

c. Delete Products:

```
curl --request DELETE \
  --url http://localhost:8070/product/3 \
  --header 'content-type: application/json'
```

You just need to inform the id of the product that you want to remove.

d. Create Images:

```
curl --request POST \
  --url http://localhost:8070/image \
  --header 'content-type: application/json' \
  --data '{
	"product": {
		"id": 1
	},
	"type":"jpg"
}'
```

e. Update Images:

```
curl --request PUT \
  --url http://localhost:8070/image \
  --header 'content-type: application/json' \
  --data '{
	"product": {
		"id": 1
	},
	"id":1,
	"type":"png"
}'
```

f. Delete Images:

```
curl --request DELETE \
  --url http://localhost:8070/image/1 \
  --header 'content-type: application/json'
```

You just need to inform the id of the image that you want to remove.

g. Get all products excluding relationships (child products, images):

```
curl --request GET \
  --url 'http://localhost:8070/product?all=false' \
  --header 'content-type: application/json'
```

Here you only need to inform the query parameter "all=false" and i will give you only product information without relationships.

h. Get all products including specified relationships (child product and/or images):

```
curl --request GET \
  --url 'http://localhost:8070/product?all=true' \
  --header 'content-type: application/json'
```

Here you only need to inform the query parameter "all=true" and i will give you all data that we have of all products.

i. Same as (3) -> (g) using specific product identity:

```
curl --request GET \
  --url 'http://localhost:8070/product/1?all=false' \
  --header 'content-type: application/json'
```

As you can see the "all" parameter still exists, you only need to inform a valid id first.

j. Same as (4) -> (h) using specific product identity:

```
curl --request GET \
  --url 'http://localhost:8070/product/1?all=true' \
  --header 'content-type: application/json'
```

k. Get set of child products for specific product:

So, to Get child products for a specific product you need to perform this:

```
curl --request GET \
  --url http://localhost:8070/product/1/childs \
  --header 'content-type: application/json'
```

You only need to inform a valid id.

To set any child for a product you can use the command on (a).

i. Get set of images for specific product

So, to Get images for a specific product you need to perform this:

```
curl --request GET \
  --url http://localhost:8070/product/10/images \
  --header 'content-type: application/json'
```

To set any image for a product you can use the command on (a).

### Item 8 - Subitem 2 - How to run the suite of automated tests.

Inside the project "product" folder you only need to execute:

```
mvn test
```

### Item 8 - Subitem 3 - Mention anything that was asked but not delivered and why, and any additional comments.

So, i think that i have covered all requirements for this challenge. I hope you enjoy this project. And also i hope that i get the job :D


Also you can use the [insomnia](https://insomnia.rest/) with the provided [insomnia-env.json](https://bitbucket.org/softctrl/avenue_code/raw/f39abc067dd8e30b6b138a3b9b74e86b753aa5ee/product/insomnia-env.json) to make some tests.

thanks,