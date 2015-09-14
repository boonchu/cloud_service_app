
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileReaderSpout implements IRichSpout {

  public static final Log log = LogFactory.getLog(FileReaderSpout.class);

  private SpoutOutputCollector _collector;
  private TopologyContext context;

  @Override
  public void open(Map conf, TopologyContext context,
                   SpoutOutputCollector collector) {

     /*
    ----------------------TODO-----------------------
    Task: initialize the file reader
    ------------------------------------------------- */
    
    final String datafile = (String) conf.get("datafile");

/*
    log.info("[DEBUG] File Name is: datafile=" + new String(datafile));
*/

    try {

	final FileReader filehandler = new FileReader(datafile);
	final BufferedReader br = new BufferedReader(filehandler); 
	context.setTaskData("reader", br);

    } catch (FileNotFoundException e) {
	e.printStackTrace();
    }	

    this.context = context;
    this._collector = collector;
  }

  @Override
  public void nextTuple() {
     /*
    ----------------------TODO-----------------------
    Task:
    1. read the next line and emit a tuple for it
    2. don't forget to sleep when the file is entirely read to prevent a busy-loop
    ------------------------------------------------- */

	final BufferedReader br = (BufferedReader) this.context.getTaskData("reader");

	final String line;
	try {
		if ((line = br.readLine()) != null) {
			_collector.emit(new Values(line));
		} 
	} catch (IOException e) {
		e.printStackTrace();
	}	

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {

    declarer.declare(new Fields("word"));

  }

  @Override
  public void close() {
   /*
    ----------------------TODO-----------------------
    Task: close the file
    ------------------------------------------------- */

	BufferedReader br = (BufferedReader) this.context.getTaskData("reader");

  }


  @Override
  public void activate() {
  }

  @Override
  public void deactivate() {
  }

  @Override
  public void ack(Object msgId) {
  }

  @Override
  public void fail(Object msgId) {
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
