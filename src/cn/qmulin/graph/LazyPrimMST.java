package cn.qmulin.graph;

import cn.qmulin.base.In;
import cn.qmulin.base.Queue;
import cn.qmulin.graph.base.Edge;
import cn.qmulin.graph.base.EdgeWeightedGraph;

import java.util.PriorityQueue;

/**
 * @description: 最小生成树的Prim算法的延时实现
 * @author: xys
 * @date: 2022/6/15 16:29
 */
public class LazyPrimMST {
    //最小生成树的顶点
    private boolean[] marked;
    //最小生成树的边
    private Queue<Edge> mst;
    //横切边(包括失效的边)
    private PriorityQueue<Edge> pq;
    //最小生成树的总权重
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new PriorityQueue<>();
        marked = new boolean[graph.getV()];
        mst = new Queue<>();
        //假设图是连通的
        visit(graph, 0);
        while (!pq.isEmpty()) {
            //从pq中得到权重最小的点
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            //跳过失效的边
            if (marked[v] && marked[w]) continue;
            //将边添加到树中
            mst.enqueue(e);
            weight+=e.getWeight();
            //将顶点v或w添加到树中
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        //标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            if (!marked[edge.other(v)]) {
                pq.offer(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight(){
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

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
