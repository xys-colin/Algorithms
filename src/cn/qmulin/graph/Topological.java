package cn.qmulin.graph;

import cn.qmulin.graph.base.Digraph;
import cn.qmulin.graph.base.SymbolDigraph;

/**
 * @description: 拓扑排序, 当且仅当一幅有向图是无环图时它才能进行拓扑排序
 * 一幅有向无环图的拓扑排序顺序即为所有顶点的逆后序排列
 * @author: xys
 * @date: 2022/6/12 12:36
 */
public class Topological {
    //顶点的拓扑顺序
    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    /**
     * 拓扑有序的所有顶点
     *
     * @return
     */
    public Iterable<Integer> getOrder() {
        return order;
    }

    /**
     * 图是有向无环图吗
     *
     * @return
     */
    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = "jobs.txt";
        String separator = "/";
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological topological = new Topological(sg.G());
        for (Integer w : topological.getOrder()) {
            System.out.println(sg.name(w));
        }
    }
}
