package cn.qmulin.sort;

/**
 * @description: 一般策略是先随意的取a[lo]作为切分元素,即那个将会被排定的元素,然后从
 * 数组的左端开始向右扫描直到找到一个大于等于它的元素,再从数组的右端开始向左扫描直到找到一个小于等于它的元素
 * @author: muLin
 * @time: 2020/11/14 23:22
 */
public class Quick {

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);//切分
        sort(a, lo, j - 1);//将左半部分a[lo .. j-1]排序
        sort(a, j + 1, hi);//将右半部分a[j+1 .. hi]排序
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //将数组切分为a[lo..i-1], a[i],a[i+1..hi]
        int i = lo, j = hi + 1;    //左右扫描指针
        Comparable v = a[lo];     //切分元素
        while (true) {           //扫描左右,检查扫描是否结束并交换元素
            while (Example.less(a[++i], v)) if (i == hi) break;
            while (Example.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Example.exch(a, i, j);
        }
        Example.exch(a, lo, j);   //将v=a[j]放入正确的位置
        return j;               //a[lo..j-1] <=a[j] <=a[j+1..hi]达成
    }

    /**
     * 三向切分的快速排序
     * @param a
     * @param lo
     * @param hi
     */
    private static void quick3Way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) Example.exch(a, lt++, i++);    //a[i]小于V, 将a[lt]和a[i]交换, 将lt和i加一
            else if (cmp > 0) Example.exch(a, i, gt--); //a[i]大于V, 将a[gt]和a[i]交换, 将gt减一
            else i++;                                   //a[i]等于v, 将i加一
        }//现在 a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi] 成立
        quick3Way(a, lo, lt - 1);
        quick3Way(a, gt + 1, hi);
    }

}
