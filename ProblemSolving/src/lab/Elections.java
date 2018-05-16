package lab;

import java.util.*;
import java.math.BigInteger;

public class Elections{
    public static void main(String []args){
        Scanner scr = new Scanner(System.in);
        int k = Integer.parseInt(scr.nextLine());
        int n = Integer.parseInt(scr.nextLine());
        int[] parties = new int[n];

        for(int i = 0; i < n; i++){
            parties[i] = Integer.parseInt(scr.nextLine());
        }

        BigInteger[] sums = new BigInteger[Arrays.stream(parties).sum() + 1];
        for(int big = 0; big < sums.length; big++){
            sums[big] = new BigInteger("0");
        }
        sums[0] = new BigInteger("1");

        for(int number : parties){
            for(int sum = sums.length-1; sum >= 0L; sum--){
                if(!sums[sum].toString().equals("0")){
                    sums[sum + number] = sums[sum + number].add(sums[sum]);
                }
            }
        }

        BigInteger counter = new BigInteger("0");
        for(int i = k; i < sums.length; i++){
            counter = counter.add(sums[i]);
        }

        System.out.println(counter);
    }
}