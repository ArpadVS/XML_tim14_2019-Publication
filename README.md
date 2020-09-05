# ScientificArticles
Projekat iz predmeta "XML i veb servisi".  
Tim 14.
  Članovi tima:
  - Nikola Maksimović SW-15/2016
  - Marieta Rakoš SW-43/2016
  - Arpad Varga Šomođi  SW-35/2016
  
Video: [link]()

# Prerequisites
- Spring Boot + eclipse - Backend
- Angular - Frontend
- Apache Tomee [download](http://tomee.apache.org/download-ng.html)
  - exist v4.6.1 WAR [download](https://bintray.com/existdb/releases/exist/4.6.1/view)
  - apache-jena-fuseki WAR [download](https://jena.apache.org/download/index.cgi#apache-jena-fuseki)

## Setup
* Rename and deploy exist and jena-fuseki war files as exist.war and fuseki.war into the webapps folder of tomee server, and run startup.bat in tomee/bin folder
* Exist database is available at http://localhost:8080/exist (login as admin, uname:"admin", pw:"") and jena-fuseki at http://localhost:8080/fuseki
* Import backend in eclipse and run PublicationsApplication.java to start backend server on port 8000
* Through  http://localhost:8080/api/exist/initiateData endpoint initiate database
* In /frontend run: npm install and then npm run start to run frontend server available on port:4200 (http://localhost:4200/)
