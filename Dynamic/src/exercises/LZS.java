package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LZS {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        String[] tokens = scr.nextLine().split("\\s+");
        int[] sequence = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            sequence[i] = Integer.parseInt(tokens[i]);
        }
        List<Integer> zigzag = findLongestZigZagSequence(sequence);
        System.out.println(String.join(" ", zigzag.stream().map(a -> String.valueOf(a)).collect(Collectors.toList())));
    }

    private static List<Integer> findLongestZigZagSequence(int[] sequence)
    {
        // creating two pairs of arrays so we can keep track of sequences with 1)odds 2)evens that are bigger
        // odds are bigger length and prev
        int[] lenOddIsBigger = new int[sequence.length];
        int[] prevOddIsBigger = new int[sequence.length];

        // evens are bigger length and prev
        int[] lenEvenIsBigger = new int[sequence.length];
        int[] prevEvenIsBigger = new int[sequence.length];

        int maxLen = 0;
        int maxPrev = -1;

        boolean isOddsBigger = false;

        for (int x = 0; x < sequence.length; x++){
            lenOddIsBigger[x] = 1;
            prevOddIsBigger[x] = -1;

            lenEvenIsBigger[x] = 1;
            prevEvenIsBigger[x] = -1;

            for (int i = 0; i < x; i++){
                // check sequence when odds have bigger values
                if (lenOddIsBigger[i] % 2 == 0){
                    if (sequence[x] > sequence[i] && lenOddIsBigger[x] <= lenOddIsBigger[i]){
                        lenOddIsBigger[x] = lenOddIsBigger[i] + 1;
                        prevOddIsBigger[x] = i;
                    }
                }else{
                    if (sequence[x] < sequence[i] && lenOddIsBigger[x] <= lenOddIsBigger[i]){
                        lenOddIsBigger[x] = lenOddIsBigger[i] + 1;
                        prevOddIsBigger[x] = i;
                    }
                }

                // check sequence when evens have bigger values
                if (lenEvenIsBigger[i] % 2 == 0){
                    if (sequence[x] < sequence[i] && lenEvenIsBigger[x] <= lenEvenIsBigger[i]){
                        lenEvenIsBigger[x] = lenEvenIsBigger[i] + 1;
                        prevEvenIsBigger[x] = i;
                    }
                }else {
                    if (sequence[x] > sequence[i] && lenEvenIsBigger[x] <= lenEvenIsBigger[i]){
                        lenEvenIsBigger[x] = lenEvenIsBigger[i] + 1;
                        prevEvenIsBigger[x] = i;
                    }
                }
            }

            if (lenOddIsBigger[x] > maxLen){
                maxLen = lenOddIsBigger[x];
                maxPrev = x;
                isOddsBigger = true;
            }

            if (lenEvenIsBigger[x] > maxLen){
                maxLen = lenEvenIsBigger[x];
                maxPrev = x;
                isOddsBigger = false;
            }
        }


        List<Integer> longesLzzs = new ArrayList<>();
        if (isOddsBigger){
            while (maxPrev != -1){
                longesLzzs.add(sequence[maxPrev]);
                maxPrev = prevOddIsBigger[maxPrev];
            }
        }else {
            while (maxPrev != -1){
                longesLzzs.add(sequence[maxPrev]);
                maxPrev = prevEvenIsBigger[maxPrev];
            }
        }

        Collections.reverse(longesLzzs);
        return longesLzzs;
    }

}
