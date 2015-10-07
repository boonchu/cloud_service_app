#!/usr/bin/env bash
spark-submit --class KMeansMP target/mp5-1.0-SNAPSHOT-jar-with-dependencies.jar /mp5/data/cars.data /mp5/output/part-b/ 2>/dev/null
hadoop fs -cat /mp5/output/part-b/*
