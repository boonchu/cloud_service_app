import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

/**
 * Implementation of the connected component algorithm that identifies
 * connected components and assigns each vertex its "component
 * identifier" (the smallest vertex id in the component).
 */
public class ConnectedComponentsComputation extends
    BasicComputation<IntWritable, IntWritable, NullWritable, IntWritable> {
  /**
   * Propagates the smallest vertex id to all neighbors. Will always choose to
   * halt and only reactivate if a smaller id has been sent to it.
   *
   * @param vertex Vertex
   * @param messages Iterator of messages from the previous superstep.
   * @throws IOException
   */
  @Override
  public void compute(
	Vertex<IntWritable, IntWritable, NullWritable> vertex,
	Iterable<IntWritable> messages) throws IOException {

	int active_vertex = vertex.getValue().get();
	if (getSuperstep() == 0) {
		for (Edge < IntWritable, NullWritable > edge : vertex.getEdges()) {
			int next = edge.getTargetVertexId().get();
			if (next < active_vertex) {
				active_vertex = next;
			}
		}
	
		if (active_vertex != vertex.getValue().get()) {
			vertex.setValue(new IntWritable(active_vertex));

			for (Edge < IntWritable, NullWritable > edge : vertex.getEdges()) {
				IntWritable next = edge.getTargetVertexId();
				if (next.get() > active_vertex) {
					sendMessage(next, vertex.getValue());
				}
			}
			
		}
		
		vertex.voteToHalt();
		return;
	}

	boolean updated = false;
		
	for (IntWritable m : messages) {
		int candidate = m.get();
		if (candidate < active_vertex) {
			active_vertex = candidate;
			updated = true;
		}
	}
	
	if (updated) {
		vertex.setValue(new IntWritable(active_vertex));
		sendMessageToAllEdges(vertex, vertex.getValue());
	}

	vertex.voteToHalt();
  }

}
