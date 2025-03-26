package DataStr;

import java.util.ArrayDeque;
import java.util.Queue;

class BFSDFS {
    int[][] D = new int[][]{{0,1}, {0, -1}, {1, 0}, {-1, 0}};
//    int[][] D = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};

    public void dfs(int i,int j,boolean[][] visited){
        visited[i][j] = true;
        int n = visited.length;
        int m = visited[0].length;
        for (var d : D){
            int x = i + d[0];
            int y = j + d[1];
            if(x<0||y<0||x>=n||y>=m||visited[x][y]){
                continue;
            }
            dfs(x,y,visited);
        }
    }

    public void bfs(int i,int j,boolean[][] visited){
        Queue<int[]> q = new ArrayDeque<>();
        int n = visited.length;
        int m = visited[0].length;

        int size = 0;
        while (!q.isEmpty()){
            int l = q.size();
            for (int k = 0; k < l; k++) {
                var t = q.poll();
                for (var d : D){
                    int x = t[0] + d[0];
                    int y = t[1] + d[1];
                    if (x<0||y<0||x>=n||y>=m||visited[x][y]){
                        continue;
                    }
                    q.add(new int[]{x,y});
                }
            }
            size++;
        }

    }
}
