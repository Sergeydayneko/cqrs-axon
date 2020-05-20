Axon Server provides a platform to the developers to handle internals of the communication as well as monitor it.

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