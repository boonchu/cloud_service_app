mkdir -p output{1,2,3}
hadoop fs -get /tutorial/output1/part* ./output1
hadoop fs -get /tutorial/output2/part* ./output2
hadoop fs -get /tutorial/output3/part* ./output3
