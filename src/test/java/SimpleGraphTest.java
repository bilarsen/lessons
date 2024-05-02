import data_structures.SimpleGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGraphTest {

    private SimpleGraph graph;

    @BeforeEach
    public void setup() {
        graph = new SimpleGraph(5);
    }

    @Test
    @DisplayName("checking for edge")
    void testIsEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        assertFalse(graph.isEdge(0, 1));
    }

    @Test
    @DisplayName("adding vertex")
    void testAddVertex() {
        graph.addVertex(1);
        assertNotNull(graph.getVertex()[0]);
        assertEquals(1, graph.getVertex()[0].getValue());
        assertNull(graph.getVertex()[1]);
        for (int i = 0; i < graph.getMaxVertex(); i++) {
            for (int j = 0; j < graph.getMaxVertex(); j++)
                assertEquals(0, graph.getMatrixAdjacency()[i][j]);
        }
    }

    @Test
    @DisplayName("adding an edge")
    void testAddEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        assertEquals(0, graph.getMatrixAdjacency()[0][1]);
        assertEquals(0, graph.getMatrixAdjacency()[1][0]);

        graph.addEdge(-1, 100);

        graph.addEdge(0, 1);
        assertEquals(1, graph.getMatrixAdjacency()[0][1]);
        assertEquals(1, graph.getMatrixAdjacency()[1][0]);
        assertTrue(graph.isEdge(0, 1));
    }

    @Test
    @DisplayName("removing an edge")
    void testRemoveEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(0, 1);
        assertEquals(1, graph.getMatrixAdjacency()[0][1]);
        assertEquals(1, graph.getMatrixAdjacency()[1][0]);

        graph.removeEdge(-1, 100);

        graph.removeEdge(0, 1);
        assertEquals(0, graph.getMatrixAdjacency()[0][1]);
        assertEquals(0, graph.getMatrixAdjacency()[1][0]);
    }

    @Test
    @DisplayName("removing a vertex")
    void testRemoveVertex() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        assertEquals(1, graph.getMatrixAdjacency()[0][1]);
        assertEquals(1, graph.getMatrixAdjacency()[0][2]);
        assertEquals(1, graph.getMatrixAdjacency()[0][3]);
        assertEquals(1, graph.getMatrixAdjacency()[1][0]);
        assertEquals(1, graph.getMatrixAdjacency()[2][0]);
        assertEquals(1, graph.getMatrixAdjacency()[3][0]);

        graph.removeVertex(0);
        assertNull(graph.getVertex()[0]);
        assertEquals(0, graph.getMatrixAdjacency()[0][1]);
        assertEquals(0, graph.getMatrixAdjacency()[0][2]);
        assertEquals(0, graph.getMatrixAdjacency()[0][3]);
        assertEquals(0, graph.getMatrixAdjacency()[1][0]);
        assertEquals(0, graph.getMatrixAdjacency()[2][0]);
        assertEquals(0, graph.getMatrixAdjacency()[3][0]);
    }

    @Test
    @DisplayName("checking depth first search")
    void testDepthFirstSearch() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<SimpleGraph.Vertex> expected = new ArrayList<>();
        expected.add(graph.getVertex()[0]);
        expected.add(graph.getVertex()[1]);
        expected.add(graph.getVertex()[4]);
        List<SimpleGraph.Vertex> actual = graph.depthFirstSearch(0, 4);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        actual = graph.depthFirstSearch(4, 0);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.removeEdge(0, 1);
        expected = new ArrayList<>();
        expected.add(graph.getVertex()[0]);
        expected.add(graph.getVertex()[2]);
        expected.add(graph.getVertex()[3]);
        expected.add(graph.getVertex()[4]);
        actual = graph.depthFirstSearch(0, 4);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.removeEdge(3, 4);
        expected = new ArrayList<>();
        expected.add(graph.getVertex()[0]);
        expected.add(graph.getVertex()[2]);
        expected.add(graph.getVertex()[3]);
        expected.add(graph.getVertex()[1]);
        expected.add(graph.getVertex()[4]);
        actual = graph.depthFirstSearch(0, 4);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.removeEdge(1, 4);
        actual = graph.depthFirstSearch(0, 4);
        assertEquals(0, actual.size());
    }

    @Test
    @DisplayName("checking breadth first search")
    void testBreadthFirstSearch() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<SimpleGraph.Vertex> expected = new ArrayList<>();
        expected.add(graph.getVertex()[0]);
        expected.add(graph.getVertex()[1]);
        expected.add(graph.getVertex()[4]);
        List<SimpleGraph.Vertex> actual = graph.breadthFirstSearch(0, 4);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        actual = graph.breadthFirstSearch(4, 0);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));


        graph = new SimpleGraph(9);
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(7, 8);

        expected = new ArrayList<>();
        expected.add(graph.getVertex()[0]);
        expected.add(graph.getVertex()[4]);
        expected.add(graph.getVertex()[5]);
        expected.add(graph.getVertex()[7]);
        expected.add(graph.getVertex()[8]);
        actual = graph.breadthFirstSearch(0, 8);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        graph.removeEdge(7, 8);
        actual = graph.breadthFirstSearch(0, 8);
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("getting weak vertices")
    void testGetWeakVertices() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        assertTrue(graph.getWeakVertices().isEmpty());

        graph.removeEdge(3, 4);
        List<SimpleGraph.Vertex> actual = graph.getWeakVertices();
        assertFalse(actual.isEmpty());
        assertEquals(5, actual.getFirst().getValue());
    }

    private int[] getVertexValues(List<SimpleGraph.Vertex> vertices) {
        int[] values = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++)
            values[i] = vertices.get(i).getValue();
        return values;
    }
}
