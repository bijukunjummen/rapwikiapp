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

Edit src/main/resources/application.properties and uncomment this line:

    spring.profiles.active=gemfirexd

If the above steps complete cleanly, then run the application using the following command:

    mvn spring-boot:run

If Mysql database is preferred, then edit src/main/resources/application.properties and ensure that this line is present:

    spring.profiles.active=mysql

and run: 

    mvn spring-boot:run 

And Access Page at http://localhost:8080.

In a deployed cloud environment, appropriate database profile will be activated based on the availability of the
service types.



Instruction for running the App in a bosh-lite/cf environment:
===========================================================
Rabbitmq by default blocks connections using the guest account from anything but localhost. So the first step is to get rabbitmq to accept connections from the bosh-lite hosted vms, by running the following command:

	rabbitmq-server
    rabbitmqctl set_permissions -p / guest ".*" ".*" ".*"

Assuming that GemfireXD is being used as the persistent store, gemfireXD also by default blocks connections from anything but localhost, so to get gemfirexd to accept remote connections, start it up the following way:

    gfxd server start -dir=server1 -client-bind-address=0.0.0.0 -prefer-netserver-ipaddress=true


With both RabbitMQ and GemfireXD running, start up the bosh-lite/cf environment and add the user provided service for each of these two services the following way:

    cf create-user-provided-service gemfirexd -p '{"uri":"jdbc:gemfirexd://bkunjummen-mbp.local:1527/;user=app;password=app", "tags":"gemfirexd"}'
    
    cf create-user-provided-service rabbitmq -p '{"uri":"amqp://guest:guest@bkunjummen-mbp.local:5672", "tags":"rabbitmq"}'

Now, push the app to the cf:

   cf push rapwikiapp -p target/rapwikiapp-1.0.0-SNAPSHOT.war --no-start

and bind the services to the app:

    cf bind-service rapwikiapp gemfirexd
    cf bind-service rapwikiapp rabbitmq

If everything has worked cleanly upto this point, cross your fingers and push the app once more:

   cf push rapwikiapp -p target/rapwikiapp-1.0.0-SNAPSHOT.war




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
- [X] GemfireXD ServiceInfo, ServiceInfoCreator, ServiceConnnectorCreator
- [ ] Integrate with Spring-security
- [ ] Ability to add/edit band through Spring-security
