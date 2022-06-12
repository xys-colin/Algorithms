package cn.qmulin.graph.base;

import cn.qmulin.base.Bag;

/**
 * @description: 无向图构建
 * @author: xys
 * @date: 2022/6/9 23:01
 */
public class Graph {
    //顶点数目
    private final int V;
    //边的数目
    private int E;
    //邻接表
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        //初始化邻接表
        adj = new Bag[V];
        //将所有链表初始化为空
        for (int i = 0; i < V; i++) {
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
     * 向图中添加一条边v-w
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 得到与顶点v相邻的所有顶点
     *
     * @param v
     * @return
     */
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
    public int degree(Graph graph, int v) {
        return graph.adj(v).size();
    }

    /**
     * 计算所有顶点的最大度数
     *
     * @param graph
     * @return
     */
    public int maxDegree(Graph graph) {
        int max = 0;
        for (int i = 0; i < graph.V(); i++) {
            int degree = degree(graph, i);
            if (degree > max)
                max = degree;
        }
        return max;
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int i = 0; i < V; i++) {
            s += i + ": ";
            for (Integer w : adj(V)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
