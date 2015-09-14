###### Apache Storm
  * https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1041

  * Starting Storm
```
su - storm /usr/hdp/current/storm-supervisor/bin/storm nimbus &
su - storm /usr/hdp/current/storm-supervisor/bin/storm ui &
su - storm /usr/hdp/current/storm-supervisor/bin/storm logviewer &
su - storm /usr/hdp/current/storm-supervisor/bin/storm drpc &
```
   
   * Basic Storm
     - https://storm.apache.org/documentation/Tutorial.html
     - http://www.slideshare.net/ptgoetz/storm-hadoop-summit2014?related=2

   * How to Storm Message Buffers
     - http://www.michael-noll.com/blog/2013/06/21/understanding-storm-internal-message-buffers/

   * How to deal with Parallelism (Storm Topology)
     - https://storm.apache.org/documentation/Understanding-the-parallelism-of-a-Storm-topology.html

   * Twitter APIs vs Twitter Firehose
     - http://brnrd.me/twitter-apis-vs-twitter-firehose/
     - https://github.com/apache/storm/blob/master/examples/storm-starter/src/jvm/storm/starter/spout/TwitterSampleSpout.java
     - https://dev.twitter.com/streaming/reference/get/statuses/firehose

   * How to pass storm tuples to external storage
     - https://github.com/apache/storm/tree/master/external

   * Is nimbus a Single Point of Failure?
     - https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1062
     - https://storm.apache.org/documentation/Fault-tolerance.html

```
If you lose the Nimbus node, the workers will still continue to function. Additionally, supervisors will continue to restart workers if they die. However, without Nimbus, workers won't be reassigned to other machines when necessary (like if you lose a worker machine).

So the answer is that Nimbus is "sort of" a SPOF. In practice, it's not a big deal since nothing catastrophic happens when the Nimbus daemon dies. There are plans to make Nimbus highly available in the future
```

   * Storm vs. Hadoop MapReduce
     - http://datascience.stackexchange.com/questions/227/tradeoffs-between-storm-and-hadoop-mapreduce

```
MapReduce: A fault tolerant distributed computational framework. MapReduce allows you to operate over huge amounts of data- with a lot of work put in to prevent failure due to hardware. MapReduce is a poor choice for computing results on the fly because it is slow. (A typical MapReduce job takes on the order of minutes or hours, not microseconds)

A MapReduce job takes a file (or some data store) as an input and writes a file of results. If you want these results available to an application, it is your responsibility to put this data in a place that is accessible. This is likely slow, and there will be a lag between the values you can display, and the values that represent your system in its current state.

An important distinction to make when considering using MapReduce in building realtime systems is that of training your model, and applying your model. If you think your model parameters do not change quickly, you can fit them with MapReduce, and then have a mechanism for accessing these pre-fit parameters when you want to apply your model.

Storm: A real-time, streaming computational system. Storm is online framework, meaning, in this sense, a service that interacts with a running application. In contrast to MapReduce, it receives small pieces of data (not a whole file) as they are processed in your application. You define a DAG of operations to perform on the data. A common and simple use case for Storm is tracking counters, and using that information to populate a real-time dashboard.

Storm doesn't have anything (necessarily) to do with persisting your data. Here, streaming is another way to say keeping the information you care about and throwing the rest away. In reality, you probably have a persistence layer in your application that has already recorded the data, and so this a good and justified separation of concerns.
```

   * Storm vs. Spark
     - http://www.slideshare.net/ptgoetz/apache-storm-vs-spark-streaming?related=1
