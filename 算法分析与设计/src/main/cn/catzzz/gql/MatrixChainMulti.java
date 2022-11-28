package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   MatrixChainMulti<br/>
 * Description: 矩阵链乘法问题<br/>
 * Date: 2022/11/26 16:33<br/>
 * Created by gql
 */
public class MatrixChainMulti {
    public static int[] input() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(";");
        String[] cost_str = s1[1].split(",");
        int[] cost = new int[cost_str.length];
        for (int i = 0; i < cost_str.length; i++) {
            cost[i] = Integer.parseInt(cost_str[i]);
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] chain = input();
        int[][] pos = matrixChainMulti(chain);
//        System.out.printf("最少计算次数为 : %d 次\n", dpSolution(chain));
        printBracket(1, chain.length - 1, pos);
    }

    /**
     * @param cost 矩阵链
     * @return 最优化矩阵乘积的标量乘积次数
     */
    private static int solution(int i, int j, int[] cost) {
        // 单个矩阵不用做标量乘积
        if (i == j) {
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        // k为矩阵链Ai..Aj的最优化方案
        for (int k = i; k < j; k++) {
            minCost = Math.min(minCost, solution(i, k, cost) + solution(k + 1, j, cost)
                    + cost[i - 1] * cost[k] * cost[j]);
        }
        return minCost;
    }

    /**
     * @param cost 矩阵链
     * @return 最优化括号方案标量乘积次数<br />
     * Bottom -> Top solution
     */
    private static int dpSolution(int[] cost) {
        //矩阵数量
        int matrixCount = cost.length - 1;
        //最优子结构解
        int[][] dp = new int[matrixCount + 1][matrixCount + 1];
        //现在考虑C(2,n)个子问题，从小问题逐渐求解
        for (int i = 2; i <= matrixCount; i++) {
            //考虑i为子问题规模，loopTimes为规模求解次数
            int loopTimes = matrixCount - i + 1;
            //对于每个规模下的j->k自底向上求解
            for (int j = 1; j <= loopTimes; j++) {
                int subMin = Integer.MAX_VALUE;
                //对于当前求解的矩阵链，通过j(矩阵链起始位置)与i(矩阵链长度)计算出矩阵链的截至位置
                int endPos = j + i - 1;
                for (int k = j; k <= endPos - 1; k++) {
                    subMin = Math.min(subMin, dp[j][k]
                            + dp[k + 1][endPos] +
                            cost[j - 1] * cost[k] * cost[endPos]);
                }
                dp[j][endPos] = subMin;
            }
        }
        return dp[1][matrixCount];
    }

    private static int[][] matrixChainMulti(int[] cost) {
        int matrixCount = cost.length - 1;
        // dp[i][j]表示Ai到Aj之间的矩阵链的最小相乘次数
        int[][] dp = new int[matrixCount + 1][matrixCount + 1];
        int[][] plan = new int[matrixCount + 1][matrixCount + 1];

        /*
         * 变量说明
         * i: 子问题规模。如i=2，查找矩阵链中哪2个矩阵相乘次数最小。
         * loops: 在子问题规模为i的情况下，需要循环求解的次数。
         * j: 子问题起始位置，从下标1开始遍历，
         * endPos: 对于当前求解的矩阵链，通过j(矩阵链起始位置)与i(矩阵链长度)计算出矩阵链的截至位置。
         */
        for (int i = 2; i <= matrixCount; i++) {
            int loops = matrixCount - i + 1;
            for (int j = 1; j <= loops; j++) {
                int end = j + i - 1;
                int minCost = Integer.MAX_VALUE;
                int bracketPos = -1;    // 划分位置
                for (int k = j; k <= end - 1; k++) {
                    // 递归式: dp[i][j]=dp[i][k]+dp[k+1][j]+cost[i]*cost[k]*cost[j] (i<j)
                    if (dp[j][k] + dp[k + 1][end] + cost[j - 1] * cost[k] * cost[end] < minCost) {
                        minCost = dp[j][k] + dp[k + 1][end] + cost[j - 1] * cost[k] * cost[end];
                        bracketPos = k;
                    }
                }
                dp[j][end] = minCost;
                plan[j][end] = bracketPos;
            }
        }
        return plan;
    }

    /**
     * 根据MatrixChain求解结果输出括号化方案
     * @param i    矩阵链起始位置
     * @param j    矩阵链截至位置
     * @param plan 括号化方案
     */
    private static void printBracket(int i, int j, int[][] plan) {
        char leftBracket = '(', rightBracket = ')';
        String mtx = "U";
        if (i == j) {
            //平凡case，输出矩阵标识
            System.out.print(mtx + i);
        } else {
            System.out.print(leftBracket);
            //当前矩阵链分割左侧的括号化方案
            printBracket(i, plan[i][j], plan);
            //当前矩阵链分割右侧的括号化方案
            printBracket(plan[i][j] + 1, j, plan);
            System.out.print(rightBracket);
        }
    }

}
