#!/bin/bash

run_assignment() {
	echo "${yellow}	Compile the Code${reset}"
        echo "${yellow} PREFIX is ${PREFIX}${reset}"
        echo "${yellow} Copy java code to ${PREFIX}${reset}"
	mkdir -p ${PREFIX} && cp ./src/${1}.java ${PREFIX}/
	#javac $1.java -d $PREFIX
	export CLASSPATH=$(/usr/bin/hbase classpath)
	export HADOOP_CLASSPATH=$(hbase classpath)
	mvn clean package

	# fix bug 
	# https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1219
	echo "${yellow}	Run${reset}"
	#java $1 > $1.output 2> $1.log
	cp ./target/hbase.example-1.0-SNAPSHOT.jar $PREFIX/
	cd $PREFIX	
	hadoop jar hbase.example-1.0-SNAPSHOT.jar $1 > $1.output 2> $1.log
	
	echo "${yellow}	Output${reset}"
	tail -20 $1.output
	echo
	echo "${yellow}	Log${reset}"
	tail -5 $1.log
 
	echo "${yellow}	Collect the Output${reset}"
	echo "scan 'powers'" | hbase shell > $1.hbase
	echo
	echo "${yellow}	Hbase output${reset}"
	tail -20 $1.hbase

        cd ..
}

echo "${green}Reset the Environment${reset}"
echo "disable 'powers';drop 'powers'" | hbase shell
rm -rf $PREFIX

echo "${green}Running Assingment${reset}"
run_assignment SuperTable
