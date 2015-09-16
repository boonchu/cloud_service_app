###### HBase Tutorial
  * Starting HBase

```
# bash ~/start_hbase.sh
Starting HBase...
Starting Postgre SQL                                      [  OK  ]
Starting name node                                        [  OK  ]
Starting zookeeper nodes                                  [  OK  ]
Starting hbase master                                     [  OK  ]
Starting hbase thrift                                     [  OK  ]
Starting hbase stargate                                   [  OK  ]
Starting hbase regionservers                              [  OK  ]
====================================

HBase autostart enabled
To disable auto-start of HBase do
  # chkconfig hbase-starter off

====================================
```
  * Compile with maven
      - https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1176

```
$ export CLASSPATH=$(/usr/bin/hbase classpath)
$ echo $CLASSPATH | tr ":" "\n" | grep hadoop | head
/usr/hdp/2.3.0.0-2557/hbase/lib/hbase-hadoop2-compat-1.1.1.2.3.0.0-2557.jar
/usr/hdp/2.3.0.0-2557/hbase/lib/hbase-hadoop2-compat.jar
/usr/hdp/2.3.0.0-2557/hbase/lib/hbase-hadoop-compat-1.1.1.2.3.0.0-2557.jar
/usr/hdp/2.3.0.0-2557/hbase/lib/hbase-hadoop-compat.jar
/usr/hdp/2.3.0.0-2557/hadoop/conf
/usr/hdp/2.3.0.0-2557/hadoop/lib/*
/usr/hdp/2.3.0.0-2557/hadoop/.//*
/usr/hdp/2.3.0.0-2557/hadoop-hdfs/./
/usr/hdp/2.3.0.0-2557/hadoop-hdfs/lib/*
/usr/hdp/2.3.0.0-2557/hadoop-hdfs/.//*
$ mvn clean package
```

  * Create Table 'emp'

```
$ cd target
$ hadoop jar hbase.example-1.0-SNAPSHOT.jar CreateTable
$ hbase shell
hbase(main):004:0> describe 'emp'
Table emp is ENABLED
emp
COLUMN FAMILIES DESCRIPTION
{NAME => 'personal', DATA_BLOCK_ENCODING => 'NONE', BLOOMFILTER => 'ROW', REPLICATION_SCOPE => '0', VERSIONS => '1', COMPRESSION => 'NONE', MIN_
VERSIONS => '0', TTL => 'FOREVER', KEEP_DELETED_CELLS => 'FALSE', BLOCKSIZE => '65536', IN_MEMORY => 'false', BLOCKCACHE => 'true'}
{NAME => 'professional', DATA_BLOCK_ENCODING => 'NONE', BLOOMFILTER => 'ROW', REPLICATION_SCOPE => '0', VERSIONS => '1', COMPRESSION => 'NONE',
MIN_VERSIONS => '0', TTL => 'FOREVER', KEEP_DELETED_CELLS => 'FALSE', BLOCKSIZE => '65536', IN_MEMORY => 'false', BLOCKCACHE => 'true'}
2 row(s) in 0.1470 seconds

hbase(main):005:0> disable 'emp'
0 row(s) in 2.2830 seconds

hbase(main):006:0> drop 'emp'
0 row(s) in 1.2590 seconds
```
  
  * List tables

```
$ cd target
$ hadoop jar hbase.example-1.0-SNAPSHOT.jar ListTables
```

  * Insert Data 

```
$ cd target
$ hadoop jar hbase.example-1.0-SNAPSHOT.jar InsertData
```

  * Retrieve Data

```
$ hadoop jar hbase.example-1.0-SNAPSHOT.jar RetrieveData
name: raju city: hyderabad
```

  * Scan Table

```
$ hadoop jar hbase.example-1.0-SNAPSHOT.jar ScanTable
Found row : keyvalues={row1/personal:city/1442417706474/Put/vlen=9/seqid=0, row1/personal:name/1442417706474/Put/vlen=4/seqid=0}
```
