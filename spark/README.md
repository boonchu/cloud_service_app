###### Spark in 5 minutes
  * http://hortonworks.com/hadoop-tutorial/hands-on-tour-of-apache-spark-in-5-minutes/

```
./sample.py

15/09/19 17:32:52 INFO TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 654 ms on localhost (1/2)
15/09/19 17:32:52 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 670 ms on localhost (2/2)
15/09/19 17:32:52 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool
15/09/19 17:32:52 INFO DAGScheduler: Stage 0 (count at /media/sf_/projects/cloud_service_app/spark/./sample.py:10) finished in 0.689 s
15/09/19 17:32:52 INFO DAGScheduler: Job 0 finished: count at /media/sf_/projects/cloud_service_app/spark/./sample.py:10, took 0.757164 s
310 <-- produce filter count of 310
```

###### Spark Tutorial (U. of Maryland)
  * http://lintool.github.io/SparkTutorial/

###### Spark Pi Calculator

```
# ./pi.py 2>/dev/null
Pi is roughly 3.133120
```

###### Spark KMean
  * http://blog.cloudera.com/blog/2015/09/how-to-prepare-your-apache-hadoop-cluster-for-pyspark-jobs/
  * http://web.cs.ucla.edu/~zhoudiyu/tutorial/

```
# python get-pip.py
# pip install -I numpy==1.9.3
# ./kmean.py 2>/dev/null

1
0
1
```

###### Spark Interactive with Shakespeare
  * https://districtdatalabs.silvrback.com/getting-started-with-spark-in-python

```
# wget -O shakespeare.txt.zip https://www.dropbox.com/s/4xc9zberq0vkpoh/shakespeare.txt.zip?dl=1
# unzip shakespeare.txt.zip
# ./pyspark
>>> text = sc.textFile("shakespeare.txt")
15/10/10 18:07:55 INFO MemoryStore: ensureFreeSpace(148738) called with curMem=0, maxMem=278302556
15/10/10 18:07:55 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 145.3 KB, free 265.3 MB)
15/10/10 18:07:55 INFO MemoryStore: ensureFreeSpace(28571) called with curMem=148738, maxMem=278302556
15/10/10 18:07:55 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 27.9 KB, free 265.2 MB)
15/10/10 18:07:55 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on localhost:50877 (size: 27.9 KB, free: 265.4 MB)
15/10/10 18:07:55 INFO BlockManagerMaster: Updated info of block broadcast_0_piece0
15/10/10 18:07:55 INFO SparkContext: Created broadcast 0 from textFile at NativeMethodAccessorImpl.java:-2

>>> print text
shakespeare.txt MapPartitionsRDD[1] at textFile at NativeMethodAccessorImpl.java:-2

>>> from operator import add

>>> def tokenize(text):
...     return text.split()
...

>>> words = text.flatMap(tokenize)

>>> wc = words.map(lambda x: (x,1))

>>> print wc.toDebugString()
15/10/10 18:10:51 INFO FileInputFormat: Total input paths to process : 1
(2) PythonRDD[2] at RDD at PythonRDD.scala:43 []
 |  shakespeare.txt MapPartitionsRDD[1] at textFile at NativeMethodAccessorImpl.java:-2 []
 |  shakespeare.txt HadoopRDD[0] at textFile at NativeMethodAccessorImpl.java:-2 []

>>> counts = wc.reduceByKey(add)

>>> counts.saveAsTextFile("wc")

# head wc/part-00000
(u'fawn', 14)
(u'Fame.', 1)
(u'Fame,', 2)
(u'kinghenryviii@7731', 1)
(u'othello@36737', 1)
(u'loveslabourslost@51678', 1)
(u'1kinghenryiv@54228', 1)
(u'troilusandcressida@83747', 1)
(u'fleeces', 1)
(u'midsummersnightsdream@71681', 1)
```

###### Spark Airline delay traffic analysis
  * https://districtdatalabs.silvrback.com/getting-started-with-spark-in-python

```
# wget -O ontime/ontime.zip https://www.dropbox.com/s/gnzztknnhrx81uv/ontime.zip?dl=1
# unzip ontime/ontime.zip
# ./airline_delay.py
-45442 minutes delayed	Alaska Airlines Inc.: AS
-20654 minutes delayed	Hawaiian Airlines Inc.: HA
39247 minutes delayed	AirTran Airways Corporation: FL
40841 minutes delayed	Virgin America: VX
108480 minutes delayed	Frontier Airlines Inc.: F9
177717 minutes delayed	US Airways Inc.: US
279981 minutes delayed	JetBlue Airways: B6
390614 minutes delayed	United Air Lines Inc.: UA
431755 minutes delayed	American Airlines Inc.: AA
461753 minutes delayed	Delta Air Lines Inc.: DL
493527 minutes delayed	Envoy Air: MQ
519867 minutes delayed	SkyWest Airlines Inc.: OO
1160058 minutes delayed	ExpressJet Airlines Inc.: EV
2181955 minutes delayed	Southwest Airlines Co.: WN
```

###### Sparkling Water
  * http://blog.cloudera.com/blog/2015/10/how-to-build-a-machine-learning-app-using-sparkling-water-and-apache-spark/
