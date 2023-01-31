import java.util.*;

public class KnightMove {

    static class cell {

        int x, y;
        LinkedList<cell> path;
        int dis;

        public cell(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.path = new LinkedList<>();
        }
    }

    static boolean isInside(int x, int y, int n, int m, Character[][] board) {
        if (x >= 1 && x <= n && y >= 1 && y <= m)
            return board[x - 1][y - 1] == 'D' || board[x - 1][y - 1] == '-';
        return false;
    }


    static void minimumSteps(int n, int m, cell knightPos, LinkedList<cell> Targets, Character[][] board) {
        // Possible moves for knight
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        Vector<cell> q = new Vector<>();
        q.add(knightPos);
        knightPos.path.add(knightPos);
        cell t = null, finalCell = null;
        int x, y, result = -1;
        boolean visited[][] = new boolean[n][m];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                visited[i - 1][j - 1] = false;

        visited[knightPos.x - 1][knightPos.y - 1] = true;

        while (!q.isEmpty()) {
            t = q.firstElement();
            q.remove(0);
            Boolean flag = false;

            for (cell cur : Targets)
                if (t.x == cur.x && t.y == cur.y) {
                    result = t.dis;
                    finalCell = t;
                    flag = true;
                    break;
                }

            if (flag)
                break;

            for (int i = 0; i < 8; i++) {

                x = t.x + dx[i];
                y = t.y + dy[i];

                if (isInside(x, y, n, m, board) && !visited[x - 1][y - 1]) {
                    visited[x - 1][y - 1] = true;
                    cell newCell = new cell(x, y, t.dis + 1);
                    newCell.path = (LinkedList<cell>) t.path.clone();
                    newCell.path.add(newCell);
                    q.add(newCell);
                }
            }
        }

        System.out.println(result);

        if (finalCell != null)
            if (finalCell.path.size() > 0)
                for (cell cell : finalCell.path)
                    System.out.print(String.format("%d %d\n", cell.x, cell.y));
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        cell startC = null;
        LinkedList<cell> endCs = new LinkedList<>();
        int n, m;
        //int temp1 = 0, temp2 = 0;
        n = scan.nextInt();
        m = scan.nextInt();
        Character[][] board = new Character[n][m];

        //Here we start from 0, but we'll shift them to 1 in methods
        for (int i = 0; i < n && scan.hasNext(); i++) {
            String line = scan.next();

            for (int j = 0; j < m; j++) {

                Character ch = line.charAt(j);
                board[i][j] = ch;
                if (ch.equals('K'))
                    startC = new cell(i + 1, j + 1, 0);

                else if (ch.equals(('D'))) {
                    cell tempCell = new cell(i + 1, j + 1, 0);
                    endCs.add(tempCell);
                }
            }
        }

//        System.out.println(startC.x + " " + startC.y);
//        System.out.println(Arrays.deepToString(board));
//        for (cell endC : endCs) {
//            System.out.println(endC.x + " " + endC.y);
//        }

        minimumSteps(n, m, startC, endCs, board);
    }
}

