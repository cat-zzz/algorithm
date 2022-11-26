package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   <br/>
 * Description: 01背包问题（动态规划）<br/>
 * Date: 2022/11/19 21:03<br/>
 * Created by gql
 */
public class BackPack {

    private static int[] wt;
    private static int[] val;
    private static int w;
    private static int n;

    /**
     * 输入数据并整理数据格式
     */
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(";");
        w = Integer.parseInt(s1[0]);  // 背包容量
        // wt表示商品的体积，val表示商品的价格
        wt = new int[s1.length];
        val = new int[s1.length];
        n=s1.length-1;
        wt[0] = val[0] = 0; // 从下标1开始
        // 获取每个商品的体积和价格
        for (int i = 1; i < s1.length; i++) {
            int position = s1[i].indexOf(',');
            int length = s1[i].length();
            wt[i] = Integer.parseInt(s1[i].substring(0, position));
            val[i] = Integer.parseInt(s1[i].substring(position + 1, length));
        }
    }

    /**
     * 01背包问题
     *
     * @param w   背包容量
     * @param n   物品个数
     * @param wt  每个商品的体积
     * @param val 每个商品的价值
     */
    public static int backpack(int w, int n, int[] wt, int[] val) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j - wt[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i]] + val[i]);
                }
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        input();
        int v = backpack(w, n, wt, val);
        System.out.println(v);
    }
}
