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
