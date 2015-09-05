mkdir dataset
wget -c http://www.gutenberg.lib.md.us/2/2/6/2264/2264.txt -P dataset 
wget -c http://www.gutenberg.lib.md.us/2/2/6/2265/2265.txt -P dataset 
wget -c http://www.gutenberg.lib.md.us/2/2/6/2266/2266.txt -P dataset 
wget -c http://www.gutenberg.lib.md.us/2/2/6/2267/2267.txt -P dataset
hadoop fs -mkdir -p /tutorial/input
hadoop fs -put ./dataset/* /tutorial/input
hadoop fs -ls  /tutorial/input
# wget https://github.com/xldrx/mapreduce_examples/raw/master/tutorial/first_example/WordCount.java
