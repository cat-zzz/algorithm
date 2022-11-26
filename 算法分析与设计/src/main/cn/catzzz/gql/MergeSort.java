package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   MergeSort<br/>
 * Description: 归并排序<br/>
 * Date: 2022/11/24 21:53<br/>
 * Created by gql
 */
public class MergeSort {

    public static int[] input() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] inputStr = s.split(",");
        int[] input =new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }
        return input;
    }

    public static void sort(int[] a, int start, int end) {
        if (start>=end)
            return;
        int mid = (start + end) / 2;
        sort(a, start, mid);    // 对左边数组进行递归
        sort(a, mid + 1, end);  // 对右边数组进行递归
        merge(a, start, mid, end);
    }

    /**
     * 将两个数组（一个数组的两部分）进行合并，使合并后仍然有序
     */
    public static void merge(int[] a, int start, int mid, int end) {
        int[] temp = new int[a.length];
        int k=0;
        int i = start;
        int j=mid+1;
        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                temp[k] = a[i];
                k++;
                i++;
            }else {
                temp[k++] = a[j++];
            }
        }
        // 将剩余部分依次放入临时数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= end) {
            temp[k++] = a[j++];
        }
        // 将临时数组中的内容拷贝回原数组中(start-end范围的内容)
        if (k >= 0) System.arraycopy(temp, 0, a, start, k);
    }
    public static void main(String[] args) {
        int[] a = input();
        sort(a,0, a.length-1);
        for (int i = 0; i < a.length-1; i++) {
            System.out.print(a[i]+",");
        }
        System.out.println(a[a.length-1]);
    }
}
