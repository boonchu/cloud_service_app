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
0	2
8 	2147483647
...
```

   * Note that if there is no path between two nodes you should return a big int, in JAVA:

##### Part D: Random Forest Classifier

   * to use Random Forest models for classifying the dataset and finding the accuracy of the dataset.
   * to use the “RandomForestMP” class to classify samples.
   * NOTE input: The data input format is similar to the format used with the Connected Components part of Tutorial 5: Introduction to MLlib in that each line has a feature set with a last number corresponding to label (0 or 1), for instance:
   * Note output: The output will be the result of testing the trained model on a testing dataset. Results for each sample in the testing data set will include the predicted label and the actual label along with the feature set, for example:

```
...
(1.0,[1.0,122.0,84.0,47.0,240.0,45.8,0.551,31.0])
(0.0,[5.0,110.0,80.0,35.0,3.0,29.0,0.263,29.0])
...
```
