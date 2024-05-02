package data_structures;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class SimpleGraph {

    private final Vertex[] vertex;

    private final int[][] matrixAdjacency;

    private final int maxVertex;

    public SimpleGraph(int size) {
        maxVertex = size;
        matrixAdjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void addVertex(int value) {
        int index = findAvailableIndex();
        if (index >= 0) {
            vertex[index] = new Vertex(value);
        }
    }

    public void removeVertex(int vertexIndex) {
        if (checkIndex(vertexIndex)) {
            vertex[vertexIndex] = null;
            for (int i = 0; i < maxVertex; i++)
                removeEdge(vertexIndex, i);
        }
    }

    public boolean isEdge(int vertexIndex1, int vertexIndex2) {
        if (checkIndex(vertexIndex1) && checkIndex(vertexIndex2))
            return matrixAdjacency[vertexIndex1][vertexIndex2] == 1 && matrixAdjacency[vertexIndex2][vertexIndex1] == 1;
        return false;
    }

    public void addEdge(int vertexIndex1, int vertexIndex2) {
        if (checkIndex(vertexIndex1) && checkIndex(vertexIndex2)) {
            matrixAdjacency[vertexIndex1][vertexIndex2] = 1;
            matrixAdjacency[vertexIndex2][vertexIndex1] = 1;
        }
    }

    public void removeEdge(int vertexIndex1, int vertexIndex2) {
        if (checkIndex(vertexIndex1) && checkIndex(vertexIndex2)) {
            matrixAdjacency[vertexIndex1][vertexIndex2] = 0;
            matrixAdjacency[vertexIndex2][vertexIndex1] = 0;
        }
    }

    public List<Vertex> depthFirstSearch(int vertexIndexFrom, int vertexIndexTo) {
        List<Vertex> vertices = new ArrayList<>();
        if (checkIndex(vertexIndexFrom) && checkIndex(vertexIndexTo)) {
            for (int i = 0; i < maxVertex; i++) vertex[i].hit = false;
            ArrayDeque<Integer> verticesHit = new ArrayDeque<>();
            vertex[vertexIndexFrom].hit = true;
            verticesHit.push(vertexIndexFrom);
            int currentVertex = vertexIndexFrom;
            DFS:
            while (!verticesHit.isEmpty()) {
                if (matrixAdjacency[currentVertex][vertexIndexTo] == 1) {
                    verticesHit.push(vertexIndexTo);
                    break;
                }
                for (int i = 0; i < maxVertex; i++) {
                    if (matrixAdjacency[currentVertex][i] == 1 && !vertex[i].hit) {
                        vertex[i].hit = true;
                        verticesHit.push(i);
                        currentVertex = i;
                        continue DFS;
                    }
                }
                verticesHit.pop();
                if (verticesHit.isEmpty()) return vertices;
                currentVertex = verticesHit.peek();
            }
            while (!verticesHit.isEmpty()) {
                vertices.add(vertex[verticesHit.pollLast()]);
            }
        }
        return vertices;
    }

    public List<Vertex> breadthFirstSearch(int vertexIndexFrom, int vertexIndexTo) {
        List<Vertex> vertices = new ArrayList<>();
        if (!checkIndex(vertexIndexFrom) || !checkIndex(vertexIndexTo)) return vertices;
        if (vertexIndexFrom == vertexIndexTo) {
            vertices.add(vertex[vertexIndexFrom]);
            return vertices;
        }
        for (int i = 0; i < maxVertex; i++) vertex[i].hit = false;
        int[] prevVertices = new int[maxVertex];
        Arrays.fill(prevVertices, -1);
        ArrayDeque<Integer> verticesHit = new ArrayDeque<>();
        vertex[vertexIndexFrom].hit = true;
        verticesHit.offer(vertexIndexFrom);
        BFS:
        while (!verticesHit.isEmpty()) {
            int currentVertex = verticesHit.poll();
            for (int i = 0; i < maxVertex; i++) {
                if (matrixAdjacency[currentVertex][i] == 1 && !vertex[i].hit) {
                    verticesHit.offer(i);
                    vertex[i].hit = true;
                    prevVertices[i] = currentVertex;
                    if (i == vertexIndexTo) break BFS;
                }
            }
        }
        return tracePath(prevVertices, vertices, vertexIndexTo);
    }

    public List<Vertex> getWeakVertices() {
        List<Vertex> weakVertices = new ArrayList<>();
        for (int i = 0; i < maxVertex; i++)
            if (!findTriangle(i)) weakVertices.add(vertex[i]);
        return weakVertices;
    }

    private int findAvailableIndex() {
        for (int i = 0; i < maxVertex; i++)
            if (vertex[i] == null) return i;
        return -1;
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < maxVertex;
    }

    private boolean findTriangle(int vertexIndex) {
        for (int i = 0; i < maxVertex; i++) {
            if (matrixAdjacency[vertexIndex][i] == 1 && vertexIndex != i) {
                for (int j = 0; j < maxVertex; j++) {
                    if (j == vertexIndex) continue;
                    if (matrixAdjacency[i][j] == 1 && matrixAdjacency[vertexIndex][j] == 1) return true;
                }
            }
        }
        return false;
    }

    private List<Vertex> tracePath(int[] prevVertices, List<Vertex> vertices, int vertexTo) {
        if (prevVertices[vertexTo] == -1) return vertices;
        int currentVertex = vertexTo;
        while (currentVertex != -1) {
            vertices.add(vertex[currentVertex]);
            currentVertex = prevVertices[currentVertex];
        }
        Collections.reverse(vertices);
        return vertices;
    }

    @Getter
    public static class Vertex {

        private final int value;

        private boolean hit;

        public Vertex(int value) {
            this.value = value;
            hit = false;
        }
    }

}
