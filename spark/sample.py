#! /usr/bin/env pyspark
#
# http://hortonworks.com/hadoop-tutorial/hands-on-tour-of-apache-spark-in-5-minutes/

from pyspark import SparkContext

sc =SparkContext()
myLines = sc.textFile('hdfs://sandbox.hortonworks.com/user/guest/Hortonworks')
myLines_filtered = myLines.filter( lambda x: len(x) > 0 )
print myLines_filtered.count()
