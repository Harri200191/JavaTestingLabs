package graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * @param <L> type of vertex labels in the graph, must be immutable
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    // Abstraction function:
    //  The graph consists of a set of vertices and a list of edges where each edge connects a source to a target with a weight.

    // Representation invariant:
    //  - vertices cannot contain duplicates.
    //  - edges list contains only valid edges (non-null source, target, and weight).

    // Constructor for ConcreteEdgesGraph
    public ConcreteEdgesGraph() {
    }

    // Check Rep invariant
    private void checkRep() {
        assert vertices != null : "vertices should not be null";
        assert edges != null : "edges should not be null";
        for (Edge<L> e : edges) {
            assert e != null : "edge should not be null";
            assert vertices.contains(e.getSource()) : "source vertex should exist in graph";
            assert vertices.contains(e.getTarget()) : "target vertex should exist in graph";
        }
    }

    @Override
    public boolean add(L vertex) {
        checkRep();
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override
    public int set(L source, L target, int weight) {
        checkRep();

        // If weight is zero, remove the edge
        if (weight == 0) {
            return removeEdge(source, target);
        }

        // Look for an existing edge
        for (Edge<L> e : edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                // Update the weight if edge exists and return previous weight
                int prevWeight = e.getWeight();
                edges.remove(e);
                edges.add(new Edge<>(source, target, weight));  // Replace with new edge with updated weight
                checkRep();
                return prevWeight;
            }
        }

        // If no edge found, add a new one
        Edge<L> newEdge = new Edge<>(source, target, weight);
        edges.add(newEdge);
        vertices.add(source);
        vertices.add(target);

        checkRep();
        return 0; // No previous edge weight, return 0
    }

    // Helper method to remove an edge
    private int removeEdge(L source, L target) {
        Iterator<Edge<L>> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge<L> e = iterator.next();
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                int weight = e.getWeight();
                iterator.remove();
                return weight;
            }
        }
        return 0;
    }

    @Override
    public boolean remove(L vertex) {
        checkRep();
        if (!vertices.contains(vertex)) return false;
        vertices.remove(vertex);
        edges.removeIf(e -> e.getSource().equals(vertex) || e.getTarget().equals(vertex));
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        checkRep();
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        checkRep();
        Map<L, Integer> result = new HashMap<>();
        for (Edge<L> e : edges) {
            if (e.getTarget().equals(target)) {
                result.put(e.getSource(), e.getWeight());
            }
        }
        return result;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        checkRep();
        Map<L, Integer> result = new HashMap<>();
        for (Edge<L> e : edges) {
            if (e.getSource().equals(source)) {
                result.put(e.getTarget(), e.getWeight());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\nEdges:\n");
        for (Edge<L> e : edges) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}

/**
 * Internal class to represent an edge in the graph.
 *
 * @param <L> type of vertex labels in this graph
 */
class Edge<L> {
    private final L source;
    private final L target;
    private final int weight;

    // Constructor to initialize Edge
    public Edge(L source, L target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge data");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public L getSource() {
        return source;
    }

    public L getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + target + " (" + weight + ")";
    }
}
