###### Connected Components

	* look at the Connected Components algorithm and how the code works. The connected component of a graph 
	is a subgraph in which any two vertices are connected to each other by paths and which is connected to 
	no additional vertices in the supergraph.

	* setup

```
hadoop fs -mkdir -p /giraph-tutorial/input
hadoop fs -mkdir -p /giraph-tutorial/output
mvn clean package
cat data/tiny2_graph.txt
hadoop fs -put data/tiny2_graph.txt /giraph-tutorial/input
```

	* run giraph

```
hadoop fs -cat '/giraph-tutorial/input/tiny2_graph.txt'
0 1 3
1 0 2 3
2 1 4
3 0 1 4
4 3 2
5 6 7
6 5 7
7 5 6
8 9
9 8

hadoop fs -rm -r -f /giraph-tutorial/output/connected-components*

hadoop jar target/giraph-mp-1.0-SNAPSHOT-jar-with-dependencies.jar org.apache.giraph.GiraphRunner ConnectedComponentsComputation -vif org.apache.giraph.io.formats.IntIntNullTextInputFormat -vip /giraph-tutorial/input/tiny2_graph.txt -vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat -op /giraph-tutorial/output/connected-components -w 1 -ca giraph.SplitMasterWorker=false

hadoop fs -cat /giraph-tutorial/output/connected-components/*
6	5
5	5
0	0
8	8
7	5
2	0
1	0
9	8
3	0
4	0

	As you can see, the application has found three connected components with identifiers 0,5,8:

hadoop fs -rm -r -f /giraph-tutorial/output/connected-components*

```

###### Shortest Path

	*  shortest path problem is one of the old problems in graph theory. The goal is to find a path between
	two vertices in a graph such that the number of edges 
	(or if edges are weighted, sum of the weights) is minimized.

```
hadoop jar giraph-examples.jar org.apache.giraph.GiraphRunner org.apache.giraph.examples.SimpleShortestPathsComputation -vif org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat -vip /giraph-tutorial/input/tiny_graph.txt -vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat -op /giraph-tutorial/output/shortestpaths -w 1 -ca giraph.SplitMasterWorker=false
```
