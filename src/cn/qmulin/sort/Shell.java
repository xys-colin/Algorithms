package cn.qmulin.sort;

/**
 * @description: 希尔排序
 * @author: muLin
 * @time: 2020/11/5 20:43
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = h * 3 + 1;//1,4,13,40,121,...
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[]i-h],a[i-2*h],a[i-3*h]...之中
                for (int j = i; j >= h && Example.less(a[j], a[j - h]); j -= h) {
                    Example.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }

    }
}
