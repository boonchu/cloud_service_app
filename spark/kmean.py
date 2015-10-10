#! /usr/bin/env pyspark
from pyspark import SparkContext
from pyspark.mllib.clustering import KMeans
from numpy import array
from math import sqrt

sc = SparkContext()

#4 data points (0.0, 0.0), (1.0, 1.0), (9.0, 8.0) (8.0, 9.0)
data = array([0.0,0.0, 1.0,1.0, 9.0,8.0, 8.0,9.0]).reshape(4,2)

#Generate K means
model = KMeans.train(sc.parallelize(data), 2, maxIterations=100, runs=300, initializationMode="random")

#Print out the cluster of each data point
print (model.predict(array([0.0, 0.0])))
print (model.predict(array([1.0, 1.0])))
print (model.predict(array([9.0, 8.0])))
print (model.predict(array([8.0, 0.0])))
