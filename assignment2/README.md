###### Why call cleanup()?
  * https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=788

```
After perusing several sources, it would appear that cleanup is called once "at the end of the task". Logically, this suggests that cleanup is run after the MapReduce has completed, i.e., after the reduce method (similar to setup which is called once at the beginning of the task).

The class WordCountMap will emit single (key, value) pairs where each key is "paired up" with the integer 1.
The class WordCountReduce will take these pairs and calculate the total count for each key which is subsequently written out. This output will be the input for the next mapper class.

The class TopWordsMap will take a (key, sum) pair and store this in a TreeSet as (sum, key) with a maximum of 10 entries resulting in a TreeSet with the highest sums for all the entries sent to that particular map function.
At the end of this mapper, the pairs are stored as a string array and all string arrays are stored in a TextArrayWritable as a value (note the use of the NullWritable as a placeholder for a "key").

The class TopWordsReduce then takes the output of the cleanup function and converts all the "values" (which are really (int, string) pairs) into a Pair consisting of the numerical value as the first element of that pair and the word as the second element (this is necessary to accommodate the ordering in the next TreeSet). The reducer function will output all elements of the resulting TreeSet (named countToWordMap) as (word, value); in this case there are 10 such pairs.

So, I can see the necessity of the cleanup function and I can only assume it is called by Hadoop but I must admit it would have been nice to have some more guidance on this.
```

  * Tez?
	- http://hortonworks.com/hadoop/tez/

  * YARN?
	- http://hortonworks.com/get-started/yarn/

  * Log on Hardoop
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=837

```
To setup the logging, I used Apache commons logging (here from the OrphanPages exercise):

Imports:

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

Setup the log:

public static class LinkCountMap extends Mapper<Object, Text, IntWritable, IntWritable> {

    public static final Log log = LogFactory.getLog(LinkCountMap.class);

Later in the LinkCountMap class:

log.info(new Integer(parts[0]) + "," + 0);


When you run a Hadoop MapReduce application, in the output you'll get an application ID:

15/09/04 18:17:06 INFO impl.YarnClientImpl: Submitted application application_1441239124625_0048

"application_1441239124625_0048" in the log entry above is the application ID for this execution of a MapReduce job.

In an SSH session to the HortonWorks VM, you can look at the logs after the job is completed running (usually available after a complete write to HDFS).

Use application ID to access the Container log files
#> yarn logs -applicationId application_1441239124625_0048

As part of the output will be:

2015-09-04 18:09:07,400 INFO [main] OrphanPages$LinkCountMap: 1149406,0
2015-09-04 18:09:07,400 INFO [main] OrphanPages$LinkCountMap: 1149421,0
2015-09-04 18:09:07,400 INFO [main] OrphanPages$LinkCountMap: 1149606,0
2015-09-04 18:09:07,400 INFO [main] OrphanPages$LinkCountMap: 1149764,0
2015-09-04 18:09:07,401 INFO [main] OrphanPages$LinkCountMap: 1149779,0

```
