package lab;

import java.util.Scanner;

public class CutRob {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String[] priceArgs = scr.nextLine().split("\\s+");
        int[] price = new int[priceArgs.length];
        for(int i = 0; i < priceArgs.length; i++){
            price[i] = Integer.parseInt(priceArgs[i]);
        }
        int cuts = Integer.parseInt(scr.nextLine());
        Rod rod = new Rod(new int[price.length], price, new int[price.length]);
        System.out.println(cutRod(rod, cuts));

        while (cuts - rod.bestCombo[cuts] != 0)
        {
            System.out.print(rod.bestCombo[cuts] + " ");
            cuts = cuts - rod.bestCombo[cuts];
        }

        System.out.print(rod.bestCombo[cuts]);

    }

    public static int cutRod(Rod rod, int n){
        for (int i = 1; i <= n; i++) {
            int currentBest = rod.bestPrice[i];
            for (int j = 1; j <= i; j++) {
                currentBest =
                        Math.max(rod.bestPrice[i], rod.price[j] + rod.bestPrice[i - j]);
                if (currentBest > rod.bestPrice[i]) {
                    rod.bestPrice[i] = currentBest;
                    rod.bestCombo[i] = j;
                }
            }
        }
        return rod.bestPrice[n];
    }


}

class Rod{
    int[] bestPrice;
    int[] price;
    int[] bestCombo;

    public Rod(int[] bestPrice, int[] price, int[] bestCombo) {
        this.bestPrice = bestPrice;
        this.price = price;
        this.bestCombo = bestCombo;
    }
}
