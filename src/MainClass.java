import java.util.HashMap;

public class MainClass {
    public static void main(String[] args) {
        int x[] = {1, 2, 3, 4, 5};
        int y[] = x.clone();
        x[0] = 5;
        x[1] = 4;
        for (int value : y) {
            System.out.println(value);
        }

        HashMap<Integer, Integer> age = new HashMap<>();
    }
}
