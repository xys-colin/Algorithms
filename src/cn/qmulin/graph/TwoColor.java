package cn.qmulin.graph;

import cn.qmulin.graph.base.Graph;

/**
 * @description: 是否是一幅二分图(双色问题)
 * @author: xys
 * @date: 2022/6/11 15:33
 */
public class TwoColor {
    private boolean[] markded;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {
        markded = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!markded[s])
                dfs(graph, s);
        }
    }

    private void dfs(Graph graph, int v) {
        markded[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!markded[w]) {
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) isTwoColorable = false;
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
