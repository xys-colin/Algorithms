package cn.qmulin.graph.undirected;

import cn.qmulin.graph.base.Graph;

/**
 * @description: 检测图是否是无环图(假设不存在自环和平行边)
 * @author: xys
 * @date: 2022/6/11 15:12
 */
public class Cycle {
    private boolean[] markded;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        markded = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!markded[i]) {
                dfs(graph, i, i);
            }
        }
    }

    private void dfs(Graph graph, int v, int u) {
        markded[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!markded[w])
                dfs(graph, w, v);
            else if (w != u) hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
