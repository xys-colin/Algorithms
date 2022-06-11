package cn.qmulin.graph.base;

import cn.qmulin.base.In;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 无向符号图, 用字符串而非整数来表示和指代顶点
 * @author: xys
 * @date: 2022/6/11 16:11
 */
public class SymbolGraph {
    //符号名->索引
    private Map<String, Integer> st;
    //索引->符号名
    private String[] keys;
    //图
    private Graph graph;

    public SymbolGraph(String stream, String sp) {
        st = new HashMap<>();
        //第一遍,构建索引
        In in = new In(stream);
        while (in.hasNextLine()) {
            //读取字符串
            String[] a = in.readLine().split(sp);
            //为每个不同的字符串关联一个索引
            for (String s : a) {
                if (!st.containsKey(s)) {
                    st.put(s, st.size());
                }
            }
        }
        //用来获取顶点名的反向索引是一个数组
        keys = new String[st.size()];
        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }
        graph = new Graph(st.size());
        //第二遍,构建图
        in = new In(stream);
        while (in.hasNextLine()) {
            //将每一行的第一个顶点和该行的其他顶点相连
            String[] a = in.readLine().split(sp);
            Integer v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * key是一个顶点吗
     *
     * @param s
     * @return
     */
    public boolean contains(String s) {
        return st.containsKey(s);
    }

    /**
     * key的索引
     *
     * @param s
     * @return
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * 索引v的顶点名
     *
     * @param v
     * @return
     */
    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return graph;
    }
}
