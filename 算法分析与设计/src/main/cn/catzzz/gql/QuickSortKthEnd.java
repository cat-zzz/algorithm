package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   QuickSortKthEnd<br/>
 * Description: 利用快速排序查找数组中第k大的元素<br/>
 * Date: 2022/11/24 21:14<br/>
 * Created by gql
 */
public class QuickSortKthEnd {
    private static int[] input;
    private static int k;
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] inputStr = s.split(",");
        input =new int[inputStr.length-2];
        k=Integer.parseInt(inputStr[1]);
        for (int i = 0; i < inputStr.length-2; i++) {
            input[i] = Integer.parseInt(inputStr[i+2]);
        }
    }
    /**
     * 将比基准数大的数放前面，将比基准数小的数放后面
     * @param arr 整个待排序的数组（不是数组的一部分）
     * @return 比基准数大的元素的个数（索引从left到right）
     */
    public static int swap(int[] arr, int left, int right){
        int base=arr[left];     // 基准数
        while (left < right) {
            while (base >= arr[right] && left < right) {
                right--;
            }
            arr[left] = arr[right];
            while (base <= arr[left] && left < right) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[right]=base;
        return right;
    }

    public static int findKthEnd(int[] arr, int left, int right, int k) {
        int temp = swap(arr, left, right);
        if (temp >= k) {
            return findKthEnd(arr, left, temp - 1, k);
        } else if (temp < k - 1) {
            return findKthEnd(arr, temp + 1, right, k);
        } else {    // temp==k-1
            return arr[temp];
        }
    }
    public static void main(String[] args) {
        input();
        int result=findKthEnd(input, 0, input.length - 1, input.length-k+1);
        System.out.println(result);
    }
}
