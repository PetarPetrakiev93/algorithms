import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Knapsack {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int capacity = Integer.parseInt(scr.nextLine());
        List<Item> items = new ArrayList<>();
        String line = scr.nextLine();
        while (!line.equals("end")){
            String[] itemTokens = line.split("\\s");
            items.add(new Item(itemTokens[0],
                    Integer.parseInt(itemTokens[1]),
                    Integer.parseInt(itemTokens[2])));
            line = scr.nextLine();
        }
        List<Item> selected = fillKnapsack(items, capacity);

        int weight = selected.stream().map((item -> item.getWeight()))
                .reduce((a, b) -> a+b).get();
        int price = selected.stream().map((item -> item.getPrice()))
                .reduce((a, b) -> a+b).get();

        selected = selected.stream().sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());

        System.out.println("Total Weight: " + weight);
        System.out.println("Total Value: " + price);

        for (Item item : selected) {
            System.out.println(item.getName());
        }
    }

    private static List<Item> fillKnapsack(List<Item> items, int capacity){
        int[][] maxPrice = new int[items.size()+1][capacity + 1];
        boolean[][] isItemTaken = new boolean[items.size()+1][capacity + 1];

        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            int rowIndex = i + 1;
            for(int c = 0; c <= capacity; c++){
                if(item.getWeight() > c){
                    continue;
                }

                int excluding = maxPrice[rowIndex-1][c];
                int including = item.getPrice() + maxPrice[rowIndex-1][c - item.getWeight()];

                if(including > excluding){
                    maxPrice[rowIndex][c] = including;
                    isItemTaken[rowIndex][c] = true;
                }else{
                    maxPrice[rowIndex][c] = excluding;
                }
            }
        }
        List<Item> selected = new ArrayList<>();
        int currentCapacity = capacity;
        int itemIndex = items.size() - 1;
        while (itemIndex >= 0){
            if(currentCapacity <= 0){
                break;
            }
            if(isItemTaken[itemIndex+1][currentCapacity]){
                Item currentItem = items.get(itemIndex);
                selected.add(currentItem);
                currentCapacity -= currentItem.getWeight();
            }

            itemIndex--;
        }
        return selected;
    }

}

class Item{
    private String name;
    private int weight;
    private int price;

    public Item(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}