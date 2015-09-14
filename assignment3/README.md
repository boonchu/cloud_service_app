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

   * How to Storm Message Buffers
     - http://www.michael-noll.com/blog/2013/06/21/understanding-storm-internal-message-buffers/

   * How to deal with Parallelism (Storm Topology)
     - https://storm.apache.org/documentation/Understanding-the-parallelism-of-a-Storm-topology.html

   * Twitter APIs vs Twitter Firehose
     - http://brnrd.me/twitter-apis-vs-twitter-firehose/
     - https://github.com/apache/storm/blob/master/examples/storm-starter/src/jvm/storm/starter/spout/TwitterSampleSpout.java
     - https://dev.twitter.com/streaming/reference/get/statuses/firehose

   * Normalize Bolt
     - https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1030

```
The NormalizeBold should run after SplitSentenceBold so the WordCountBold can count the normalized words.

    FileReaderSpout -> "spout" ~> SplitSentenceBolt -> "split" ~> WordCountBolt -> "count" ~> NormalizerBolt -> "normalize 

```

   * Part D
     - https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1029
