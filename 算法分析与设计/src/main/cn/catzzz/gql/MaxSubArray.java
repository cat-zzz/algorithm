package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   MaxSubArray<br/>
 * Description: 最大子数组之和<br/>
 * Date: 2022/11/22 17:24<br/>
 * Created by gql
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = input();
        Result max = maxSubArray(nums);
        System.out.println(max);
    }

    /**
     * 输入数据并整理数据格式
     */
    public static int[] input() {
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
     * 求最大连续子数组之和
     */
    public static Result maxSubArray(int[] nums) {
        /*
         * 思路：dp[i]表示以nums[i]结尾的子数组的最大和，
         * 不能定义为nums[0]到nums[i]中的最大子数组为dp[i]，因为nums[0]到nums[i]中的最大子数组不一定与nums[i+1]连续，
         * 所以就无法通过dp[i]推导出dp[i+1]。
         * 那么，dp[i]有两种选择。1. 与前面的相邻子数组连接，形成一个和更大的子数组。2. 不与前面的子数组连接，自己就是最大的子数组。
         * 即dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
         *
         */
        int current = 0, max = 0;   // current表示前i项和，max表示前i项中最大的子数组之和
        int index_start = 0, index_end = 0, temp = 0;        // 初始化子数组最大和下标
        Result result = new Result(0, 0, 0);
        if (nums.length <= 1) {     // 只有一个元素
            result.max = nums[0];
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            current += nums[i];
            if (current < 0) {
                current = 0;
                temp = i + 1;   // 调整最大子数组的开始下标
            }
            if (current > max) {
                max = current;
                index_end = i;  // 调整最大子数组的结束下标
                index_start = temp;
            }
        }
        if (max == 0) {     // 最大和依然为0，说明数组中所有元素都为负值
            max = nums[0];
            index_start = index_end = 0;
            // 查找数组的最大值
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    index_start = index_end = i;
                }
            }
        }
        result.start = index_start;
        result.end = index_end;
        result.max = max;
        return result;
    }

    /**
     * 用于表示输出结果（起止下标及最大值）
     */
    public static class Result {
        int start, end;
        int max;

        public Result(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
        }

        @Override
        public String toString() {
            return "X[" + start + ',' + end + "]=" + max;
        }
    }
}
