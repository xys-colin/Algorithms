package cn.qmulin.graph;

import cn.qmulin.graph.base.Digraph;

/**
 * @description: 计算强连通分量的Kosaraju算法
 * 定义: 如果两个顶点v和w是互通可达的,即存在一条v到w的有向路径,也存在一条w到v的有向路径,则称为它们是强连通的
 * 如果一幅有向图中任意两个顶点都是强连通的,则称为这副图是强连通的
 * @author: xys
 * @date: 2022/6/12 16:21
 */
public class KosarajuSCC {
    private boolean[] marked;
    //强连通分量的标识符
    private int[] id;
    //强连通分量的数量
    private int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (Integer s : order.reversePost()) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * v和w是否强连通
     *
     * @param v
     * @param w
     * @return
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * v所在的强连通分量的标识符
     *
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * 图中的强连通分量总数
     *
     * @return
     */
    public int count() {
        return count;
    }
}
