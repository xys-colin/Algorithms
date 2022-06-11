package cn.qmulin.graph;

import cn.qmulin.graph.base.Digraph;

/**
 * @description: 有向图的可达性
 * @author: xys
 * @date: 2022/6/11 18:30
 */
public class DirectedDFS {
    private boolean[] marked;

    /**
     * 在图中找到从s可达的所有顶点
     * @param g
     * @param s
     */
    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    /**
     * 在图中找到从sources中的所有顶点可达的所有顶点
     * @param g
     * @param sources
     */
    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.V()];
        for (Integer s : sources) {
            if (!marked[s]) {
                dfs(g, s);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
