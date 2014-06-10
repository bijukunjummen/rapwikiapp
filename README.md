cd to extLib folder

`cd extLib`

Run the following command to get the sqlfirehibernate dialect related jar to the local maven repo:

`./installCustomJars.sh`

Run using mvn spring-boot:run

Access Page at http://localhost:8080


TODO:
=====
- [X] Very basic view summary..
- [X] Storing summary and basic details as markdown and displaying it cleanly
- [ ] Explore possible microservices
- [ ] Explore possible rabbit scenarios
- [ ] Integrate with Spring-Cloud
- [X] Use a sqlfire database
- [ ] Bring in Gemfire XD use case
- [X] Add viewing details
- [ ] Integrate with Spring-security
- [ ] Ability to add/edit band through Spring-security
