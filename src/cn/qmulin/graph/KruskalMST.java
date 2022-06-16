package cn.qmulin.graph;

import cn.qmulin.base.Queue;
import cn.qmulin.graph.base.Edge;
import cn.qmulin.graph.base.EdgeWeightedGraph;
import cn.qmulin.base.MinPQ;

/**
 * @description: 最小生成树的Kruskal算法
 * Kruskal一般比Prim要慢
 * @author: xys
 * @date: 2022/6/16 17:05
 */
public class KruskalMST {
    private Queue<Edge> mst;
    private double weight = 0.0;
    //父链接数组
    private int[] id;
    //各个根节点所对应的分量的大小
    private int[] sz;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        id = new int[graph.getV()];
        sz = new int[graph.getV()];
        MinPQ<Edge> pq = new MinPQ<Edge>(1);
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        for (int i = 0; i < graph.getV(); i++) {
            id[i] = i;
            sz[i] = 1;
        }
        while (!pq.isEmpty() && mst.size() < graph.getV() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either(), w = edge.other(v);
            if (connected(v, w)) continue;
            union(v, w);
            weight += edge.getWeight();
            mst.enqueue(edge);
        }
    }

    private void union(int v, int w) {
        int i = find(v);
        int j = find(w);
        if (i == j) return;
        //将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    private boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    private int find(int v) {
        //跟随链接找到根节点
        while (v != id[v]) v = id[v];
        return v;
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
