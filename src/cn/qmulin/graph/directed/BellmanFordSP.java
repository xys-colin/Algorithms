package cn.qmulin.graph.directed;

import cn.qmulin.base.Queue;
import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;

/**
 * @description: 基于队列的Bellman-Ford最短路径算法(或者找到从s可达的负权重环)所需时间与EV成正比,空间和V成正比
 * @author: xys
 * @date: 2022/7/6 14:51
 */
public class BellmanFordSP {
    //从起点到某个顶点的路径长度
    private double[] distTo;
    //从起点到某个顶点的最后一条边
    private DirectedEdge[] edgeTo;
    //该顶点是否存在队列中
    private boolean[] onQ;
    //正在被放松的顶点
    private Queue<Integer> queue;
    //relax()的调用次数
    private int cost;
    //edgeTo[]中的是否有负权重环
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph g, int s) {
        distTo = new double[g.getV()];
        edgeTo = new DirectedEdge[g.getV()];
        onQ = new boolean[g.getV()];
        queue = new Queue<>();
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }
        if (cost++ % g.getV() == 0) {
            findNegativeCycle();
        }
    }

    private void findNegativeCycle() {
        int v = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(v);
        for (DirectedEdge edge : edgeTo) {
            if (edge != null) {
                spt.addEdge(edge);
            }
            EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
            cycle = finder.cycle();
        }
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

}
