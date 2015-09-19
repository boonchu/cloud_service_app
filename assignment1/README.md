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

* Train yourself a data structure in Java
  - review source src/main/java/ArrayDemo.java

```
$ mvn clean package
$ java -cp ./target/MP1-1.0.jar WordCount/ArrayDemo
$ mvn clean
```

* Steps
  - source must be in ./src/main/java
  - header must exist with string 'pacakge MP1;'
  - compile with 'mvn package'

```
$ mvn clean package
$ jar tvf ./target/MP1-1.0.jar
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
$ java -cp ./target/MP1-1.0.jar WordCount.MP1 0
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
$ mvn clean
```

###### Why do we need ramdon generator?
  * https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=495

```
Let's work this backwards.  Your code needs to process 10,000 lines of input from a 50,000 line file.  We'd like the series of lines that are chosen to be different for each student, but the solution should be verifiable (if you provide the same user ID, you should always get the same result).  To get the list of lines to process, you should call getIndexes().

Now, getIndexes() uses a random number generator to create the list of lines to return.  A random number generator uses a seed value as a starting point for generating it's sequence of numbers.  If the random number generator is seeded with the same number, it will produce the same sequence.  So it makes sense to seed the random number generator with a value derived from your student ID, that way each time you run the program, you'll get the exact same stream of random numbers, and it'll be unique for each student.

The problem is that even if student IDs go up to 1,000,000 that can be represented in roughly 20 bits.  The leading 44 bits will always be 0.  That's probably fine for this application, but if security is a concern, it makes the seed far too easy to guess.  And if we can guess the seed, we can crack the code.
```
