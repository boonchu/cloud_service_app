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
$ java -cp /projects/cloud_service/target/MP1-1.0.jar MP1.MP1 123456
```
