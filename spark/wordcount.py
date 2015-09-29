#! /usr/bin/env pyspark

import sys
import codecs
from operator import add
from pyspark import SparkContext

if __name__ == "__main__":
    sc = SparkContext()
    lines = sc.textFile('hdfs://sandbox.hortonworks.com/user/guest/Hortonworks')
    counts = lines.flatMap(lambda x: x.split(' ')) \
                  .map(lambda x: (x, 1)) \
                  .reduceByKey(add)
    output = counts.collect()
    UTF8Writer = codecs.getwriter('utf8')
    sys.stdout = UTF8Writer(sys.stdout)
    for (word, count) in output:
        print "%s: %i" % (word, count)
