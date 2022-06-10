package cn.qmulin.graph;

import cn.qmulin.base.Queue;
import cn.qmulin.base.Stack;

/**
 * @description: 使用广度优先搜索查找图中的路径
 * @author: xys
 * @date: 2022/6/10 12:23
 */
public class BreadthFirstPaths {
    //到达该顶点的最短路径已知吗
    private boolean[] marked;
    //到达该顶点的已知路径上的最后一个顶点
    private int[] edgeTo;
    //起点
    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new Queue<>();
        //标记起点
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            //从队列中删去下一顶点
            int v = queue.dequeue();
            for (Integer w : graph.adj(v)) {
                //对于每个没被标记的相邻顶点
                if (!marked[w]) {
                    edgeTo[w] = v;//保存最短路径的最后一条边
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 起点s到v的路径,如果不存在则返回null
     *
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
