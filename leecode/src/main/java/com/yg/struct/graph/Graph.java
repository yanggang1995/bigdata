package com.yg.struct.graph;

import com.yg.struct.queue.LinkedQueue;
import com.yg.struct.stack.LinkedStack;

/**
 * 无向图
 *
 * @author Y.G
 * @date 2020/4/21 8:58
 **/
public class Graph {
    private int size;
    private int arrIndex;
    public Vertex[] vertexArr;
    public int[][] adjacencyMatrix;

    public Graph(int size) {
        this.size = size;
        this.arrIndex = 0;
        this.vertexArr = new Vertex[size];
        this.adjacencyMatrix = new int[size][size];
    }

    /**
     * 添加顶点
     *
     * @param vertex 顶点
     * @return 当前图
     */
    public Graph addVertex(Vertex vertex) {
        if (arrIndex == size) {
            throw new RuntimeException("图结构顶点已满");
        }
        vertexArr[arrIndex] = vertex;
        vertex.vIndex = arrIndex;
        arrIndex++;
        return this;
    }

    /**
     * 添加边
     *
     * @param a A顶点
     * @param b B顶点
     * @return 当前图
     */
    public Graph addSide(Vertex a, Vertex b) {
        int indexA, indexB;
        if ((indexA = a.vIndex) >= 0 && (indexB = b.vIndex) >= 0) {
            adjacencyMatrix[indexA][indexB] = 1;
            adjacencyMatrix[indexB][indexA] = 1;
            return this;
        }
        throw new RuntimeException("顶点不存在");
    }

    /**
     * Depth First Search
     * 深度优先搜索算法，遍历无向图
     * 1、遍历当前节点的第一个邻接节点
     * 2、相同方式遍历邻接节点
     * 3、直到不存在邻接节点时，回溯到上一个节点，continue 1
     */
    public void dfsPrint() {
        LinkedStack<Vertex> vertexStack = new LinkedStack<>();
        Vertex vertex = vertexArr[0];
        dfs:
        while (true) {
            if (vertex.visitIndex == -1) {
                vertex.visitIndex = vertex.vIndex + 1;
            }
            System.out.println(vertex.toString());
            for (; vertex.visitIndex < vertexArr.length; vertex.visitIndex++) {
                Vertex nextVertex = vertexArr[vertex.visitIndex];
                if (adjacencyMatrix[vertex.vIndex][vertex.visitIndex] == 1 && nextVertex.visitIndex == -1) {
                    vertexStack.push(vertex);
                    vertex = nextVertex;
                    continue dfs;
                }
            }
            if ((vertex = vertexStack.pop()) == null) {
                break;
            }
        }
        clearVisit();
    }

    /**
     * Breadth First Search
     * 广度优先算法 (宽度优先算法)
     * 1、先遍历当前节点的邻接节点
     * 2、以相同方式遍历邻接节点
     * 3、直到所有节点都遍历
     */
    public void bfsPrint() {
        LinkedQueue<Vertex> linkedQueue = new LinkedQueue<>();
        Vertex vertex = vertexArr[0];
        do {
            if (vertex.visitIndex == -1) {
                vertex.visitIndex = vertex.vIndex + 1;
            }
            System.out.println(vertex);
            for (; vertex.visitIndex < vertexArr.length; vertex.visitIndex++) {
                if (adjacencyMatrix[vertex.vIndex][vertex.visitIndex] == 1) {
                    linkedQueue.offer(vertexArr[vertex.visitIndex]);
                }
            }
        } while ((vertex = linkedQueue.take()) != null);
        clearVisit();
    }

    /**
     * 清空访问计数
     */
    private void clearVisit() {
        for (Vertex vertex : vertexArr) {
            vertex.visitIndex = -1;
        }
    }
}
