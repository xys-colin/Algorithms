package cn.qmulin.graph.directed;

import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;

import java.util.Stack;

/**
 * @description: 无环加权有向图的最短路径算法(在此条件下比Dijkstra算法快)
 * O(E+V)
 * 特点:
 * 能够在线性时间内解决单点最短路径问题
 * 能够处理负权重的边
 * 能够解决相关的问题,例如找出最长路径
 * 步骤:
 * 首先将distTo[s]初始化为0,其他dist[]To初始化为无穷大,然后一个一个地按照拓扑排序顺序放松所有顶点.
 * 原理:
 * 每一条v->w边,都只会放松一次,因为distTo[v]是不会变化地(因为按照拓扑排序顺序放松顶点,在v放松之后算法不会在处理任何指向v的边)
 * 而distTo[w]只会变小.因此,在所有从s可达的顶点都被加入到树中,最短路径的最优条件成立
 * @author: xys
 * @date: 2022/7/4 23:08
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.getV()];
        distTo = new double[g.getV()];
        for (int v = 1; v < g.getV(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        Topological topological = new Topological(g);
        for (Integer v : topological.getOrder()) {
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, Integer v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
