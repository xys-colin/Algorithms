package cn.qmulin.graph;

import cn.qmulin.base.IndexMinPQ;
import cn.qmulin.base.Queue;
import cn.qmulin.graph.base.Edge;
import cn.qmulin.graph.base.EdgeWeightedGraph;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @description: 最小生成树的Prim算法(即时版本)
 * 与延时版本不同是从优先队列中删除了失效的边,只含有树顶点和非树顶点之间的横切边
 * @author: xys
 * @date: 2022/6/15 17:42
 */
public class PrimMST {
    //距离树最近的边
    private Edge[] edgeTo;
    //distTo[w]=edgeTo[w].weight();
    private double[] distTo;
    //如果v在树中则为true;
    private boolean[] marked;
    //有效地横切边
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.getV()];
        distTo = new double[graph.getV()];
        marked = new boolean[graph.getV()];
        for (int i = 1; i < graph.getV(); i++) {
            //初始化顶点到每个顶点距离为无穷大(除自身)
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.getV());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) continue;
            if (edge.getWeight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.getWeight();
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (Edge e : edgeTo) {
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.getWeight();
        return weight;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(8);
        G.addEdge(new Edge(4,5,0.35));
        G.addEdge(new Edge(4,7,0.37));
        G.addEdge(new Edge(5,7,0.28));
        G.addEdge(new Edge(0,7,0.16));
        G.addEdge(new Edge(1,5,0.32));
        G.addEdge(new Edge(0,4,0.38));
        G.addEdge(new Edge(2,3,0.17));
        G.addEdge(new Edge(1,7,0.19));
        G.addEdge(new Edge(0,2,0.26));
        G.addEdge(new Edge(1,2,0.36));
        G.addEdge(new Edge(1,3,0.29));
        G.addEdge(new Edge(2,7,0.34));
        G.addEdge(new Edge(6,2,0.40));
        G.addEdge(new Edge(3,6,0.52));
        G.addEdge(new Edge(6,0,0.58));
        G.addEdge(new Edge(4,6,0.93));

        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
