package algo;
import java.util.Map;

/**
 * The Graph interface represents a directed graph
 * with unweighted edges.  It describes common operations
 * to be provided by any Graph implementation.  All implementations
 * should allow multiple edges between two nodes.
 * @author Jason B. Shepherd
 */
public interface Graph {
	/**
	 * Adds <code>node</code> to this graph.
	 * <code>node</code> needs to be a unique name.
	 * @param node the label for the node
	 * @throws IllegalArgumentException if <code>node</code>
	 *        is not unique
	 */
	public void addNode(String node);
	
	
	
	
	/**
	 * Adds a directed edge between <code>a</code>
	 * and <code>b</code>.  If either <code>a</code> or
	 * <code>b</code> do not already exist, this
	 * <code>Graph</code> will perform <code>addNode(a)</code>
	 * or <code>addNode(b)</code> accordingly.
	 * @param a the label for the edge's source node
	 * @param b the label for the edge's destination node
	 */
	public void addEdge(String a, String b);
	
	/**
	 * @param node the node's label
	 * @return whether <code>node</code> is part of the graph.
	 */
	public Boolean containsNode(String node);
	
	/**
	 * @param a the label for the source node
	 * @param b the label for the destination node
	 * @return whether the given directed edge exists
	 */
	public Boolean containsEdge(String a, String b);
	
	/**
	 * @return the number of nodes currently in the graph
	 */
	public Integer numberOfNodes();
	
	/**
	 * @return the number of edges currently in the graph
	 */
	public Integer numberOfEdges();
	
	/**
	 * Performs a breadth-first search on the graph.
	 * @param startNode the label for the start node for this search
	 * @return a <code>Map</code> of node labels mapped to
	 *    a <code>GraphTuple</code>, which contains the distance
	 *    from the source (the "d" value) and the adjacent parent
	 *    node through which the shortest path exists (the "p"
	 *    value)
	 */
	public Map<String, GraphTuple> bfs(String startNode);
		
}
