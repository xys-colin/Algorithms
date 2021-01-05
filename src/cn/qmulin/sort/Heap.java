package cn.qmulin.sort;

import java.util.Arrays;

/**
 * @description: 堆排序
 * @author: muLin
 * @time: 2021/1/5 10:59
 */
public class Heap {
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split(" ");
        String[] s = new String[12];
        System.arraycopy(a, 0, s, 1, 11);
        sort(s);
        System.out.println(Arrays.toString(s));
    }

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int i = N / 2; i >= 1; i--) {
            sink(a, i, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);

        }
    }

    //下沉
    public static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }
}
