package com.yg.struct.graph;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/21 9:18
 **/
public class GraphMain {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        // 增加节点
        graph.addVertex(a)
                .addVertex(b)
                .addVertex(c)
                .addVertex(d)
                .addVertex(e);
        // 增加边
        graph.addSide(a, b)
                .addSide(a, c)
                .addSide(b, c)
                .addSide(b, d)
                .addSide(b, e);

        System.out.println(Arrays.toString(graph.vertexArr));
        for (int[] var1 : graph.adjacencyMatrix) {
            System.out.println(Arrays.toString(var1));
        }

        graph.dfsPrint();
        System.out.println("=====");
        graph.bfsPrint();
    }
}
