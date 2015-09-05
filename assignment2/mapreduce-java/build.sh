export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
mkdir -p build/WordCount build/WordCount2 build/TopWords
hadoop com.sun.tools.javac.Main WordCount.java -d build/WordCount
jar -cvf WordCount.jar -C build/WordCount/ ./

hadoop com.sun.tools.javac.Main WordCount2.java -d build/WordCount2
jar -cvf WordCount2.jar -C build/WordCount2/ ./

hadoop com.sun.tools.javac.Main TopWords.java -d build/TopWords 
jar -cvf TopWords.jar -C build/TopWords/ ./
