package cn.qmulin.graph.directed;

import cn.qmulin.graph.base.Digraph;
import cn.qmulin.graph.directed.DirectedDFS;

/**
 * @description: 顶点对的可达性, 是否存在一条给定顶点v到顶点w的路径
 * @author: xys
 * @date: 2022/6/12 17:14
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    public TransitiveClosure(Digraph g) {
        all = new DirectedDFS[g.V()];
        for (int v = 0; v < g.V(); v++) {
            all[v] = new DirectedDFS(g, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
