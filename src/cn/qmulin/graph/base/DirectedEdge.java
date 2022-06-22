package cn.qmulin.graph.base;

/**
 * @description: 加权有向边
 * @author: xys
 * @date: 2022/6/20 21:54
 */
public class DirectedEdge {
    //边的起点
    private final int v;
    //边的终点
    private final int w;
    //边的权重
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
