package graph;

import java.util.Map;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;

public abstract class GraphInstanceTest {
    
    // Overridden by implementation-specific test classes.
    public abstract Graph<String> emptyInstance();

    // Test for initial empty graph (no vertices)
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    // Test for adding a vertex
    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("vertex should be added successfully", graph.add("A"));
        assertEquals("graph should have 1 vertex", Set.of("A"), graph.vertices());
    }

    // Test for adding a duplicate vertex
    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("adding duplicate vertex should return false", graph.add("A"));
    }

    // Test for removing a vertex
    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue("vertex should be removed successfully", graph.remove("A"));
        assertEquals("graph should have no vertices", Collections.emptySet(), graph.vertices());
    }

    // Test for removing a non-existent vertex
    @Test
    public void testRemoveNonExistentVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse("removing non-existent vertex should return false", graph.remove("A"));
    }

    // Test for adding an edge between two vertices
    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        int previousWeight = graph.set("A", "B", 10);
        assertEquals("previous weight should be 0", 0, previousWeight);
        assertEquals("B should be a target of A", 
                     Map.of("B", 10), graph.targets("A"));
    }

    // Test for updating an existing edge
    @Test
    public void testUpdateEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10);
        int previousWeight = graph.set("A", "B", 20);
        assertEquals("previous weight should be 10", 10, previousWeight);
        assertEquals("B should be a target of A with updated weight", 
                     Map.of("B", 20), graph.targets("A"));
    }

    // Test for removing an edge by setting weight to 0
    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10);
        int previousWeight = graph.set("A", "B", 0);
        assertEquals("previous weight should be 10", 10, previousWeight);
        assertTrue("edge should be removed", graph.targets("A").isEmpty());
    }

    // Test for fetching all vertices
    @Test
    public void testVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        Set<String> expected = Set.of("A", "B");
        assertEquals("vertices should match the expected set", expected, graph.vertices());
    }

    // Test for fetching source vertices for a given target
    @Test
    public void testSources() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10);
        Map<String, Integer> sources = graph.sources("B");
        assertEquals("A should be a source of B with weight 10", 
                     Map.of("A", 10), sources);
    }

    // Test for fetching target vertices for a given source
    @Test
    public void testTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10);
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("B should be a target of A with weight 10", 
                     Map.of("B", 10), targets);
    }
}
