import java.util.Scanner;

public class Accounting {
    static int adama = 0;

    static void give(int from, int to, int money, int[][] people) {
        for (int i = 0; i < adama; i++)
            for (int j = 0; j < adama; j++)
                if (from == i && to == j) {
                    people[i][j] += money;
                    people[j][i] -= money;
                }
    }

    static void profit(int[][] people, String adam[]) {
        int temp1 = 0, temp2 = 0, k = 0, s = 0;
        String stringTemp;
        boolean flag = true;
        String profPersons[] = new String[adama];
        for (int j = 0; j < adama; j++) {
            for (int i = 0; i < adama; i++)
                temp1 += people[i][j];

            if (temp1 > temp2)
                temp2 = temp1;

            temp1 = 0;
        }

        for (int j = 0; j < adama; j++) {
            for (int i = 0; i < adama; i++)
                temp1 += people[i][j];

            if (temp1 == temp2) {
                profPersons[k] = adam[j];
                k++;
            }
            temp1 = 0;
        }

        if (temp2 == 0)
            System.out.println("-1");

        else if (k == 1)
            System.out.println(profPersons[0]);

        else {
            while (flag) {
                flag = false;
                for (s = k - 1; s > 0; s--)
                    if (profPersons[s - 1].compareTo(profPersons[s]) > 0) {
                        stringTemp = profPersons[s - 1];
                        profPersons[s - 1] = profPersons[s];
                        profPersons[s] = stringTemp;
                        flag = true;
                    }
            }
            System.out.println(profPersons[0]);
        }
    }

    static void loss(int[][] people, String adam[]) {
        int temp1 = 0, temp2 = 0, k = 0, s = 0;
        String stringTemp;
        boolean flag = true;
        String lossPerson[] = new String[adama];
        for (int i = 0; i < adama; i++) {
            for (int j = 0; j < adama; j++)
                temp1 += people[i][j];

            if (temp1 > temp2)
                temp2 = temp1;

            temp1 = 0;
        }
        for (int i = 0; i < adama; i++) {
            for (int j = 0; j < adama; j++)
                temp1 += people[i][j];

            if (temp1 == temp2) {
                lossPerson[k] = adam[i];
                k++;
            }
            temp1 = 0;
        }

        if (temp2 == 0)
            System.out.println(-1);

        else if (k == 1)
            System.out.println(lossPerson[0]);

        else {
            while (flag) {
                flag = false;
                for (s = k - 1; s > 0; s--)
                    if (lossPerson[s - 1].compareTo(lossPerson[s]) > 0) {
                        stringTemp = lossPerson[s - 1];
                        lossPerson[s - 1] = lossPerson[s];
                        lossPerson[s] = stringTemp;
                        flag = true;
                    }
            }
            System.out.println(lossPerson[0]);
        }
    }


    static void bedehkars(int[][] people, String adam[], String person) {
        int myPerson = 0, sum = 0;
        for (int i = 0; i < adama; i++)
            if (adam[i].equals(person)) {
                myPerson = i;
                break;
            }

        for (int i = 0; i < adama; i++)
            if (people[i][myPerson] > 0)
                sum++;

        System.out.println(sum);
    }

    static void talabkars(int[][] people, String adam[], String person) {
        int myPerson = 0, sum = 0;
        for (int i = 0; i < adama; i++)
            if (adam[i].equals(person)) {
                myPerson = i;
                break;
            }

        for (int i = 0; i < adama; i++)
            if (people[myPerson][i] > 0)
                sum++;

        System.out.println(sum);
    }

    static void debt(int[][] people, String adam[], String from, String to) {
        int fromPerson = 0, toPerson = 0, temp = 0;
        float res = 0;
        for (int i = 0; i < adama; i++) {
            if (adam[i].equals(from)) {
                fromPerson = i;
                temp++;
            }

            if (adam[i].equals(to)) {
                toPerson = i;
                temp++;
            }

            if (temp == 2)
                break;
        }


        res = people[toPerson][fromPerson];
        System.out.printf("%.2f \n", res / 100);

    }

    /*static int compare(String s1, String s2) {

        for (int i = 0; i < min(s1.length(), s2.length()); i++) {
            int ascii1 = 0;
            int ascii2 = 0;

            ascii1 = (int) s1.charAt(i);
            ascii2 = (int) s2.charAt(i);

            if (ascii1 > ascii2)
                return -1;

            else if (ascii1 < ascii2)
                return 1;

            else if (ascii1 == ascii2) {
                if (s1.length() == i + 1 && s2.length() > i + 1)
                    return 1;
                else if (s2.length() == i + 1 && s1.length() > i + 1)
                    return -1;
                else continue;
            }

        }
        return 1;
    }*/

    public static void main(String[] args) {

        int m, temp = -1, temp1 = 0, temp2 = 0;
        Boolean flag = true;
        int[][] people = new int [1500][1500];
        String[] adam = new String[1500];

        /*for (int i = 0; i < 1500; i++) {
            adam[i] = null;
        }

        for (int i = 0; i < 1500; i++) {
            for (int j = 0; j < 1500; j++) {
                people[i][j] = 0;
            }
        }*/

        Scanner scan = new Scanner(System.in);
        m = scan.nextInt();

        while (temp < m) {
            String str = scan.nextLine();
            String[] strArr = str.split(" ");
            switch (strArr[0]) {
                case ("1"):
                    if (adama == 0)
                        adam[0] = strArr[1];
                    else
                        for (int i = 0; i < adama; i++)
                            if (adam[i].equals(strArr[1])) {
                                temp1 = i;
                                flag = false;
                                break;
                            }
                    if (flag) {
                        adam[adama] = strArr[1];
                        temp1 = adama;
                        adama++;
                    }

                    flag = true;
                    for (int i = 0; i < adama; i++)
                        if (adam[i].equals(strArr[2])) {
                            temp2 = i;
                            flag = false;
                            break;
                        }
                    if (flag) {
                        adam[adama] = strArr[2];
                        temp2 = adama;
                        adama++;
                    }

                    flag = true;
                    give(temp1, temp2, Math.round(Float.parseFloat(strArr[3]) * 100), people);

                    break;

                case ("2"):
                    profit(people, adam);
                    break;

                case ("3"):
                    loss(people, adam);
                    break;

                case ("4"):
                    bedehkars(people, adam, strArr[1]);
                    break;

                case ("5"):
                    talabkars(people, adam, strArr[1]);
                    break;

                case ("6"):
                    debt(people, adam, strArr[1], strArr[2]);
                    break;
            }
            temp++;
        }
    }
}