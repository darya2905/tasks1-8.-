import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter numbers:");
        String[] nums = scan.nextLine().split("");

        System.out.println("Enter S:");
        int S = scan.nextInt();

        TreeSet<Integer> treeSet = new TreeSet<>();
        List<String> pairs = new ArrayList<>();

        for (String s : nums) {
            treeSet.add(Integer.parseInt(s));
        }

        for (int x : treeSet) {
            int y = S - x;

            if (y < x) {
                continue;
            }

            if (treeSet.contains(y)) {
                if (x == y) {
                    int count = 0;
                    for (String s : nums) {
                        if (Integer.parseInt(s) == x) {
                            count++;
                        }
                    }
                    if (count >= 2) {
                        pairs.add("(" + x + ", " + y + ")");
                    }
                } else {
                    pairs.add("(" + x + ", " + y + ")");
                }
            }
        }

        if (pairs.isEmpty()) {
            System.out.println("There are no pairs");
        } else {
            System.out.println("Pairs:");
            for (String p : pairs) {
                System.out.println(p);
            }
        }

        scan.close();
    }
}