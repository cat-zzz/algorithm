package cn.catzzz.gql;

import java.util.Scanner;

/**
 * Classname:   Floyd<br/>
 * Description: Floyd算法<br/>
 * Date: 2022/12/2 12:35<br/>
 * Created by gql
 */
public class Floyd {
    /*
     * 算法思想：遍历每一个点-->遍历每一个点的入度-->遍历每一个点的出度-->
     * -->以这个点为中转站，如果这个点到目标点的距离更短就刷新距离
     */
    public static int[][] graph;
    public static int[][] path;
    public static int[][] xy;
    public static int n, q;

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        q = scanner.nextInt();
        graph = new int[n][n];
        xy= new int[q][2];
        // 输入有向图的邻接矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        // 输入q次查询的x，y
        for (int i = 0; i < q; i++) {
            xy[i][0] = scanner.nextInt();
            xy[i][1] = scanner.nextInt();
        }
    }

    /**
     *
     * @param graph 邻接矩阵
     * @param distance 距离矩阵，用来储存每个点到其他点的最短距离
     * @param path 路径矩阵，用来储存每个点到其他点最短距离的路径<br/>
     *  举例：path[i][j]=k表示从结点i到j的最短距离需要经过结点k
     */
    public static void floyd(int[][] graph, int[][] distance, int[][] path) {
        // 初始化distance的值为graph的值
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, distance[i], 0, graph.length);
        }
        // 初始化path
        path = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                path[i][j] = j;     // 初始时，将路径设为直达路径
            }
        }
        // 遍历每一个结点
        for (int i = 0; i < graph.length; i++) {
            // 结点的所有入度，即从j到i的距离
            for (int j = 0; j < graph.length; j++) {
                // j到i不直接相连
                if (graph[j][i] == -1)  continue;
                // 结点的所有出度，即从i到k的距离
                for (int k = 0; k < graph[j].length; k++) {
                    // 判断从j到k是经过i的距离短，还是不经过i的距离短
                    if (graph[i][k]==-1) continue;
                    int newDistance = graph[j][i] + graph[i][k];
                    if (newDistance < graph[j][k] || graph[j][k] == -1) {
                        distance[j][k] = newDistance;
                        path[j][k] = i;     // 从j到k需要经过i点，至于从j到i会不会经过其他点，就要看path[j][i]的值是多少
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        int[][] distance = new int[n][n];
        int[][] path = new int[n][n];
        floyd(graph,distance, path);
        for (int[] ints : xy) {
            int j = ints[0], k = ints[1];
            System.out.println(distance[j-1][k-1]);
        }
    }
}
