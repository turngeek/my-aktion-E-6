# my-aktion-E-6
Implementing My-Aktion as a microservices-based application

## Branch: seed

Contains a seed project for the *campaign service* generated with the [quarkee archetype of Adam Bien](https://github.com/AdamBien/quarkee).

## Branch: basics

In this branch, we start implementing the *campaign service*. We implement the domain classes, a class providing sample data in memory without a data base, boundary services for campaigns and donations and REST resource classes for both.
Test the resulting REST services with Swagger! After running the application, open http://localhost:8080/swagger-ui in the browser.

### Build:

`mvn package`

### Run:

`mvn compile quarkus:dev`

Or build a docker image:

`docker build -f src/main/docker/Dockerfile.jvm -t quarkus/full-jvm .`

and run it:

`docker run -i --rm -p 8080:8080 quarkus/full-jvm`

Required: Java 8
