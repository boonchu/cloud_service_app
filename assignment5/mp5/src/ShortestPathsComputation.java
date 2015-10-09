import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Compute shortest paths from a given source.
 *
@Algorithm(
  name = "Shortest paths",
  description = "Finds all shortest paths from a selected vertex"
)

 */
public class ShortestPathsComputation extends BasicComputation<
    IntWritable, IntWritable, NullWritable, IntWritable> {

  /** The shortest paths id */
  public static final LongConfOption SOURCE_ID =
      new LongConfOption("SimpleShortestPathsVertex.sourceId", 1,
          "The shortest paths id");

  /** Class logger */
  private static final Logger LOG =
      Logger.getLogger(ShortestPathsComputation.class);

  /**
   * Is this vertex the source id?
   *
   * @param vertex Vertex
   * @return True if the source id
   */
  private boolean isSource(Vertex<IntWritable, ?, ?> vertex) {
    return vertex.getId().get() == SOURCE_ID.get(getConf());
  }

  @Override
  public void compute(
      Vertex<IntWritable, IntWritable, NullWritable> vertex,
      Iterable<IntWritable> messages) throws IOException {

    // Integer.MAX_VALUE and MIN_VALUE are the largest/smallest values representable with an int .
    // That's the point: you can't have an int with lesser or greater values. 
    if (getSuperstep() == 0) {
      vertex.setValue(new IntWritable(Integer.MAX_VALUE));
    }

    int minDist = isSource(vertex) ? 0 : Integer.MAX_VALUE;

    for (IntWritable message : messages) {
      minDist = Math.min(minDist, message.get());
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("Vertex " + vertex.getId() + " got minDist = " + minDist + " vertex value = " + vertex.getValue());
    }

    // http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L21-MinPath.htm
    if (minDist < vertex.getValue().get()) {

      vertex.setValue(new IntWritable(minDist));

      for (Edge<IntWritable, NullWritable> edge : vertex.getEdges()) {

	// https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1454
	// https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1446
	//
	// modify the example code with Giraph to make it read in the IntIntNullTextInputFormat input and 
	// set 1 to any edge value.
        int distance = minDist + edge.getValue().get();

        if (LOG.isDebugEnabled()) {
          LOG.debug("Vertex " + vertex.getId() + " sent to " + edge.getTargetVertexId() + " = " + distance);
        }
        sendMessage(edge.getTargetVertexId(), new IntWritable(distance));

      }

    }
    vertex.voteToHalt();
  }

}
