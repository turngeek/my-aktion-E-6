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

## How to build and run the branches seed, basics and jpa-h2?

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

## Branch: security

In this branch, we secure our services using JWT and Java EE security annotations and classes. We use a little helper application [jwtenizr](https://github.com/AdamBien/jwtenizr) from Adam Bien that generate public/private keys, JWT token and application.properties configuration. Please be aware that the generated tokens have an expiration date. If you want to test the services, you probably have to generate a new token using jwtenizr.

We did the following steps:

* Download and Store the current `jwtenizr.jar` in the root of the campaignservice project.
* Execute `java -jar jwtenizr.jar` once again to generate all files with dummy values.
* Adapt the files especially `jwt-token.json` to our needs for the service (issuer my-aktion-auth, upn max@mustermann.de, group Organizer...)
* Add smallrye-jwt dependency in `pom.xml` (MicroProfile implementation of jwt).
* Execute `java -jar jwtenizr.jar` again if you want to have a valid token for the principal max@mustermann.de with role Organizer.
* Update `application.properties` with the key-value-pairs from microprofile-config.properties (issuer and public key).
* Secure the endpoints of the REST services with security annotations (@PermitAll or @RolesAllowed).
* Add new attribute organizerName to Entity Campaign and update NamedQueries. Inject Principal in order to use the principalName as organizerName.
* Adapt import.sql!
* Finally, avoid forbidden operations of authorized users (e.g. delete campaigns of other ogranizers or list donations of campaigns from other organizers).

Hint: if you want to test the services in the branch, execute jwtenizr again to produce a new and valid token for user max@mustermann.de. In the console you'll a curl command. You just have to adapt the URI of the service you want to call:

`curl -i -H'Authorization: Bearer eyJraWQiOiJqd3Qua2V5IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJNYXggTXVzdGVybWFubiIsInVwbiI6Im1heEBtdXN0ZXJtYW5uLmRlIiwiYXV0aF90aW1lIjoxNTgyNzIzODE2LCJpc3MiOiJteS1ha3Rpb24tYXV0aCIsImdyb3VwcyI6WyJPcmdhbml6ZXIiXSwiZXhwIjoxNTgyNzI0ODE2LCJpYXQiOjE1ODI3MjM4MTYsImp0aSI6IjQyIn0.FlJoDdy8tAyB0Hjnr-NLUZQXw6WprdrXgvs_Y_7KvIODY6Ckhi7WtCXJiteWywhsKDrFJxRw8PaVWHelIsyqZiNj9pd27R58CWP-2kzBP2rjJQRtW5JNQ_uR74JEipM99hl8UFtT2qf9gyX-kP_acmxpi7z2LJHO8i0PzDTkXttIM6C6y6YfLBzCf9NARAEZZ4GiLsbYBWZ7_6vRtgOPKkViUf76-NAKgqNF5xCYAeUgD49iIfpA8_w0qt7H4D0oFdzgLrfuJvNrWFR54Yfo3_LDc8NwSwXDt4qPgYo1O5PwrMRn7ns4mK4ARQ5rSUIe5LAo33G9ARt69Gcm-qouPg' http://localhost:8080/campaign/list`


## How to build and run the branches postgres and security?

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
