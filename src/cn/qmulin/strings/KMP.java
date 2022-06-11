package cn.qmulin.strings;

/**
 * @description:
 * @author: xys
 * @date: 2022/2/17 17:32
 */
public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        //由模式串构建dfa；
        this.pat = pat;
        int m = pat.length();
        int R = 256;
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            //计算dfa[][j]
            for (int c = 0; c < 256; c++) {
                //复制匹配失败情况下的值
                dfa[c][j] = dfa[c][x];
            }
            //设置匹配成功情况下的值
            dfa[pat.charAt(j)][j] = j + 1;
            //更新重启状态
            x = dfa[pat.charAt(j)][x];
        }
    }

    public int search(String txt) {
        //在txt上模拟dfa运行
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; j < m && i < n; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;
        return -1;
    }
}
