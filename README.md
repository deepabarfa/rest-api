# Filesaver-Api

## Introduction

Filesaver will serve as a cloud storage platform for users to backup their data. The project will contain the backend code and APIs for Filesaver.

## Project details and setup

The project is written in Java 8.

Maven is used for Project Management. The `pom.xml` file will contain all the dependencies needed for the project
as well as other configuration details.

### Prerequisites

* Java 8
* Maven 3
* Postgres 9.3
* Redis 3.2

### Environment variables

* FSENC_PASSWD_SALT
* NCRYPT_16

## Build tasks

* Running test
`mvn test`

* Compile project / generate code coverage report / make war
`mvn clean package`

* Running server
`mvn tomcat7:run`

* Applying migrations
`mvn clean compile flyway:migrate`
