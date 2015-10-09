##### How to run?

##### MLib - KMean

 * Use dataset from the 1974 Motor Trend US magazine. Dataset comprises fuel consumption and 10 aspects of automobile design and performance for 32 automobiles (1973–74 models).

```
# hadoop fs -cat /mllib-tutorial/input/mtcars.csv
"Mazda RX4",21,6,160,110,3.9,2.62,16.46,0,1,4,4
"Mazda RX4 Wag",21,6,160,110,3.9,2.875,17.02,0,1,4,4
"Datsun 710",22.8,4,108,93,3.85,2.32,18.61,1,1,4,1
"Hornet 4 Drive",21.4,6,258,110,3.08,3.215,19.44,1,0,3,1
"Hornet Sportabout",18.7,8,360,175,3.15,3.44,17.02,0,0,3,2
"Valiant",18.1,6,225,105,2.76,3.46,20.22,1,0,3,1
```

 * Conceptual Graph

```
Our goal is to use these properties to cluster cars into multiple categories using the K-means algorithm. K-means is one of the most used algorithms in Machine Learning. K-means belong to a class of machine learning algorithms called clustering algorithms. Clustering is the task of grouping a set of objects in such a way that objects in the same group (called a cluster) are more similar (in some sense or another) to each other than to those in other groups (clusters) [Wikipedia].
```

 * Compile and run

```
$ mvn clean package
$ spark-submit --class KMeansExample target/mllib.mp-1.0-SNAPSHOT.jar 3 /mllib-tutorial/input/mtcars.csv 2> /dev/null
["Mazda RX4", "Mazda RX4 Wag", "Hornet 4 Drive", "Valiant", "Merc 280", "Merc 280C", "Ferrari Dino", ]
["Datsun 710", "Merc 240D", "Merc 230", "Fiat 128", "Honda Civic", "Toyota Corolla", "Toyota Corona", "Fiat X1-9", "Porsche 914-2", "Lotus Europa", "Volvo 142E", ]
["Hornet Sportabout", "Duster 360", "Merc 450SE", "Merc 450SL", "Merc 450SLC", "Cadillac Fleetwood", "Lincoln Continental", "Chrysler Imperial", "Dodge Challenger", "AMC Javelin", "Camaro Z28", "Pontiac Firebird", "Ford Pantera L", "Maserati Bora", ]
```

##### Naive Bayes classifier

 * data set description

```
This dataset is comprised of 768 observations of medical details for Pima Indians patients. The records describe instantaneous measurements taken from the patient such as their age, the number of times pregnant, and their blood workup. All patients are women aged 21 or older. All attributes are numeric, and their units vary from attribute to attribute.
Each record has a class value that indicates whether the patient suffered an onset of diabetes within five years of when the measurements were taken (1) or not (0); you can see this indicator in the last column.
The goal of a classifier is to build a model that takes a patient’s set of measurements and gives a result that could tell you whether that patient is diabetic.
This is a standard dataset that has been studied a lot in machine learning literature. A good prediction accuracy is 70%-76%.
```

 * Create training data set by extracting 615 samples and 153 samples for testing

```
head -615 data/pima-indians-diabetes.data > data/training.data
tail -153 data/pima-indians-diabetes.data > data/test.data
hadoop fs -put data/training.data /mllib-tutorial/input
hadoop fs -put data/test.data /mllib-tutorial/input

# hadoop fs -ls /mllib-tutorial/input/*
-rw-r--r--   1 root hdfs       1745 2015-09-24 15:22 /mllib-tutorial/input/mtcars.csv
-rw-r--r--   1 root hdfs       4650 2015-10-09 14:15 /mllib-tutorial/input/test.data
-rw-r--r--   1 root hdfs      18629 2015-10-09 14:15 /mllib-tutorial/input/training.data
[root@sandbox graph-tutorial]#
```

 * Submit Apache Spark job to classify the Pima Indian Diabete Dataset

```
# spark-submit --class NaiveBayesExample target/mllib.mp-1.0-SNAPSHOT.jar /mllib-tutorial/input/training.data /mllib-tutorial/input/test.data 2>/dev/null
0.5228758169934641
```

 * Check the accuracy of dataset

```
You should see a number about 0.61, which means the classifier has built a model which is capable of labeling sample patients as diabetics or non diabetic with accuracy of 61%. As we pointed out, a good prediction accuracy for this dataset is about 70%-76%.

To build better classifiers there are usually three options:
	1. Try other algorithms,
	2. Add more data with more sample patients (the true meaning of Big Data), and
	3. Extend the feature set, meaning you gather more information and measurements for each patient.
```
