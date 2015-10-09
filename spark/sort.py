#! /usr/bin/env pyspark

from __future__ import print_function
import sys
from pyspark import SparkContext


if __name__ == "__main__":

    if len(sys.argv) != 2:
        print("Usage: sort <file>", file=sys.stderr)
        exit(-1)
    sc = SparkContext(appName="PythonSort")

    lines = sc.textFile(sys.argv[1], 1)
    sortedCount = lines.flatMap(lambda x: x.split(' ')) \
        .map(lambda x: (int(x), 1)) \
        .sortByKey(lambda x: x)
    # This is just a demo on how to bring all the sorted data back to a single node.
    # In reality, we wouldn't want to collect all the data to the driver node.
    output = sortedCount.collect()
    for (num, unitcount) in output:
        print(num)

    sc.stop()
