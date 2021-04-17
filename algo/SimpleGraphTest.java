import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
