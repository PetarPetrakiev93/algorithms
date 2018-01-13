package lab;

public class InterpolationSearch {

    public static <T extends Comparable> int interpolationSearch(T[] arr, T key){
        int low = 0;
        int high = arr.length - 1;
        while (arr[low].compareTo(key) <= 0 && arr[high].compareTo(key) >= 0) {
            int mid = low + (((Integer) key - (Integer)arr[low]) * (high - low))
                    / ((Integer)arr[high] - (Integer)arr[low]);
            if (arr[mid].compareTo(key) < 0)
                low = mid + 1;
            else if (arr[mid].compareTo(key) > 0)
                high = mid - 1;
            else
                return mid;
        }
        if (arr[low] == key) return low;
        else return -1;
    }

    public static void main(String[] args){
        Integer[] collection = {3,2,6,9,7,3,-23,-45,65,39};
        QuickSort<Integer> mergeSort = new QuickSort<>();
        mergeSort.sort(collection);
        System.out.println(interpolationSearch(collection, 65));
        System.out.println(interpolationSearch(collection, 2));
    }
}
