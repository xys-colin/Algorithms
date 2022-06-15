package cn.qmulin.graph.base;

/**
 * @description: 带权重的边的数据类型
 * @author: xys
 * @date: 2022/6/15 14:22
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 边的权重
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 边两端的顶点之一
     *
     * @return
     */
    public int either() {
        return v;
    }

    /**
     * 另一个顶点
     *
     * @param vertex
     * @return
     */
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    /**
     * 将这条边与另一条边权重比较
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight) return -1;
        else if (this.weight > o.weight) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
