This Spring Boot project is a demonstration of the Aspect Orientated Programming (AOP) capabilities in the Spring
framework. AOP is desirable because it allows common functionality to deployed throughout an application without having
to modify methods across the app. You may wish to comment out certain Advice methods to showcase specific functionality.


The following advice annotations are demonstrated:
- @Before
- @After
- @AfterThrowing
- @AfterReturning
- @PointCut
- @Around


There are four packages in this project:

- com.example.springdemo.springaopdemo
This is where the application class is located. Here the beans are retrieved and DAO methods are executed to trigger the
advice methods.

- com.example.springdemo.springaopdemo.aop
This package is the main portion of the project and the classes that demonstrate the AOP functionality are located here

- com.example.springdemo.springaopdemo.dao
This pacakge contains the dummy DAO methods. There aren't any actual calls to a database.

- com.example.springdemo.springaopdemo.entities
This package contains a mock entity.


Credit to Chad Darby as much of the information was sourced from his Spring & Hibernate class on Udemy.