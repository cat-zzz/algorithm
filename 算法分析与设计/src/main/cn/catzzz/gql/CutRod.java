package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   CutRod<br/>
 * Description: 钢条切割问题<br/>
 * Date: 2022/11/28 20:44<br/>
 * Created by gql
 */
public class CutRod {
    public static int[] p;  // 钢条切割大小及其价格
    public static int n;    // 钢条长度
    public static void input(){
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        String[] inputSplit = inputStr.split(";");
        n= Integer.parseInt(inputSplit[0]);     //钢条长度
        p=new int[inputSplit.length];
        for (int i = 1; i < inputSplit.length; i++) {   // i从1开始。i表示长度为i的钢条价格
            String[] s2 = inputSplit[i].split(",");
            p[i]= Integer.parseInt(s2[1]);  // s2的第二个元素表示价格
        }
    }
    public static void main(String[] args) {
        input();
        int max = cutRod(p, n);
        System.out.println(max);
    }
    public static int cutRod(int[] p, int n)
    {
        int[] r=new int[n+1];   // r[i]表示钢条长度为i的最优解
        r[0]=0; // 钢条长度为0，最优解也是0
        for (int i = 1; i <= n; i++) {
            int q=Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                q=Math.max(q,p[j]+r[i-j]);
            }
            r[i]=q;
        }
        return r[n];
    }
}
