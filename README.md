**Axon Server** provides a platform to the developers to handle internals of the communication as well as monitor it.

You can go in and test the server separately in three steps:

1. Down jar\docker file https://axoniq.io/download

2. Start jar file or docker image

3. Go to http://localhost:8024 (here will be empty dashboard)

Then you just need to add one dependency so that the axon sees your application

    <dependency>
        <groupId>org.axonframework</groupId>
        <artifactId>axon-spring-boot-starter</artifactId>
        <version>${axon.version}</version>
    </dependency>

After that you can start your app and see what the axon saw your application (on http://localhost:8024  dashboard)

Each event will be created on the axon server board.

Basic concepts:

Command - object with filled information 

Aggregate -  is a set of the value object and entities. The unit has transactional consistency. All objects included in a single whole.

@Aggregate - this is a bean of spring. It represents the current state of the Unit for validating commands in it.

@TargetAggregateIdentifier  - needed in order for the command to send the ID of the unit to which the event arrived to the gateway

Additional Dependencies:

1. As an event bus for interaction between different parts of the program was used **RabbitMQ**.

2. **Mongodb** was used as a repository

3. **Spring cloud config server** for application variables

The service is a demonstration for reproducing the idea of building **architecture through an event model** and 
using the **CQRS** pattern. In an ideal presentation, the request and command services should be separated (or the project should be modular),
 but since the demo service is not necessary.
