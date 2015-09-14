# cloudapp-mp2
Machine Programming Assignment for Cloud Application Course
 
   * starting with Title Count Exercise A
```
[root@sandbox mapreduce-mp2]# ./start.sh
Coursera User ID: 0
Prepare the Environment
/media/sf_/projects/cloud_service_app/assignment2/mapreduce-mp2
Prepare the HDFS
15/09/05 19:54:50 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 360 minutes, Emptier interval = 0 minutes.
Moved: 'hdfs://sandbox.hortonworks.com:8020/mp2' to trash at: hdfs://sandbox.hortonworks.com:8020/user/root/.Trash/Current
Done

[root@sandbox mapreduce-mp2]# hadoop fs -ls /mp2/misc
Found 3 items
-rw-r--r--   1 root hdfs         19 2015-09-05 19:55 /mp2/misc/delimiters.txt
-rw-r--r--   1 root hdfs         49 2015-09-05 19:55 /mp2/misc/league.txt
-rw-r--r--   1 root hdfs        621 2015-09-05 19:55 /mp2/misc/stopwords.txt
```

   * run hadoop with USER ID 0

```
hadoop jar TitleCount.jar TitleCount -D stopwords=/mp2/misc/stopwords.txt -D delimiters=/mp2/misc/delimiters.txt /mp2/titles /mp2/A-output
```

   * run.sh and read output from tmp folder
```
cat internal_use/tmp/output-TitleCount.txt
```
   
   * Orphan Pages
	- Map function. When you parse the string you know that the first token is alway a page having a sorting link to other pages, so by default I set the value to 0. Other pages, instead are referenced, so I set the value to 1.

	- Map function example: in case of "2: 5 7 3 9" I write to the context (2,0) (5,1) (7,1) (3,1) (9,1).

	- Reduce function. It receives for each key all the aggregated values map functions have produced so just an array of "1" and, may be one "0" you can then sum. If the sum is == 0 you can write to the context, that is an orphan page.

	- Reduce function example: (2,[0]), (5,[1,1,1,1]), (7,[1,1,1]), (12,[0, 1, 1]) means that page 2 has only referenced other pages, page 5 got references as page 7, page 12 references other pages and it is also referenced, there are 2 links pointing to page 12.


   * Popularity League assignment
	- need to select a subset of this list, i.e. only those matching the page_id given in the league variable.  According to the instructions there are at most 16 entries in this shorter list.
	- need to calculate the rank of each page, based on its link count, as described in the text you've quoted above.  For example, suppose you have three pairs <1, 100>, <2, 50>, <3, 75>. 
	- The corresponding ranks would be 2, 0 (because there are no pages in the league with less than 50 incoming links), 1.

```
<1,100> -> 2 because in the list there are 2 elements smaller than 100 -> 50 and 75
<2,50> -> 0 because it is the smallest number, no other elements smaller than 50
<3,75> -> 1 because in the list is only one element smaller than 75 -> 50
```

   * Adding log info to Orphan Pages
	- Imports Apache Commons Loggins

```
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
```
	- Setup Log

```
public static class LinkCountMap extends Mapper<Object, Text, IntWritable, IntWritable> {

    public static final Log log = LogFactory.getLog(LinkCountMap.class);
```

	- at LinkCountMap class

```
log.info(new Integer(parts[0]) + "," + 0);
```
	- run a Hadoop MapReduce application, in the output you'll get an application ID:
	- "application_1441239124625_0048" in the log entry above is the application ID for this execution of a MapReduce job.

```
# yarn logs -applicationId application_1442201920772_0003 | grep OrphanPages\$LinkCountMap | head -10
15/09/14 04:32:36 INFO impl.TimelineClientImpl: Timeline service address: http://sandbox.hortonworks.com:8188/ws/v1/timeline/
15/09/14 04:32:36 INFO client.RMProxy: Connecting to ResourceManager at sandbox.hortonworks.com/10.0.2.15:8050
15/09/14 04:32:37 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
15/09/14 04:32:37 INFO compress.CodecPool: Got brand-new decompressor [.deflate]
2015-09-14 04:30:59,256 INFO [main] OrphanPages$LinkCountMap: 499231,0
2015-09-14 04:30:59,292 INFO [main] OrphanPages$LinkCountMap: 304631,0
2015-09-14 04:30:59,303 INFO [main] OrphanPages$LinkCountMap: 43460,0
2015-09-14 04:30:59,304 INFO [main] OrphanPages$LinkCountMap: 97087,0
2015-09-14 04:30:59,304 INFO [main] OrphanPages$LinkCountMap: 364046,0
2015-09-14 04:30:59,304 INFO [main] OrphanPages$LinkCountMap: 390701,0
2015-09-14 04:30:59,304 INFO [main] OrphanPages$LinkCountMap: 51851,0
2015-09-14 04:30:59,305 INFO [main] OrphanPages$LinkCountMap: 436916,0
2015-09-14 04:30:59,320 INFO [main] OrphanPages$LinkCountMap: 437687,0
2015-09-14 04:30:59,321 INFO [main] OrphanPages$LinkCountMap: 483005,0

```
