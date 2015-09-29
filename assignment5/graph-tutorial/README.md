##### How to run?

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
