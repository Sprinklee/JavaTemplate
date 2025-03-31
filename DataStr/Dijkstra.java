package DataStr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    //共有两种方法 一种适用于稠密图 一种适用于稀疏图
    //1.稠密图  稠密图：边的数量级和 n^2 相当的图 一般不用

    public int Dij(int[][] times, int n, int k) { //找到可以找到所有节点的最短路径 找不到返回-1
        final int INF = Integer.MAX_VALUE / 2; // 防止加法溢出
        int[][] g = new int[n][n]; // 邻接矩阵
        for (int[] row : g) {
            Arrays.fill(row, INF);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }

        int maxDis = 0;
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[k - 1] = 0;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x < 0) {
                return maxDis; // 最后一次算出的最短路就是最大的
            }
            if (dis[x] == INF) { // 有节点无法到达
                return -1;
            }
            maxDis = dis[x]; // 求出的最短路会越来越大
            done[x] = true; // 最短路长度已确定（无法变得更小）
            for (int y = 0; y < n; y++) {
                // 更新 x 的邻居的最短路
                dis[y] = Math.min(dis[y], dis[x] + g[x][y]);
            }
        }
    }

    //2.堆优化 Dijkstra（适用于稀疏图）
    public int Dij2(int[][] times, int n, int k) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] t : times) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }

        int maxDis = 0;
        int left = n; // 未确定最短路的节点个数
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) { // x 之前出堆过
                continue;
            }
            maxDis = dx; // 求出的最短路会越来越大
            left--;
            for (int[] e : g[x]) {
                int y = e[0];
                int newDis = dx + e[1];
                if (newDis < dis[y]) {
                    dis[y] = newDis; // 更新 x 的邻居的最短路
                    pq.offer(new int[]{newDis, y});
                }
            }
        }
        return left == 0 ? maxDis : -1;
    }


//    以下是我理解的Dij原理
//    迪杰斯特拉的内在原理是最近的即为最有效的，核心是每次选取距离初始点最近的并且之前没有访问过的点
//    那么初始点到这个点的最短距离就是现在的距离，之后没有可能比当前距离更近的可能了，所以就在进一步寻找下一个最近的点
//
//
//    如果一个条件是最优条件，并且其他可能的条件组成的条件不可能比当前的最优，所以这个条件下的最优条件就确定了，可以进一步寻找其他的最优条件了 {贪心}


}
