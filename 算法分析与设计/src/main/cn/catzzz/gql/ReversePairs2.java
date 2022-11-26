package cn.catzzz.gql;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Classname:   ReversePairs<br/>
 * Description: 求一个数组中逆序对的个数<br/>
 * Date: 2022/11/23 15:37<br/>
 * Created by gql
 */
public class ReversePairs2 {

    public int[] input() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] inputStr = s.split(",");
        int[] input = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }
        return input;
    }

    /**
     * 利用归并排序的思想实现求逆序数
     */
    public static void main(String[] args) {
        ReversePairs2 reversePairs = new ReversePairs2();
        int[] nums= reversePairs.input();
        System.out.println(reversePairs.mergeSort(nums, 0, nums.length - 1));
    }

    public int merge(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l, swaps = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }

    public int mergeSort(int[] arr, int l, int r) {
        int count = 0;
        if (l < r) {
            int middle = (l + r) / 2;
            count += mergeSort(arr, 0, middle);
            count += mergeSort(arr, middle + 1, r);
            count += merge(arr, l, middle, r);
        }
        return count;
    }
}
