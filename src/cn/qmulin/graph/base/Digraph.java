package cn.qmulin.graph.base;

import cn.qmulin.base.Bag;

/**
 * @description: 有向图
 * @author: xys
 * @date: 2022/6/11 17:43
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private Digraph reverse;

    public Digraph(int v) {
        this.V = v;
        this.E = 0;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 向图中添加一条边v->w
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Bag<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 计算顶点v的出度数
     *
     * @param graph
     * @param v
     * @return
     */
    public int degree(Digraph graph, int v) {
        return graph.adj(v).size();
    }

    /**
     * 计算顶点v的入度数
     *
     * @param v
     * @return
     */
    public int indegree(int v) {
        if (reverse == null) {
            reverse = reverse();
        }
        return reverse.adj(v).size();
    }

    /**
     * 该图的反向图,即将其中所有边的方向反转
     *
     * @return
     */
    public Digraph reverse() {
        reverse = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (Integer w : adj(i)) {
                reverse.addEdge(w, i);
            }
        }
        return reverse;
    }
}
