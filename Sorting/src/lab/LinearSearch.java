package lab;

public class LinearSearch<T extends Comparable> {

    public int search(T[] arr, T element){
        int index = 0;
        for(T e: arr){
            if(e.equals(element)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3,-23,-45,65,39};
        QuickSort<Integer> mergeSort = new QuickSort<>();
        mergeSort.sort(collection);
        LinearSearch<Integer> s = new LinearSearch<>();
        System.out.println(s.search(collection, -23));
    }
}
