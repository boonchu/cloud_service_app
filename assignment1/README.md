##### MP1 Word Count java project

  * option 1 use brew to install on local desktop (code development setup only)
    - http://stackoverflow.com/questions/8826881/maven-install-on-mac-os-x

```
$ java -version (JDK 1.7+)
$ brew update && brew install maven
```

  * option 2 setup hotronwork Hadoop, download virtual box and horton works image for virtualbox
	- http://hortonworks.com/hdp/downloads/
  * import virtualbox horton works
  * start virtualbox 

```
$ ssh root@127.0.0.1 -p 2222
```

  * access console (admin/admin access)
	- http://127.0.0.1:8080/#/main/dashboard/metrics

  * Build Jar in 5 minutes
  * https://cwiki.apache.org/confluence/display/MAVEN/Tutorial%3A+Build+a+JAR+file+with+Maven+in+5+minutes

```
$ wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O \
	/etc/yum.repos.d/epel-apache-maven.repo
$ yum install apache-maven
```

* Steps
  - source must be in ./src/main/java
  - header must exist with string 'pacakge MP1;'
  - compile with 'mvn package'

```
$ rm -rf target
$ mvn package
$ jar tvf /Users/boonchu/Documents/src/cloud_service_app/assignment1/target/MP1-1.0.jar
     0 Sat Sep 05 04:21:02 PDT 2015 META-INF/
   132 Sat Sep 05 04:21:00 PDT 2015 META-INF/MANIFEST.MF
     0 Sat Sep 05 04:21:02 PDT 2015 WordCount/
  1468 Sat Sep 05 04:21:02 PDT 2015 WordCount/ArrayDemo.class
  1421 Sat Sep 05 04:21:02 PDT 2015 WordCount/MP1$1.class
  8198 Sat Sep 05 04:21:02 PDT 2015 WordCount/MP1.class
  1566 Sat Sep 05 04:21:02 PDT 2015 WordCount/readLines.class
  1461 Sat Sep 05 04:21:02 PDT 2015 WordCount/SortedMapDemo$1.class
  3207 Sat Sep 05 04:21:02 PDT 2015 WordCount/SortedMapDemo.class
  1399 Sat Sep 05 04:21:02 PDT 2015 WordCount/TreeMapDemo.class
     0 Sat Sep 05 04:21:02 PDT 2015 META-INF/maven/
     0 Sat Sep 05 04:21:02 PDT 2015 META-INF/maven/MP1/
     0 Sat Sep 05 04:21:02 PDT 2015 META-INF/maven/MP1/MP1/
   241 Sat Sep 05 04:19:20 PDT 2015 META-INF/maven/MP1/MP1/pom.xml
    89 Sat Sep 05 04:21:02 PDT 2015 META-INF/maven/MP1/MP1/pom.properties
```

```
$ java -cp /Users/boonchu/Documents/src/cloud_service_app/assignment1/target/MP1-1.0.jar WordCount.MP1 0
list
de
state
school
disambiguation
county
new
john
album
c
river
station
united
highway
national
saint
william
route
f
film
```
