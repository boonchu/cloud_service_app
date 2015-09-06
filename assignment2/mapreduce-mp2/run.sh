#!/bin/bash

export USER_ID=0
export DATASET_N=5
export DATASET_PATCH=5

export HDFS_HOME=/mp2
export XL_HOME=./internal_use
export PREFIX=$XL_HOME/tmp
export DATA_HOME=$PREFIX/dataset
export LOG=$PREFIX/logs.txt
# export ERR=$PREFIX/errors.txt
export ERR=$LOG
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
export N=${3-10}
export DATASET=mini

export red=`tput setaf 1`
export green=`tput setaf 2`
export yellow=`tput setaf 3`
export reset=`tput sgr0`

run_assignment() {
	echo "${yellow}	Compile the Code${reset}"
	cp $1.java $PREFIX/
	rm -rf $PREFIX/build
	mkdir -p $PREFIX/build
	echo "${yellow}	House Keeping${reset}"

	hadoop fs -rm -r -f $HDFS_HOME/$1-output* 

	hadoop com.sun.tools.javac.Main $PREFIX/$1.java -d $PREFIX/build 
	jar -cvf $PREFIX/$1.jar -C $PREFIX/build/ . 

	echo "${yellow}	Run${reset}"
	hadoop jar $PREFIX/$1.jar $1 $2 $HDFS_HOME/$1-output 

	echo "${yellow}	Collect the Output${reset}"
	hadoop fs -cat $HDFS_HOME/$1-output/* > $PREFIX/output-$1.txt

	echo "${yellow}	House Keeping${reset}"
	hadoop fs -rm -r -f $HDFS_HOME/$1-output/* 

	echo "${yellow}	Post Processing${reset}"
	sort $3 $PREFIX/output-$1.txt -o $PREFIX/output-tmp.txt
	head -n 100 $PREFIX/output-tmp.txt > $PREFIX/$1.output
 	rm $PREFIX/output-tmp.txt

	md5sum $PREFIX/$1.output  > $PREFIX/$1.hash
# 	md5sum $PREFIX/output-TitleCount.txt | awk '{ print $1 }' >> $PREFIX/results.txt
	
	echo "result from USERID = 0"
	cat $PREFIX/output-$1.txt
}

echo "${green}Running Assingment A: Title Count${reset}"
run_assignment TitleCount "-D stopwords=$HDFS_HOME/misc/stopwords.txt -D delimiters=$HDFS_HOME/misc/delimiters.txt  $HDFS_HOME/titles" "-n -k2 -r"
