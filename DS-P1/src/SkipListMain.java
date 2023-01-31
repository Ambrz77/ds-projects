import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class SkipListMain {

    public SkipListMain() {
        top = new Node(0, 1);
        count = new ArrayList<Integer>();
        count.add(0);
    }

    class Node {
        int value;
        ArrayList<Node> next;
        //Node down, up, prev;

        public Node(int m, int h) { //two argument constructor for Node class
            this.value = m;
            next = new ArrayList<>(h);
            for (int i = 0; i < h; i++)
                next.add(null);
        }
    }

    Node top;
    List<Integer> count;

    public int Height() {

        int height = 0;
        double result = 0;
        double random;

        while (result < 0.25) {
            random = new Random().nextDouble();
            result = random;
            height++;
        }

        if (height > 10)
            return 10;
        else
            return height;
    }

    public int size() {
        return count.get(0);
    }

    public boolean Insert(int data) {
        List<Node> l = SkipSearch(data);

        if (l.get(0).next.get(0) == null || l.get(0).next.get(0).value > data) {
            int rise = Height();
            while (top.next.size() < rise) {
                top.next.add(null);
                l.add(top);
                count.add(0);
            }

            Node NewNode = new Node(data, rise);

            for (int h = 0; h < rise; h++) {
                count.set(h, count.get(h) + 1);
                Node PrevNode = l.get(h).next.get(h);
                l.get(h).next.set(h, NewNode);
                NewNode.next.set(h, PrevNode);
            }

            return true;
        } else
            return false;

    }

    public void Delete(int e) {

        List<Node> l = SkipSearch(e);
        Node n = l.get(0).next.get(0);

        if (n != null && n.value == e) {

            int rise = n.next.size();
            for (int h = 0; h < rise; h++) {
                l.get(h).next.set(h, n.next.get(h));
                count.set(h, count.get(h) - 1);
            }

            while (count.get(top.next.size() - 1) == 0 && top.next.size() >= 2) {
                top.next.remove(top.next.size() - 1);
                count.remove(count.size() - 1);
            }

        } else
            System.out.println("error");

    }

    public boolean Search(int i) {
        Node l = SkipSearch(i).get(0);
        return l.next.get(0) != null && l.next.get(0).value == i;
    }

    public List<Node> SkipSearch(int i) {
        List<Node> l = new ArrayList<Node>(top.next.size());

        for (int k = 0; k < top.next.size(); k++) {
            l.add(top);
        }

        Node n = top;
        int s = top.next.size() - 1;

        while (s > -1) {

            while (n.next.get(s) != null && n.next.get(s).value < i) {
                n = n.next.get(s);
            }

            l.set(s, n);
            s--;
        }

        return l;
    }

    public String Print() {

        ArrayList<Integer> l = new ArrayList<>();
        Node n = top.next.get(0);
        while (n != null) {
            l.add(n.value);
            n = n.next.get(0);
        }

        return l.toString().replace("[", "").replace("]", "").replace(",", "");
    }


    public static void main(String[] args) {

        SkipListMain s = new SkipListMain();
        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] l = input.split(" ");
            switch (l[0]) {

                case "Search": {
                    System.out.println(s.Search(Integer.valueOf(l[1])));
                    break;
                }

                case "Insert": {
                    s.Insert(Integer.valueOf(l[1]));
                    break;
                }

                case "Print": {
                    if (s.size() == 0)
                        System.out.println("empty");
                    else
                        System.out.println(s.Print());
                    break;
                }

                case "Delete": {
                    s.Delete(Integer.valueOf(l[1]));
                    break;
                }

            }
        }
    }
}