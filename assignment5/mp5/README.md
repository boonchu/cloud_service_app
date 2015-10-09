# cloudapp-mp5
	
   * Part B : https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1507
   * Part C :
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1446
	- https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1489
   * Part D : https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1399

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
# hadoop jar target/mp5-1.0-SNAPSHOT-jar-with-dependencies.jar org.apache.giraph.GiraphRunner ConnectedComponentsComputation -vif org.apache.giraph.io.formats.IntIntNullTextInputFormat -vip /mp5/data/graph.data -vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat -op /mp5/output/part-a -w 1 -ca giraph.SplitMasterWorker=false
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

```
...
0	2
8 	2147483647
...
```

   * Note that if there is no path between two nodes you should return a big int, in JAVA:
