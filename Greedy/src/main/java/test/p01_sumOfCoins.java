package test;

import java.util.*;

public class  p01_sumOfCoins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        try {
            Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
            System.out.println("Number of coins to take: " + usedCoins.size());
            for (Integer integer : usedCoins.keySet()) {
                System.out.println(usedCoins.get(integer) + " coin(s) with value " + integer);
            }
        }catch (IllegalArgumentException e){
            System.out.println("Error");
        }

    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Integer[] temp = Arrays.stream(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(temp, Collections.reverseOrder());
        Map<Integer, Integer> stackOfCoins = new LinkedHashMap<>();

        int coinIndex = 0;
        int currentSum = 0;
        while (coinIndex < coins.length && currentSum != targetSum) {
            if(temp[coinIndex]+currentSum > targetSum){
                coinIndex++;
                continue;
            }
            int remainingSum = targetSum - currentSum;
            int coinsToTake = remainingSum / temp[coinIndex];

            if(coinsToTake > 0){
                stackOfCoins.put(temp[coinIndex], coinsToTake);

                currentSum = currentSum + (coinsToTake * temp[coinIndex]);
            }
        }

        if(currentSum != targetSum){
            throw new IllegalArgumentException();
        }

        return stackOfCoins;
    }
}
