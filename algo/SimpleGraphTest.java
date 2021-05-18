import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleGraphTest {

    private  SimpleGraph graph;

    @Before
    public void setup() {
        graph = new SimpleGraph(5);
    }

    @Test
    public void testIsEdge() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        Assert.assertFalse(graph.IsEdge(0, 1));
    }

    @Test
    public void testAddVertex() {
        graph.AddVertex(1);
        Assert.assertNotNull(graph.vertex[0]);
        Assert.assertEquals(1, graph.vertex[0].Value);
        Assert.assertNull(graph.vertex[1]);
        for (int i = 0; i < graph.max_vertex; i++) {
            for (int j = 0; j < graph.max_vertex; j++) {
                Assert.assertEquals(0, graph.m_adjacency[i][j]);
            }
        }
    }

    @Test
    public void testAddEdge() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        Assert.assertEquals(0, graph.m_adjacency[0][1]);
        Assert.assertEquals(0, graph.m_adjacency[1][0]);

        graph.AddEdge(-1, 100);

        graph.AddEdge(0, 1);
        Assert.assertEquals(1, graph.m_adjacency[0][1]);
        Assert.assertEquals(1, graph.m_adjacency[1][0]);
        Assert.assertTrue(graph.IsEdge(0, 1));
    }

    @Test
    public void testRemoveEdge() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(0, 1);
        Assert.assertEquals(1, graph.m_adjacency[0][1]);
        Assert.assertEquals(1, graph.m_adjacency[1][0]);

        graph.RemoveEdge(-1, 100);

        graph.RemoveEdge(0, 1);
        Assert.assertEquals(0, graph.m_adjacency[0][1]);
        Assert.assertEquals(0, graph.m_adjacency[1][0]);
    }

    @Test
    public void testRemoveVertex() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);

        Assert.assertEquals(1, graph.m_adjacency[0][1]);
        Assert.assertEquals(1, graph.m_adjacency[0][2]);
        Assert.assertEquals(1, graph.m_adjacency[0][3]);
        Assert.assertEquals(1, graph.m_adjacency[1][0]);
        Assert.assertEquals(1, graph.m_adjacency[2][0]);
        Assert.assertEquals(1, graph.m_adjacency[3][0]);

        graph.RemoveVertex(0);
        Assert.assertNull(graph.vertex[0]);
        Assert.assertEquals(0, graph.m_adjacency[0][1]);
        Assert.assertEquals(0, graph.m_adjacency[0][2]);
        Assert.assertEquals(0, graph.m_adjacency[0][3]);
        Assert.assertEquals(0, graph.m_adjacency[1][0]);
        Assert.assertEquals(0, graph.m_adjacency[2][0]);
        Assert.assertEquals(0, graph.m_adjacency[3][0]);
    }

    @Test
    public void testDepthFirstSearch() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);

        List<Vertex> expected = new ArrayList<>();
        expected.add(graph.vertex[0]);
        expected.add(graph.vertex[1]);
        expected.add(graph.vertex[4]);
        List<Vertex> actual = graph.DepthFirstSearch(0, 4);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        actual = graph.DepthFirstSearch(4, 0);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.RemoveEdge(0, 1);
        expected = new ArrayList<>();
        expected.add(graph.vertex[0]);
        expected.add(graph.vertex[2]);
        expected.add(graph.vertex[3]);
        expected.add(graph.vertex[4]);
        actual = graph.DepthFirstSearch(0, 4);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.RemoveEdge(3, 4);
        expected = new ArrayList<>();
        expected.add(graph.vertex[0]);
        expected.add(graph.vertex[2]);
        expected.add(graph.vertex[3]);
        expected.add(graph.vertex[1]);
        expected.add(graph.vertex[4]);
        actual = graph.DepthFirstSearch(0, 4);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.RemoveEdge(1, 4);
        actual = graph.DepthFirstSearch(0, 4);
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void testBreadthFirstSearch() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);

        List<Vertex> expected = new ArrayList<>();
        expected.add(graph.vertex[0]);
        expected.add(graph.vertex[1]);
        expected.add(graph.vertex[4]);
        List<Vertex> actual = graph.BreadthFirstSearch(0, 4);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        actual = graph.BreadthFirstSearch(4, 0);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));


        graph = new SimpleGraph(9);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 4);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 6);
        graph.AddEdge(5, 7);
        graph.AddEdge(7, 8);

        expected = new ArrayList<>();
        expected.add(graph.vertex[0]);
        expected.add(graph.vertex[4]);
        expected.add(graph.vertex[5]);
        expected.add(graph.vertex[7]);
        expected.add(graph.vertex[8]);
        actual = graph.BreadthFirstSearch(0, 8);
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.RemoveEdge(7, 8);
        actual = graph.BreadthFirstSearch(0, 8);
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void testWeakVertices() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);

        Assert.assertTrue(graph.WeakVertices().isEmpty());

        graph.RemoveEdge(3, 4);
        List<Vertex> actual = graph.WeakVertices();
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(5, actual.get(0).Value);
    }

    // helper method to print out to console
    private int[] getVertexValues(List<Vertex> vertices) {
        int[] values = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            values[i] = vertices.get(i).Value;
        }
        return values;
    }
}
