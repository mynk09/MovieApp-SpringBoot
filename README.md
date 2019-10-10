Spring Boot Tasks

1.Created a movie service API and completed the following tasks

2.Complete all the endpoints for CRUD operations on Movie

3.Use h2-console to view in-memory data

4.Add an endpoint to search movieByName. Understand @Query and parameter passing to @Query

5.Generate API documentation using Swagger 2

6.Create custom exceptions MovieNotFoundException, MovieAlreadyExistsException in a com.stack....exceptions package. Perform appropriate exception handling and propagation back.

7.Running Logic on Startup in Spring. Create seed data to pre-fill the database with movie information whenever the application starts. Use both approaches: Approach 1: ApplicationListener Approach 2: CommandLineRunner (Find out how it differs from ApplicationRunner)

8.Global exception using Controller advice.

9.Remove all hardcoded data from the application code to application.properties

by using @Value.
by using @PropertySource
by using Environment
by using @ConfigurationProperties (https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/ Environment.html)
Add @Lombok (https://drive.google.com/file/d/1QQpEQZbDD9pmW2qrhYsx5N9XYQ5bJ5dM/view)

10.Create another service implementation MovieDummyServiceImpl. Use @Primary and @Qualifier annotations to specify which implementation to use.

11.Use @Profile to run the application with the existing service implementation, and the new MovieDummyServiceImpl implementation.

12.Swap in-memory H2 database with MySQL.

13.Create two additional properties file application-dev.properties that contains configuration for H2 and application-prod.properties that contains configurations for MySQL.