import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphToTree {
    int V; // Vertices
    LinkedList<Integer> nei[]; // Neighbor of vertices

    public void setV(int v) {
        V = v;
    }

    GraphToTree(int v) { // Constructor

        nei = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            nei[i] = new LinkedList();
        }
    }

    void edgeAdd(int v, int w) { // Method to add edge to graph
        nei[v].add(w);
        nei[w].add(v);
    }


    Boolean isCyclic(int v, Boolean visited[], int parent) {  // a function to check if the graph has cycle!
        visited[v] = true;
        int i;

        Iterator<Integer> it = nei[v].iterator();
        while (it.hasNext()) {

            i = it.next();
            if (!visited[i]) {

                if (isCyclic(i, visited, v))
                    return true;

            } else if (i != parent)
                return true;
        }
        return false;
    }


    void addToGraph() {
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        int Unvisiteds = 0;
        //visited[v] = true;
        int i;

        for (int j = 0; j < V; j++)
            if (!visited[j])
                Unvisiteds++;
        if (Unvisiteds == V) {
            System.out.println(V - 1);
            int u = 1;
            while (u != V) {
                System.out.println(u++ + " " + u);
            }
                    /*for (int j = 0; j < V-1; j++) {
                        System.out.println(V-1 + " " + );
                    }*/
        }


    }

    void removeFromGraph() {
        //System.out.println("Nothing for NOW :)");
        System.out.println(0);
    }

    Boolean isTree() { // Method to check 2 condition for a graph to be a tree

        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        if (isCyclic(0, visited, -1)) // Cyclic condition
            return false;

        for (int j = 0; j < V; j++) { // Connection condition
            if (!visited[j])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int m, n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        GraphToTree g1 = new GraphToTree(n);
        g1.setV(n);
        m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            g1.edgeAdd(scan.nextInt() - 1, scan.nextInt() - 1);
        }

        /*if (g1.isTree())
            System.out.println("is Tree");
        else
            System.out.println("Not a Tree");*/

        if (g1.isTree())
            System.out.println("0\n0");
        else {
            g1.removeFromGraph();
            g1.addToGraph();
        }
    }
}
