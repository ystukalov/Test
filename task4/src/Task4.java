import java.io.*;
import java.util.*;
import java.util.List;

public class Task4 {

    public static void main(String[] args) throws IOException {

        BufferedReader numsReader = new BufferedReader(new FileReader(args[0]));
        List<Integer> nums = new ArrayList<>();
        String line;

        while ((line = numsReader.readLine()) != null) {
            nums.add(Integer.parseInt(line));
        }

        System.out.println(moveNumber(nums));

    }

    // Calculating the number of moves
    public static int moveNumber(List<Integer> nums) {

        Collections.sort(nums);
        int n = nums.size() / 2;
        int sum = 0;

        for (int i = 0; i < nums.size(); i++) {
            sum += Math.abs(nums.get(i) - nums.get(n));
        }

        return sum;

    }
}

