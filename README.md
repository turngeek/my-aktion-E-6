# my-aktion-E-6
Implementing My-Aktion as a microservices-based application

## Branch: seed

Contains a seed project for the *campaign service* generated with the [quarkee archetype of Adam Bien](https://github.com/AdamBien/quarkee).

## Branch: basics

In this branch, we start implementing the *campaign service*. We implement the domain classes, a class providing sample data in memory without a data base, boundary services for campaigns and donations and REST resource classes for both.
Test the resulting REST services with Swagger! After running the application, open http://localhost:8080/swagger-ui in the browser (only available in *quarkus:dev* mode!).

## Branch: jpa-h2
In this branch, we introduce the persistence layer. We use the h2 database. The following steps were necessary:

* Amend enity classes with JPA annotations to define the ORM.
* Use Bean Validation annotations to enable validation of attributes of the entities during the ORM.
* Add h2 driver to pom.xml and configure h2 database as in the application.properties file
* Rewrite the CampaignService and DonationService classes to use JPA's EntityManager.
* Change resource service classes to avoid cycles during JSON generation because of bidirectional entity relations.
* Provide a file import.sql containing some insert statements in order to have a database containing sample data after startup.

### Build:

`mvn package`

### Run:

`mvn compile quarkus:dev`

Or build a docker image:

`docker build -f src/main/docker/Dockerfile.jvm -t quarkus/full-jvm .`

and run it:

`docker run -i --rm -p 8080:8080 quarkus/full-jvm`

Required: Java 8

## Branch: postgres

In this branch, we substitute the h2 database by a postgresql
database. Therefore, we don't have to program anything, but we have to configure some things and we have to change the way we start up our application.

* We want to run the postgresql database in a separate docker container. We use the official image from docker hub and set some environment variables.
* We have to change the driver dependency in the pom.xml from h2 to postgresql.
* We have to change the db configuration in file applications.properties.
* Finally, since we now have two containers, we use docker-compose to be able to startup both in the correct order.

### Run:

Again, we have to possibilities to start our service.

#### Developer mode

After pulling the postgres image (`docker pull postgres`), we create an start a container:

`docker run --name my-aktion-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=my-aktion_db -p 5432:5432 -d postgres`

Now the database is up and running. You can stop and restart the database with the normal docker commands stop and start, you don't have to use the run command again.

After starting the database, you can start our service as usual:

`mvn compile quarkus:dev`

#### Docker Compose
Now you'll find a docker-compose.yml in the root of the project. This defines both services (our Java application and the database).

You can simply pull, build and start both containers from the shell (take care that the current working directory is my-aktion-E-6):

* `docker-compose pull`
* `docker-compose build`
* `docker-compose up`

`docker-compose down` stops the containers and deletes them. While `docker-compose stop` just stops the containers, without deleting them.
