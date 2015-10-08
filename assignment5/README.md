###### Giraph tutorials

  * https://www.codatlas.com/github.com/apache/giraph/trunk/giraph-examples/src/main/java/org/apache/giraph/examples/
  * http://stackoverflow.com/questions/29095959/gremlin-giraph-graphx-on-titandb

###### Giraph examples

  * https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1395

```
# git clone -b release-1.1 https://github.com/apache/giraph.git
# cd giraph && mvn -Phadoop_2 -Dhadoop.version=2.6.0 package -e -DskipTests=true

# ls giraph/giraph-core/target/*.jar
giraph/giraph-core/target/giraph-1.1.0-for-hadoop-2.6.0.jar                        giraph/giraph-core/target/giraph-1.1.0-for-hadoop-2.6.0-sources.jar
giraph/giraph-core/target/giraph-1.1.0-for-hadoop-2.6.0-jar-with-dependencies.jar  giraph/giraph-core/target/giraph-1.1.0-for-hadoop-2.6.0-tests.jar

# cp giraph/giraph-core/target/*.jar .
# mvn clean
# cat tiny_graph.txt
[0,0,[[1,1],[3,3]]]
[1,0,[[0,1],[2,2],[3,1]]]
[2,0,[[1,2],[4,4]]]
[3,0,[[0,3],[1,1],[4,4]]]
[4,0,[[3,4],[2,4]]]

# hadoop fs -put tiny_graph.txt /giraph-tutorial/input
# hadoop fs -cat '/giraph-tutorial/input/tiny_graph.txt'
# hadoop fs -rm -r '/giraph-tutorial/output/shortestpaths'
#
# cd giraph-examples && mvn clean package
# cp giraph/giraph-core/target/giraph-1.1.0-for-hadoop-2.6.0-jar-with-dependencies.jar giraph-core.jar
# cp giraph-examples/target/giraph-examples-1.1.0-for-hadoop-1.2.1-jar-with-dependencies.jar giraph-examples.jar
# 
# hadoop jar giraph-examples.jar org.apache.giraph.GiraphRunner org.apache.giraph.examples.SimpleShortestPathsComputation -vif org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat -vip /giraph-tutorial/input/tiny_graph.txt -vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat -op /giraph-tutorial/output/shortestpaths -w 1 -ca giraph.SplitMasterWorker=false
```
