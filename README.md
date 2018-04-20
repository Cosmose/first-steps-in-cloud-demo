# First Steps in The Cloud Demo

Demo Application prepared for presentation purposes. 
Displays Los Angeles on Google Maps and visualize public transport vehicles in real-time using publicly available Los Angeles Metro API.

## Los Angeles Metro API
[Los Angeles Metro API](http://api.metro.net/)

## How to run locally
### Prerequisites
* Java 8
* Maven 3.x

Simply run maven build and run compiled JAVA application.

```sh
mvn clean install
java -jar target/firststepsincloud-1.0.0-SNAPSHOT.jar
```

Site will he available here: [localhost:8082](https://localhost:8082)


## How to deploy on Heroku
Deploying this app (pulled from this repository) is as simple as:

```sh
$ heroku create
$ git push heroku master
```

## Technologies used

* Java 8 
* Lombok
* Feign
* Hibernate
* [Spring Boot 2.0.1](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/)
* [Heroku](http://heroku.com)
* [Google Maps](https://developers.google.com/maps/documentation/javascript/)



