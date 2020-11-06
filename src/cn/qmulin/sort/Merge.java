package cn.qmulin.sort;

/**
 * @description:
 * @author: muLin
 * @time: 2020/11/5 21:15
 */
public class Merge {
    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] a = {7, 8, 4, 5, 3, 9, 2, 5, 4};
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        assert Example.isSorted(a);
        Example.show(a);
    }

    /**
     * 自顶向下的归并排序
     *
     * @param a  数组
     * @param lo 开始下标
     * @param hi 结束下标
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);//将左半边排序
        sort(a, mid + 1, hi);//将右半边排序
        merge(a, lo, mid, hi);//并归结果
    }

    /**
     * 自底向上的归并排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int sz = 1; sz < N; sz = sz + sz) {//sz数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) {//lo:子数组索引
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    /**
     * 原地归并的抽象方法
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo...mid]和a[mid+1...hi]归并
        int i = lo, j = mid + 1;
        for (int k = i; k <= hi; k++) {
            aux[k] = a[k];
        }
        //归并回到a[lo...hi]
        for (int k = i; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Example.less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }
}
