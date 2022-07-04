package cn.qmulin.graph.directed;

import cn.qmulin.base.Stack;
import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;

/**
 * @description:
 * @author: xys
 * @date: 2022/7/5 0:09
 */
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;


    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            if (!marked[v]) dfs(G, v);

        // 检查有向图是否有循环
        assert check();
    }

    // 检查算法计算拓扑顺序或找到一个有向循环
    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return;
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    //如果边加权有向图有一个有向环，则返回一个有向环，
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }


    private boolean check() {

        // 边加权有向图是循环的
        if (hasCycle()) {
            DirectedEdge first = null, last = null;
            for (DirectedEdge e : cycle()) {
                if (first == null) first = e;
                if (last != null) {
                    if (last.to() != e.from()) {
                        System.err.printf("cycle edges %s and %s not incident\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.to() != first.from()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }
        return true;
    }
}
