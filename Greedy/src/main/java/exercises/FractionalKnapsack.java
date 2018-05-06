package exercises;

import java.util.*;
import java.util.stream.Collectors;

public class FractionalKnapsack {
    public static void main(String[] args) {
        class Item {
            private Integer price;
            private Integer weight;

            public Item(Integer price, Integer weight) {
                this.price = price;
                this.weight = weight;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getWeight() {
                return weight;
            }

            public void setWeight(Integer weight) {
                this.weight = weight;
            }
        }


        Scanner in = new Scanner(System.in);
        Integer capacity = Integer.parseInt(in.nextLine().substring(10));
        Integer itemsNumber = Integer.parseInt(in.nextLine().substring(7));
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < itemsNumber; i++) {
            String[] itemArgs = in.nextLine().split(" -> ");
            Item item = new Item(Integer.parseInt(itemArgs[0]), Integer.parseInt(itemArgs[1]));
            items.add(item);
        }
        Double totalPrice = 0.0D;

        items = items.stream().sorted(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getPrice() * 1D / o1.getWeight() < o2.getPrice() * 1D / o2.getWeight()) {
                    return 1;
                } else if (o1.getPrice() * 1D / o1.getWeight() > o2.getPrice() * 1D / o2.getWeight()) {
                    return -1;
                }
                return 0;
            }
        }).collect(Collectors.toList());

        int currentIndex = 0;
        while (capacity > 0 && currentIndex < items.size()) {
            Integer curItemPrice = items.get(currentIndex).getPrice();
            Integer curItemWeight = items.get(currentIndex).getWeight();

            Double quantityPercent = 1D;

            if (curItemWeight < capacity) {
                totalPrice += curItemPrice;
                capacity -= curItemWeight;
                System.out.printf("Take 100%% of item with price %.2f and weight %.2f%n", curItemPrice * 1D, curItemWeight * 1D);
            } else {
                quantityPercent = capacity * 1D / curItemWeight;
                totalPrice += quantityPercent * curItemPrice;
                capacity = 0;
                System.out.printf("Take %.2f%% of item with price %.2f and weight %.2f%n", quantityPercent * 100D, curItemPrice * 1D, curItemWeight * 1D);
            }
            currentIndex++;
        }
        System.out.printf("Total price: %.2f", totalPrice);
    }


}
