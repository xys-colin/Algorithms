package cn.qmulin.graph.directed;

import cn.qmulin.base.Stack;
import cn.qmulin.graph.base.Digraph;

/**
 * @description: 有向图中寻找有向环, 判断图是否是有向无环图
 * 有向环检测轨迹: 0->5->4->3->5
 * @author: xys
 * @date: 2022/6/11 23:04
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    //有向环中所有的顶点(如果存在)
    private Stack<Integer> cycle;
    //递归调用的栈上的所有顶点
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) dfs(g, v);
        }
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }
}
