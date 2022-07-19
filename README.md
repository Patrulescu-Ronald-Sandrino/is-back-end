## Setup Steps
1. create IntelliJ Spring Initializr Project with
    1. Spring Web
    2. Spring Data JPA
    3. MySQL
    4. Flyway
2. set up db
   1. create database
   2. add `spring.datasource.{url,username,password}` in `application.properties`
   3. downgrade `org.flywaydb:flyway-core` to `<version>7.15.0</version>` (source: 2nd answer from https://stackoverflow.com/questions/59779435/pivotal-web-services-flyway-enterprise-edition-or-mysql-upgrade-required)
3. add swagger (search: swagger ui java 1.8 spring boot - https://www.javainuse.com/spring/boot_swagger3)
   1. just add the `org.springdoc:springdoc-openapi-ui:1.6.9` dependency and go to `localhost:8080/swagger-ui/index.html`

## Code Steps
1. add domain entities (configured such that they match (excepting the col's length) the initial SQL)
