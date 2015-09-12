###### Use Python

```
./pull-dataset.sh

./run.sh

./print.sh

# cat output3/part-0000* | sort -n -k 2 -r | head -10
(u'I', 2416)
(u'you', 1678)
(u'my', 1389)
(u'it', 1015)
(u'd', 1001)
(u'that', 998)
(u'me', 834)
(u'this', 831)
(u'your', 813)
(u'with', 729)

./clean.sh
```

```
# su - hdfs -c "hdfs dfsadmin -report -live" | egrep -A 3 '^Live'
Live datanodes (1):

Name: 10.0.2.15:50010 (sandbox.hortonworks.com)
Hostname: sandbox.hortonworks.com
```

* Links:
  - http://www.michael-noll.com/tutorials/writing-an-hadoop-mapreduce-program-in-python/
