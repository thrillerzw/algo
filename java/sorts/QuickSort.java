package sorts;

/**
 * Created by wangzheng on 2018/10/16.
 */
public class QuickSort {
  public static void main(String[] args) {
    int[] arr = {3,1,2,6,5,4};
    quickSort(arr);
    print(arr);
  }
  public static void print(int[] arr) {
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  // 快速排序，a是数组
  public static void quickSort(int[] a) {
    quickSortInternally(a, 0, a.length-1);
  }

  // 快速排序递归函数，p,r为下标
  private static void quickSortInternally(int[] a, int p, int r) {
    if (p >= r) return;

    int q = partition(a, p, r); // 获取分区点
    quickSortInternally(a, p, q-1);
    quickSortInternally(a, q+1, r);
  }

  /**
   * 这⾥的处理有点类似选择排序。我们通过游标i把A[p…r-1]分成两部分。A[p…i-1]的元素都是⼩于pivot的，我们暂且叫它“已处理区间”，A[i…r-1]是“未处理区间”。
   * 我们每次都从未处理的区间A[i…r-1]中取⼀个元素A[j]，与pivot对⽐，如果⼩于pivot，则将其加⼊到已处理区间的尾部，也就是A[i]的位置。
   * 通过A[i]与A[j]交换，避免了数组插入指定位置时候搬移数据。
   *
   * 快速排序并不是⼀个稳定的排序算法。因为分区的过程涉及交换操作，如果数组中有两个相同的元素，⽐如序列
   * 6，8，7，6，3，5，9，4，在经过第⼀次分区操作之后，两个6的相对先后顺序就会改变。
   *
   * @param a
   * @param p
   * @param r
   * @return
   */
  private static int partition(int[] a, int p, int r) {
    int pivot = a[r];
    int i = p;
    for(int j = p; j < r; j++) {
      //小于分区点的往前放，放到i的位置，大于等于的位置不变
      if (a[j] < pivot) {
        if (i == j) {
           i++; //i和j相等时候，是一样的。
        } else {
          int tmp = a[i];
          a[i] = a[j];
          a[j] = tmp;
          i++;
        }
      }
    }
    //把分区点移动到中间位置。交换i和r的位置。
    int tmp = a[i];
    a[i] = a[r];
    a[r] = tmp;

    System.out.println("i=" + i);
    return i;
  }
}
