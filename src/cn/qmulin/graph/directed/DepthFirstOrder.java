package cn.qmulin.graph.directed;

import cn.qmulin.base.Queue;
import cn.qmulin.base.Stack;
import cn.qmulin.graph.base.Digraph;
import cn.qmulin.graph.base.DirectedEdge;
import cn.qmulin.graph.base.EdgeWeightedDigraph;
import cn.qmulin.graph.base.Graph;

/**
 * @description: 有向图中基于深度优先搜索的顶点排序
 * @author: xys
 * @date: 2022/6/12 12:10
 */
public class DepthFirstOrder {
    private boolean[] marked;
    //所有顶点的前序排列 (在递归调用之前将顶点加入队列中)
    private Queue<Integer> preorder;
    //所有顶点的后序排列 (在递归调用之后将顶点加入队列中)
    private Queue<Integer> postorder;
    //所有顶点的逆后序排列 (在递归调用之后将顶点压入栈中)
    private Stack<Integer> reversePost;
    private int preCounter;// 前序计数
    private int postCounter;// 后序计数


    public DepthFirstOrder(Digraph g) {
        preorder = new Queue<>();
        postorder = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        postorder = new Queue<Integer>();
        preorder = new Queue<Integer>();
        reversePost = new Stack<>();
        marked = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph g, int v) {
        preorder.enqueue(v);
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }
        postorder.enqueue(v);
        reversePost.push(v);
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        preorder.enqueue(v);
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> preorder() {
        return preorder;
    }

    public Iterable<Integer> postorder() {
        return postorder;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
