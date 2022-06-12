package cn.qmulin.graph;

import cn.qmulin.base.Queue;
import cn.qmulin.graph.base.Digraph;

/**
 * @description: 基于队列的拓扑排序, 使用由顶点索引的数组来保存每个顶点的入度数
 * 1.从队列中删除一个起点并将其标记
 * 2.遍历由被删除顶点指出的所有边,将所有被指向的顶点的入度减一
 * 3.如果顶点的入度数变为0,将它插入起点队列
 * @author: xys
 * @date: 2022/6/12 15:03
 */
public class TopologicalX {
    //顶点的拓扑顺序
    private Queue<Integer> order;
    //ranks [v] = 顶点 v 按顺序出现的顺序
    private int[] ranks;

    public TopologicalX(Digraph g) {
        //记录顶点的入度
        int[] indegree = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            indegree[v] = g.indegree(v);
        }
        ranks = new int[g.V()];
        order = new Queue<>();
        int count = 0;
        //初始化队列以包含所有indegree = 0 的顶点
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < g.V(); v++) {
            if (indegree[v] == 0) queue.enqueue(v);
        }
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.enqueue(v);
            ranks[v] = count++;
            for (Integer w : g.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }
        if (count != g.V()) {
            order = null;
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    /**
     * 顶点的拓扑顺序
     * @param v
     * @return
     */
    public int rank(int v) {
        if (hasOrder())
            return ranks[v];
        else
            return -1;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(13);
        digraph.addEdge(2,3);
        digraph.addEdge(0,6);
        digraph.addEdge(0,1);
        digraph.addEdge(2,0);
        digraph.addEdge(11,12);
        digraph.addEdge(9,12);
        digraph.addEdge(9,10);
        digraph.addEdge(9,11);
        digraph.addEdge(3,5);
        digraph.addEdge(8,7);
        digraph.addEdge(5,4);
        digraph.addEdge(0,5);
        digraph.addEdge(6,4);
        digraph.addEdge(6,9);
        digraph.addEdge(7,6);
        TopologicalX topologicalX = new TopologicalX(digraph);
        for (Integer w : topologicalX.order()) {
            System.out.println(w+" ");
        }
    }
}
