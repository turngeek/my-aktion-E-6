# my-aktion-E-6
Implementing My-Aktion as a microservices-based application

## Branch: seed

Contains a seed project for the *campaign service* generated with the [quarkee archetype of Adam Bien](https://github.com/AdamBien/quarkee).

## Branch: basics

In this branch, we start implementing the *campaign service*. We implement the domain classes, a class providing sample data in memory without a data base, boundary services for campaigns and donations and REST resource classes for both.
Test the resulting REST services with Swagger! After running the application, open http://localhost:8080/swagger-ui in the browser (only available in *quarkus:dev* mode!).

## Branch: jpa-h2
In this branch, we introduce the persistence layer. We use the h2 database as configured in the application.properties file. The following steps were necessary:

* Amend enity classes with JPA annotations to define the ORM.
* Use Bean Validation annotations to force JPA to vadidate the attributes of the entities.
* Add h2 driver to pom.xml.
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
