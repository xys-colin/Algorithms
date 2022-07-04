package cn.qmulin.graph.undirected;

import cn.qmulin.graph.base.Graph;

/**
 * @description: 使用深度优先搜索找出图中的所有连通分量
 * @author: xys
 * @date: 2022/6/10 16:04
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : graph.adj(v)) {
            if (!marked[w])
                dfs(graph, w);
        }
    }

    /**
     * 判断v和w是否连通
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}
