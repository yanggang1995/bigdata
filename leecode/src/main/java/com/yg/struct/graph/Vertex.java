package com.yg.struct.graph;

/**
 * 图的顶点
 *
 * @author Y.G
 * @date 2020/4/21 8:59
 **/
public class Vertex {
    public int vIndex = -1;
    private String data;
    public int visitIndex = -1;

    public Vertex(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }
}
