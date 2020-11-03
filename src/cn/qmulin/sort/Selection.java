package cn.qmulin.sort;

/**
 * @description: 选择排序
 * @author: muLin
 * @time: 2020/10/29 17:16
 */
public class Selection {
    public static void main(String[] args) {
        String[] a="S O R T E X A M P L E".split(" ");
        sort(a);
        assert  Example.isSorted(a);
        Example.show(a);
    }
   public static void sort(Comparable[] a){
       //将a[] 按升序排序
       int N=a.length;
       for (int i = 0; i < N; i++) {
           //将a[i]和a[i+1..N]中最小的元素交换
           int min=i;//最小元素的索引
           for (int j = i+1; j < N; j++) {
                if (Example.less(a[j],a[min])) min=j;
           }
           Example.exch(a,i,min);
       }
   }
}
