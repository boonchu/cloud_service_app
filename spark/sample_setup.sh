#! /usr/bin/env bash

wget -O ./Hortonworks http://en.wikipedia.org/wiki/Hortonworks
hadoop fs -rm /user/guest/Hortonworks
hadoop fs -put ./Hortonworks /user/guest/Hortonworks
hadoop fs -ls /user/guest/Hortonworks
