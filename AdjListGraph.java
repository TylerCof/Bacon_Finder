package algo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjListGraph implements Graph {

	private Map<String, List<String>> g =
			new HashMap<String, List<String>>(); 
	private int numEdges = 0;
	
	/**
	 * Adds <code>node</code> to this graph.
	 * <code>node</code> needs to be a unique name.
	 * @param node the label for the node 
	 * @throws IllegalArgumentException if <code>node</code>
	 *        is not unique
	 */
	@Override
	public void addNode(String node) {
		if (g.containsKey(node)) {
			throw new IllegalArgumentException(
					node + " already exists in this graph"
			);
		}
		
		g.put(node, new ArrayList<String>());
	}

	/**
	 * Adds a directed edge between <code>a</code>
	 * and <code>b</code>.  If either <code>a</code> or
	 * <code>b</code> do not already exist, this
	 * <code>Graph</code> will perform <code>addNode(a)</code>
	 * or <code>addNode(b)</code> accordingly.
	 * @param a the label for the edge's source node
	 * @param b the label for the edge's destination node
	 */
	@Override
	public void addEdge(String a, String b) {
		if (!g.containsKey(a))
			addNode(a);
		
		if (!g.containsKey(b))
			addNode(b);
		
		g.get(a).add(b);
		numEdges++;
	}

	/**
	 * @param node the node's label
	 * @return whether <code>node</code> is part of the graph.
	 */
	@Override
	public Boolean containsNode(String node) {
		return g.containsKey(node);
	}

	/**
	 * @param a the label for the source node
	 * @param b the label for the destination node
	 * @return whether the given directed edge exists
	 */
	@Override
	public Boolean containsEdge(String a, String b) {
		if (!containsNode(a)) {
			return false;
		}
		
		return g.get(a).contains(b);
	}

	/**
	 * @return the number of nodes currently in the graph
	 */
	@Override
	public Integer numberOfNodes() {
		return g.keySet().size();
	}

	/**
	 * @return the number of edges currently in the graph
	 */
	@Override
	public Integer numberOfEdges() {
		return numEdges;
	}

	/**
	 * Performs a breadth-first search on the graph.
	 * @param startNode the label for the start node for this search
	 * @return a <code>Map</code> of node labels mapped to
	 *    a <code>GraphTuple</code>, which contains the distance
	 *    from the source (the "d" value) and the adjacent parent
	 *    node through which the shortest path exists (the "p"
	 *    value)
	 */
	@Override
	public Map<String, GraphTuple> bfs(String s) {
		Map<String, Boolean> visited = new HashMap<String, Boolean>();
		Map<String, GraphTuple> m = new HashMap<String, GraphTuple>();
		
		for (String v : g.keySet()) {
			visited.put(v, false);
			GraphTuple tuple = new GraphTuple();
			m.put(v, tuple);
		}
		visited.put(s, true);
		m.get(s).d = 0;
		
		Queue<String> q = new ArrayDeque<String>();
		q.add(s);
		while (!q.isEmpty()) {
			String u = q.remove();
			for (String v : g.get(u)) {
				if (!visited.get(v)) {
					visited.put(v, true);
					m.get(v).d = m.get(u).d + 1;
					m.get(v).p = u;
					q.add(v);
				}
			}
		}
		
		return m;
	}

	public static void main(String[] args) {
		Graph myGraph = new AdjListGraph();
		
		/*
		   _______________
		  /               v
		 a -----> b -----> d -----> e
		  \               ^
           \		     /
		    `---> c ----'
		 
		 */

		myGraph.addNode("a");
		myGraph.addNode("b");
		myGraph.addEdge("a", "b");
		myGraph.addEdge("a", "c");
		myGraph.addEdge("a", "d");
		myGraph.addEdge("b", "d");
		myGraph.addEdge("c", "d");
		myGraph.addEdge("d", "e");
		
		System.out.println(myGraph.numberOfNodes() == 5);
		System.out.println(myGraph.numberOfEdges() == 6);
		System.out.println(myGraph.containsEdge("a", "b") == true);
		System.out.println(myGraph.containsEdge("b", "a") == false);
		
		Map<String, GraphTuple> bfsResults = myGraph.bfs("a");
		System.out.println(bfsResults);
	}
}
