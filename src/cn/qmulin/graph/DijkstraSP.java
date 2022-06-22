package cn.qmulin.graph;

import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;
import cn.qmulin.graph.base.EdgeWeightedGraph;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @description: 有向图最短路径算法-Dijstra
 * 能够解决边权重非负的加权有向图
 * @author: xys
 * @date: 2022/6/21 1:05
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<double[]> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.getV()];
        distTo = new double[g.getV()];
        pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[1] - o2[1]);
            }
        });
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.offer(new double[]{s, 0.0});
        while (!pq.isEmpty()) {
            relax(g, pq.poll());
        }
    }

    private void relax(EdgeWeightedDigraph g, double[] poll) {
        int v = (int) poll[0];
        double dist = poll[1];
        if (distTo[v] < dist) return;
        for (DirectedEdge edge : g.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.getWeight()) {
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                pq.offer(new double[]{w, distTo[w]});
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

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(new DirectedEdge(4, 5, 0.35));
        G.addEdge(new DirectedEdge(5, 4, 0.35));
        G.addEdge(new DirectedEdge(4, 7, 0.37));
        G.addEdge(new DirectedEdge(5, 7, 0.28));
        G.addEdge(new DirectedEdge(7, 5, 0.28));
        G.addEdge(new DirectedEdge(5, 1, 0.32));
        G.addEdge(new DirectedEdge(0, 4, 0.38));
        G.addEdge(new DirectedEdge(7, 3, 0.39));
        G.addEdge(new DirectedEdge(0, 2, 0.26));
        G.addEdge(new DirectedEdge(1, 3, 0.29));
        G.addEdge(new DirectedEdge(2, 7, 0.34));
        G.addEdge(new DirectedEdge(6, 2, 0.40));
        G.addEdge(new DirectedEdge(3, 6, 0.52));
        G.addEdge(new DirectedEdge(6, 0, 0.58));
        G.addEdge(new DirectedEdge(6, 4, 0.93));

        DijkstraSP dijkstraSP = new DijkstraSP(G,0);
        for (double dist : dijkstraSP.distTo) {
            System.out.println(dist);
        }
    }
}
