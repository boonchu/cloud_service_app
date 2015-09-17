#!/bin/bash

echo "${green}Reset the Environment${reset}"
echo "disable 'powers';drop 'powers'" | hbase shell
rm -rf $PREFIX

run_assignment() {
	echo "${yellow}	Compile the Code${reset}"
	mkdir $PREFIX
	cp $1.java $PREFIX/
	javac $PREFIX/$1.java -d $PREFIX

	echo "${yellow}	Run${reset}"
	cd $PREFIX
	# fix bug 
	# https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1219
	java $1 > $PREFIX/$1.output 2> $PREFIX/$1.log
	cd -

	echo "${yellow}	Collect the Output${reset}"
	echo "scan 'powers'" | hbase shell > $PREFIX/$1.hbase

}

echo "${green}Running Assingment${reset}"
run_assignment SuperTable
