
import java.util.Arrays;
import java.util.Scanner;


public class Sum {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int fact = Integer.parseInt(scr.nextLine());
        String[] in = scr.nextLine().split(" ");
        Integer[] numbers = Arrays.stream(in).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(sum(numbers, 0));
        System.out.println(factorial(fact));
    }

    public static int sum(Integer[] nums, int index){
        if(index == nums.length - 1){
            return nums[index];
        }

        return nums[index] + sum(nums, index+1);
    }

    public static int factorial(int factorial){
        if(factorial == 0){
            return 1;
        }

        return factorial * factorial(factorial-1);
    }
}
