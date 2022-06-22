package cn.qmulin.graph.base;

import cn.qmulin.base.Bag;

/**
 * @description: 加权有向图
 * @author: xys
 * @date: 2022/6/20 21:59
 */
public class EdgeWeightedDigraph {
    //顶点数
    private final int V;
    //边总数
    private int E;
    //邻接表
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        V = v;
        E = 0;
        adj = new Bag[v];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }
}
