package algo;
/**
 * A GraphTuple contains search information for a particular
 * Graph node.  This information is the distance (<code>d</code>)
 * a node is from a start node in the search (default is
 * <code>Integer.MAX_VALUE</code>) and the parent node (default is
 * <code>null</code>) through which shortest path from the start node
 * is attainable.
 * @author Jason B. Shepherd
 */
public class GraphTuple {
	public Integer d = Integer.MAX_VALUE;
	public String p = null;
	
	public GraphTuple() {
	}
	
	public GraphTuple(Integer d, String p) {
		this.d = d;
		this.p = p;
	}
	
	public String toString() {
		return "<GraphTuple(d=" + this.d + ",p=" + this.p + ")>";
	}
}