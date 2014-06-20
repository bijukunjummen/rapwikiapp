The app behaves differently based on whether the app is run in the cloud or locally

cd to extLib folder

    cd extLib

Run the following command to get the gemfirexd related jars to the local maven repo:

    ./installCustomJars.sh


Install RabbitMQ server locally, you can run a server using the following command:

    rabbitmq-server


The application can be booted up at this point using an embedded h2 database:

    mvn spring-boot:run


To use the application with Gemfire XD, install Gemfire XD locally by downloading from https://network.gopivotal.com/products/pivotal-hd
and run an instance using the command:

    ./gfxd server start -dir=server1


If the above steps complete cleanly, then run the application using the following command:

    mvn spring-boot:run -Dspring.profiles.active=gemfirexd

If Mysql database is preferred, then run the following command:

    mvn spring-boot:run -Dspring.profiles.active=mysql

And Access Page at http://localhost:8080.

In a deployed cloud environment, appropriate database profile will be activated based on the availability of the
service types.



TODO:
=====
- [X] Very basic view summary..
- [X] Storing summary and basic details as markdown and displaying it cleanly
- [ ] Explore possible microservices
- [X] Explore possible rabbit scenarios
- [X] Integrate with Spring-Cloud
- [X] Use a sqlfire database
- [X] Bring in Gemfire XD use case
- [X] Add viewing details
- [ ] Integrate with Spring-security
- [ ] Ability to add/edit band through Spring-security
