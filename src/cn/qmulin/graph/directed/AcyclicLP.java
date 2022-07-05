package cn.qmulin.graph.directed;

import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;

/**
 * @description: 无环加权有向图的最长路径算法(与AcyclicSP相同)
 *  只是改变distTo[]初始值为最大负数, 并改变relax方法中的不等式的反向
 *  Tips:
 *      "关键路径"问题等价无环加权有向图中的最长路径问题
 * @author: xys
 * @date: 2022/7/5 23:59
 */
public class AcyclicLP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.getV()];
        distTo = new double[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s]=0.0;
        Topological topological = new Topological(g);
        for (Integer v : topological.getOrder()) {
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, Integer v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
            }
        }
    }
    public double distTo(int v) {
        return distTo[v];
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(new DirectedEdge(5, 4, 0.35));
        G.addEdge(new DirectedEdge(4, 7, 0.37));
        G.addEdge(new DirectedEdge(5, 7, 0.28));
        G.addEdge(new DirectedEdge(5, 1, 0.32));
        G.addEdge(new DirectedEdge(4, 0, 0.38));
        G.addEdge(new DirectedEdge(3, 7, 0.39));
        G.addEdge(new DirectedEdge(0, 2, 0.26));
        G.addEdge(new DirectedEdge(1, 3, 0.29));
        G.addEdge(new DirectedEdge(7, 2, 0.34));
        G.addEdge(new DirectedEdge(6, 2, 0.40));
        G.addEdge(new DirectedEdge(3, 6, 0.52));
        G.addEdge(new DirectedEdge(6, 0, 0.58));
        G.addEdge(new DirectedEdge(6, 4, 0.93));

        AcyclicLP acyclicLP = new AcyclicLP(G,5);
        for (DirectedEdge edge : acyclicLP.edgeTo) {
            if (edge==null) continue;
            System.out.println(edge.from()+"->"+ edge.to());
        }
    }
}
