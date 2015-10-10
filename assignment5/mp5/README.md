# cloudapp-mp5
	
   * Part B :
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1507
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1518
   * Part C :
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1395
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1446
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1489
	- http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L21-MinPath.htm
	- http://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec12/lec12-28.html
   * Part D :
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1374
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1399
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1474
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1492

##### Part A: Connected Components of Giraph

  * to build a Giraph application which finds Connected Components in a graph. This first exercise is similar to Tutorial 5: Introduction to Giraph.
  * NOTE input: Data input format is similar to the format used with the Connected Components part of Tutorial 5: Introduction to Giraph in that each line starts with the node_id, which is then followed by its neighbor nodes, for instance:
  * Note output: Each line in your output will correspond to one node and the “connected component id”, for example:

```
0 0
1 1
2 1
3 0
..
```

  * Run Giraph and examine output part-a

```
# ./start.sh
# ./run_a.sh
6	0
5	0
0	0
8	7
7	7
2	0
1	0
9	7
3	0
4	0
```

##### Part B: KMeans Clustering Model

  * to build a clustering model application using MLlib, which clusters samples in a give data set. This exercise is similar to Tutorial 5: Introduction to MLlib.
  * NOTE input: Data input format is similar to the format used in the K-means portion of Tutorial 5: Introduction to MLlib in that each line starts with the sample_id which is then followed its features, for instance:
  * Note output: Each line in your output will correspond to one node cluster and samples which fit into that cluster, for example:
  * Run Spark and examine output part-b (Variable “results” contains cluster number and then iterable members:)

```
	JavaPairRDD<Integer, Iterable<String>> results;

# ./start.sh
# ./run_b.sh
["Hornet Sportabout", "Duster 360", "Merc 450SE", "Merc 450SL", "Merc 450SLC", "Cadillac Fleetwood", "Lincoln Continental", "Chrysler Imperial", "Dodge Challenger", "AMC Javelin", "Camaro Z28", "Pontiac Firebird", "Ford Pantera L", "Maserati Bora", ]
["Datsun 710", "Merc 240D", "Merc 230", "Toyota Corona", "Porsche 914-2", "Lotus Europa", "Volvo 142E", ]
["Fiat 128", "Honda Civic", "Toyota Corolla", "Fiat X1-9", ]
["Mazda RX4", "Mazda RX4 Wag", "Hornet 4 Drive", "Valiant", "Merc 280", "Merc 280C", "Ferrari Dino", ]
(0,["Datsun 710", "Merc 240D", "Merc 230", "Toyota Corona", "Porsche 914-2", "Lotus Europa", "Volvo 142E"])
(2,["Mazda RX4", "Mazda RX4 Wag", "Hornet 4 Drive", "Valiant", "Merc 280", "Merc 280C", "Ferrari Dino"])
(1,["Hornet Sportabout", "Duster 360", "Merc 450SE", "Merc 450SL", "Merc 450SLC", "Cadillac Fleetwood", "Lincoln Continental", "Chrysler Imperial", "Dodge Challenger", "AMC Javelin", "Camaro Z28", "Pontiac Firebird", "Ford Pantera L", "Maserati Bora"])
(3,["Fiat 128", "Honda Civic", "Toyota Corolla", "Fiat X1-9"])
```

##### Part C: Shortest Paths in Giraph

   * to implement a shortest path algorithm in Giraph.
   * to test this application on a graph data set that is stored in the file “graph.txt”.
   * NOTE input: The data input format is similar to the format used with the Connected Components part of Tutorial 5: Introduction to Giraph in that each line starts with the node_id which is then followed by its neighbor nodes, for insta
   * Note output: Each line in your output will correspond to one node and its shortest path length, for example:

   * https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1395
	- by default, the algorithm finds the shortest path from vertex 1 (and not 0): 
	- adding this: "-ca SimpleShortestPathsVertex.sourceId=3".

```
# *** input data ***
# ./start.sh
# hadoop fs -cat /mp5/data/graph.data
0 1 2
1 0 2
2 0 1 3 4
3 2 4 6
4 2 3 5 6
5 4
6 3 4
7 8 9
8 7 9
9 7 8
```

```
...
# *** output data ***
6	1
5	2
0	2
8	2147483647
7	2147483647
2	1
1	2
9	2147483647
3	0
4	1
...
```

   * Note that if there is no path between two nodes you should return a big int, in JAVA:

##### Part D: Random Forest Classifier

   * to use Random Forest models for classifying the dataset and finding the accuracy of the dataset.
   * to use the “RandomForestMP” class to classify samples.
   * NOTE input: The data input format is similar to the format used with the Connected Components part of Tutorial 5: Introduction to MLlib in that each line has a feature set with a last number corresponding to label (0 or 1), for instance:
   * Note output: The output will be the result of testing the trained model on a testing dataset. Results for each sample in the testing data set will include the predicted label and the actual label along with the feature set, for example:

###### Advisory from Part D:

```
It is "new Function<Vector, LabeledPoint>" because you input a Vector with the features to classify and returns a LabeledPoint which is the predicted classification result (here called the "label"). So I think your problem is the variable types your are using in your parsed data.

Here is a hint:

   - Parse the training data into a JavaRDD<LabeledPoint> variable. You can do it with a minor modification to one of the parser classes provided in the KMeans example. Check the JavaDoc for the LabeledPoint constructor. Note that you need a LabeledPoint because to train your classifier you need to relate a set of "features" with a "label". (and also is the parameter type requested by the training method of Point 3 below)
   - Parse the test data into a JavaRDD<Vector> variable called "test". Check the Kmeans example to get inspiration on how to do this. Note in this case you just need the "features" (Vector), the "label" with be predicted by your classifier. 
   - Use your training data to train the classifier. Read the JavaDoc for RandomForest to get inspiration on how to do this.

If you check JavaDoc (https://spark.apache.org/docs/1.0.1/api/java/org/apache/spark/mllib/regression/LabeledPoint.html) for LabeledPoint, you'll see this constructor:

LabeledPoint(double label, Vector features) 

You are calling it this way:

LabeledPoint(new ParseTitle(line), new ParsePoint(line))

As first parameter you are passing an instance of ParseTitle while the compiler is expecting a double (the label). Similarly compiler is expecting a Vector (the features) as second param and you are passing an instance of ParsePoint. That is the reason why the compiler is complaining. 

So what you should have done is, inside your overridden method call inside your class ParseLabeledPoint, read the string that you receive as input and instantiate a LabeledPoint with the info contained in that string. (split string by commas then Vector=[0-n-1] item, Double=n item (last item)).

Once done, use your ParseLabeledPoint to parse your training data and use your class ParsePoint to parse your test data. (Discard your ParseTitle as you do not need it for this assignment).

```

   * Output results

```
...
(1.0,[1.0,122.0,84.0,47.0,240.0,45.8,0.551,31.0])
(0.0,[5.0,110.0,80.0,35.0,3.0,29.0,0.263,29.0])
(0.0,[13.0,140.0,94.0,34.0,146.0,36.6,0.254,51.0])
(0.0,[2.0,110.0,92.0,18.0,10.0,22.7,0.235,48.0])
(1.0,[6.0,141.0,110.0,24.0,20.0,45.4,0.721,54.0])
(0.0,[1.0,110.0,74.0,18.0,60.0,30.5,0.285,26.0])
(0.0,[2.0,118.0,88.0,39.0,10.0,36.5,1.057,37.0])
(0.0,[6.0,125.0,72.0,0.0,0.0,26.3,0.258,52.0])
(0.0,[1.0,126.0,60.0,0.0,0.0,30.1,0.349,47.0])
(0.0,[1.0,93.0,70.0,31.0,0.0,30.4,0.315,23.0])
...
```
