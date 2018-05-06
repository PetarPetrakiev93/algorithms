package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EgyptianFractions {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] number = scr.nextLine().split("/");
        long numerator = Long.parseLong(number[0]);
        long denominator = Long.parseLong(number[1]);

        if(numerator / denominator >= 1){
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }

        System.out.printf("%d/%d = ", numerator, denominator);

        int index = 2;

        List<Integer> visitedIndexes = new ArrayList<>();

        while (numerator > 0){
            long nextNumerator = numerator * index;
            long indexNumerator = denominator;

            long remaining = nextNumerator - indexNumerator;

            if(remaining < 0){
                index++;
                continue;
            }

            visitedIndexes.add(index);

            numerator = remaining;
            denominator = denominator * index;
            index++;
        }

        String joinedIndex = visitedIndexes.stream()
                .map(a -> "1/" + a)
                .collect(Collectors.joining(" + "));
        System.out.print(joinedIndex + "\n");
    }
}
