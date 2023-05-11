import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Second {
    static void main(){
        System.out.println("Second task");
        HashSet<Integer> set1 = new HashSet<>(array());
        HashSet<Integer> set2 = new HashSet<>(array());
        HashSet<Integer> intersectionSet = intersection(set1, set2);
        HashSet<Integer> unionSet = union(set1, set2);

        System.out.println("Set 1: ");
        System.out.println(set1);
        System.out.println("Set 2: ");
        System.out.println(set2);
        System.out.println("Intersection");
        System.out.println(intersectionSet);
        System.out.println("Union ");
        System.out.println(unionSet);
    }

    public static ArrayList<Integer> array(){
        Random random = new Random();
        ArrayList<Integer> array = new ArrayList();
        for (int i = 0; i < 15; i++) {
            array.add(random.nextInt(101));
        }
        return array;
    }
    public static HashSet<Integer> intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }

    public static HashSet<Integer> union(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }
}
