# my-aktion-E-6
Implementing My-Aktion as a microservices-based application

Branch: seed

Contains a seed project for the *campaign service* generated with the [quarkee archetype of Adam Bien](https://github.com/AdamBien/quarkee).

Required: Java 8

Build:

`mvn package`

Run:

`mvn compile quarkus:dev`

Or build a docker image:

`docker build -f src/main/docker/Dockerfile.jvm -t quarkus/full-jvm .`

and run it:

`docker run -i --rm -p 8080:8080 quarkus/full-jvm`
