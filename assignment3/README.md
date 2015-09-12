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
