import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1 {
    public static void main(String args[]) {
        if (isValid(args)) {
            List<Integer> path = getPath(args);
            printPath(path);
        }
    }

    // Data validation
    private static boolean isValid(String args[]) {

        return isArgsValid(args) && isInputDataValid(args);
    }

    // Numbers of arguments validation
    private static boolean isArgsValid(String args[]) {
        if (args.length < 2) {
            System.err.print("Incorrect input. 'n' and 'm' must be entered.");
            return false;
        }
        return true;
    }

    // Arguments validation
    private static boolean isInputDataValid(String args[]) {
        boolean isValid = false;

        Integer n = Integer.parseInt(args[0]);
        Integer m = Integer.parseInt(args[1]);

        if (n <= 0 || m <= 0) {
            System.err.print("Incorrect input. 'n' and 'm' must be greater then zero.");
        } else if (m > n) {
            System.err.print("Incorrect input. 'm' must be less then 'n'.");
        } else {
            isValid = true;
        }

        return isValid;
    }

    // Getting a path from intervals
    private static List<Integer> getPath(String args[]) {

        Integer n = Integer.parseInt(args[0]);
        Integer m = Integer.parseInt(args[1]);

        List<Integer> temp = new ArrayList<>();
        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;
        do {
            temp.add(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);

        return temp;
    }

    // Printing the resulting path
    private static void printPath(List<Integer> path) {
        for (int i : path) {
            System.out.print(i);
        }
        System.out.println();
    }
}
