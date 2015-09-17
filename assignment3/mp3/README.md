##### How to run storm MP3?
  * Simple Word Count Topology Part A
    - build a simple word count that counts the words generated in a random sentence spout.
    - use the “RandomSentenceSpout” class as the spout, the “SplitSentenceBolt” class to split
      sentences into words, and the “WordCountBolt” class to count the words. 
    - Topology:

```
   RandomSentanceSpout "spout" ~> SplitSentenceBolt "split" ~> WordCountBolt "count"
```

```
$ mvn clean package
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartA
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartA > output-part-a.txt (auto grader)
```

  * Input Data from a File Part B
    - count words from one of Shakespeare’s articles.
    - create a new spout that reads data from an input file and emits each line as a tuple.
    - set the number of executors of the spout to “1” so that you don’t read the input file more than once.
	- https://storm.apache.org/documentation/Understanding-the-parallelism-of-a-Storm-topology.html
    - Topology:

```
   FileReaderSpout "spout" ~> SplitSentenceBolt "split" ~> WordCountBolt "count"
```

```
$ mvn clean package
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartB -c datafile=data.txt
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartB -c datafile=data.txt > output-part-b.txt (auto grader)
```

  * Normalizer Bolt Part C
    - Part B counts the words “Apple” and “apple” as two different words. However, if we want to find the top N words, we have to count these words the same
    - adds a normalizer bolt that gets the words from the splitter, normalizes them, and then sends them to the counter bolt.
    - Normalizer is to:
    	- Make all input words lowercase
	- Remove common English words
    - set the number of executors of the spout to “1” so that you don’t read the input file more than once.
	- https://storm.apache.org/documentation/Understanding-the-parallelism-of-a-Storm-topology.html
    - Topology:
	- discuss in this thread for a number of executors that use for spouts and bolts
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1030

```
   FileReaderSpout "spout" ~> SplitSentenceBolt "split" ~> NormalizerBolt "normalize" ~> WordCountBolt "count"
```

```
$ mvn clean package
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartC -c datafile=data.txt
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartC -c datafile=data.txt > output-part-c.txt (auto grader)
```

```
[root@sandbox mp3]#  cat output-part-c.txt |grep gutenberg|grep Emitting|grep count
7192 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 1]
7498 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 2]
7650 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 3]
7764 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 4]
7965 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 5]
8217 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 6]
8414 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 7]
8816 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 8]
8954 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 9]
9402 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 10]
9508 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 11]
9517 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 12]
9671 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 13]
10047 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 14]
10266 [Thread-15-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg, 15]
10431 [Thread-31-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg-tm, 1]
10518 [Thread-31-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg-tm, 2]
10527 [Thread-31-count] INFO  backtype.storm.daemon.task - Emitting: count default [gutenberg-tm, 3]
```

  * Top N Words Part D
    - find the top N words.
    - build a topology that reads from an input file, splits the information into words, normalizes the words, and counts the number of occurrences of each word. 
    - implement a bolt that keeps count of the top N words. Upon receipt of a new count from the count bolt, it updates the top N words. Then, it reports the top N words periodically
    - printMap() method that prints the top-N words
    - emit with empty word
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1112
        - https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1029
    - Topology:

```
   FileReaderSpout "spout" ~> SplitSentenceBolt "split" ~> NormalizerBolt "normalize" ~> WordCountBolt "count" ~> TopNFinderBolt "top-n"
```

```
22919 [Thread-11-top-n] INFO  backtype.storm.daemon.task - Emitting: top-n default [top-words = [ ( , 1507) , (macb , 132) , (thou , 84) , (haue , 122) , (rosse , 48) , (enter , 81) , (th , 55) , (who , 52) , (yet , 57) , (shall , 68) ]]

$ sed 's/\.//g;s/\(.*\)/\L\1/;s/\ /\n/g' data.txt | sort | uniq -c | grep macb
    137 macb
     14 macbeth
     19 macbeth,
      5 macbeth,
      1 macbeth:
      1 macbeth:
      1 macbeth?
      2 macbeth*********************
     24 macbeth
      4 macbeth
```

```
$ mvn clean package
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartD -c datafile=data.txt
$ storm jar target/storm-example-0.0.1-SNAPSHOT.jar TopWordFinderTopologyPartD -c datafile=data.txt > output-part-d.txt (auto grader)
```
