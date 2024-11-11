/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
import java.util.*;

public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

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
        for (Edge e : edges) {
            assert e != null : "edge should not be null";
            assert vertices.contains(e.getSource()) : "source vertex should exist in graph";
            assert vertices.contains(e.getTarget()) : "target vertex should exist in graph";
        }
    }

    @Override
    public boolean add(String vertex) {
        checkRep();
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        
        // If weight is zero, remove the edge
        if (weight == 0) {
            return removeEdge(source, target);
        }
        
        // Look for an existing edge
        for (Edge e : edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                // Update the weight if edge exists and return previous weight
                int prevWeight = e.getWeight();
                edges.remove(e);
                edges.add(new Edge(source, target, weight));  // Replace with new edge with updated weight
                checkRep();
                return prevWeight;
            }
        }

        // If no edge found, add a new one
        Edge newEdge = new Edge(source, target, weight);
        edges.add(newEdge);
        vertices.add(source);
        vertices.add(target);
        
        checkRep();
        return 0; // No previous edge weight, return 0
    }


    // Helper method to remove an edge
    private int removeEdge(String source, String target) {
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                int weight = e.getWeight();
                iterator.remove();
                return weight;
            }
        }
        return 0;
    }

    @Override
    public boolean remove(String vertex) {
        checkRep();
        if (!vertices.contains(vertex)) return false;
        vertices.remove(vertex);
        edges.removeIf(e -> e.getSource().equals(vertex) || e.getTarget().equals(vertex));
        checkRep();
        return true;
    }

    @Override
    public Set<String> vertices() {
        checkRep();
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        Map<String, Integer> result = new HashMap<>();
        for (Edge e : edges) {
            if (e.getTarget().equals(target)) {
                result.put(e.getSource(), e.getWeight());
            }
        }
        return result;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        Map<String, Integer> result = new HashMap<>();
        for (Edge e : edges) {
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
        for (Edge e : edges) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}


/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    private final String source;
    private final String target;
    private final int weight;

    // Constructor to initialize Edge
    public Edge(String source, String target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge data");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
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

