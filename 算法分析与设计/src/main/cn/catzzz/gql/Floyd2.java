package cn.catzzz.gql;

/**
 * Classname:   Floyd2<br/>
 * Description: <br/>
 * Date: 2022/12/2 18:39<br/>
 * Created by gql
 */
public class Floyd2 {
    /**
     * 距离矩阵
     */
    public static int[][] distance;
    /**
     * 路径矩阵
     */
    public static int[][] path;

    public static void floyd(int[][] graph) {
        //初始化距离矩阵 distance
        distance = graph;
        //初始化路径
        path = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                path[i][j] = j;
            }
        }
        //开始 Floyd 算法
        //每个点为中转
        for (int i = 0; i < graph.length; i++) {
            //所有入度
            for (int j = 0; j < graph.length; j++) {
                //所有出度
                for (int k = 0; k < graph[j].length; k++) {
                    //以每个点为「中转」，刷新所有出度和入度之间的距离
                    //例如 AB + BC < AC 就刷新距离
                    if (graph[j][i] != -1 && graph[i][k] != -1) {
                        int newDistance = graph[j][i] + graph[i][k];
                        if (newDistance < graph[j][k] || graph[j][k] == -1) {
                            //刷新距离
                            graph[j][k] = newDistance;
                            //刷新路径
                            path[j][k] = i;
                        }
                    }
                }
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        char[] vertices = new char[]{'A', 'B', 'C', 'D'};
        int[][] graph = new int[][]{
                {0, 2, -1, 6}
                , {2, 0, 3, 2}
                , {-1, 3, 0, 2}
                , {6, 2, 2, 0}};
        floyd(graph);
        System.out.println("====distance====");
        for (int[] ints : distance) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("====path====");
        for (int[] ints : path) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
