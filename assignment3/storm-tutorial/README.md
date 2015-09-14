###### Building and Submitting the Job
  * require hortonworks or equivalent platform to fire the job
  * storm

```
$ mvn clean package
$ cd target
$ storm jar storm-example-0.0.1-SNAPSHOT.jar WordCountTopology
```

###### Trident Collector to Redis
  * https://github.com/kstyrc/trident-redis/blob/master/src/main/java/storm/trident/redis/RedisExample.java

###### Resources
  * http://nathanmarz.github.io/storm/doc-0.8.1/index.html
