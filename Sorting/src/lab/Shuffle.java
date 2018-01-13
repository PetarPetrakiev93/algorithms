package lab;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Shuffle {
    public static <T> void shuffle(T[] arr){
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            int randIndex = random.nextInt(arr.length - i);

            swap(arr, i, randIndex);
        }
    }

    public static <T> void swap(T[] collection, int from, int to){
        T temp = collection[to];
        collection[to] = collection[from];
        collection[from] = temp;
    }

    public static void main(String[] args) {
        Integer[] collection = {1,2,3,4,5,6,7,8,9,10};
        shuffle(collection);
        System.out.println(Arrays.stream(collection).map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
