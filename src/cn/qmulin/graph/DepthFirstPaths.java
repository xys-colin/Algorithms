package cn.qmulin.graph;

import cn.qmulin.base.Stack;
import cn.qmulin.graph.base.Graph;

/**
 * @description: 使用深度优先搜索查找图中的路径
 * @author: xys
 * @date: 2022/6/10 0:19
 */
public class DepthFirstPaths {
    private boolean[] marked;
    //从起点到一个顶点的已知路径上的最后一个顶点,是用一棵用父链接表示的以s为根且含有所有与s连通的顶点的树
    private int[] edgeTo;
    //起点
    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!marked[w]) {
                //记录到w顶点前一个顶点v
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    /**
     * 是否存在从s到v的路径
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 起点s到v的路径,如果不存在则返回null
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
