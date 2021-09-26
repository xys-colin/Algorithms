package cn.qmulin.sort;

/**
 * @description:
 * @author: muLin
 * @time: 2020/11/3 19:34
 */
public class Insertion {
    public static void main(String[] args) {
        String[] a="S O R T E X A M P L E".split(" ");
        sort(a);
        assert  Example.isSorted(a);
        Example.show(a);
    }
    public static void sort(Comparable[] a){
        int N =a.length;
        for (int i = 1; i < N; i++) {
            Comparable tmp=a[i];
            int j=i;
            while (j>0&&Example.less(tmp,a[j-1])){
               a[j]=a[j-1];
               j--;
            }
           a[j]=tmp;
        }
    }
}
