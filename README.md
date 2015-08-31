##### MP1 java project

  * Build Jar in 5 minutes
  * https://cwiki.apache.org/confluence/display/MAVEN/Tutorial%3A+Build+a+JAR+file+with+Maven+in+5+minutes

```
wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O \
	/etc/yum.repos.d/epel-apache-maven.repo
yum install apache-maven
```

* Steps
  - source must be in ./src/main/java
  - header must exist with string 'pacakge MP1;'
  - compile with 'mvn package'

```
$ mvn package
$ jar -tvf /projects/cloud_service/target/MP1-1.0.jar
     0 Mon Aug 31 16:12:36 UTC 2015 META-INF/
   129 Mon Aug 31 16:12:34 UTC 2015 META-INF/MANIFEST.MF
     0 Mon Aug 31 16:12:36 UTC 2015 MP1/
  4447 Mon Aug 31 16:12:36 UTC 2015 MP1/MP1.class
     0 Mon Aug 31 16:12:36 UTC 2015 META-INF/maven/
     0 Mon Aug 31 16:12:36 UTC 2015 META-INF/maven/MP1/
     0 Mon Aug 31 16:12:36 UTC 2015 META-INF/maven/MP1/MP1/
   139 Mon Aug 31 16:07:08 UTC 2015 META-INF/maven/MP1/MP1/pom.xml
    89 Mon Aug 31 16:07:14 UTC 2015 META-INF/maven/MP1/MP1/pom.properties
$ java jar /projects/cloud_service/target/MP1-1.0.jar 123456
```
