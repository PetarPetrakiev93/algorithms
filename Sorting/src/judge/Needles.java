package judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Needles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputArgs = br.readLine();
        int n = Integer.parseInt(inputArgs.split(" ")[0]);
        int c = Integer.parseInt(inputArgs.split(" ")[1]);
        String[] arguments = br.readLine().split(" ");
        Integer[] arr = new Integer[arguments.length];
        for(int i = 0; i < arguments.length; i++){
            arr[i] = Integer.parseInt(arguments[i]);
        }
        arguments = br.readLine().split(" ");
        Integer[] elements = new Integer[arguments.length];
        for(int i = 0; i < arguments.length; i++){
            elements[i] = Integer.parseInt(arguments[i]);
        }
        for(int i = 0; i < c; i++){
            System.out.print(binarySearchIterative(arr, elements[i], 0, arr.length-1) + " ");
        }
    }

    public static <T extends Comparable> int binarySearchIterative(T[] arr, T key, int start, int end){
        while (end >= start) {
            int mid = (start + end) / 2;
            if(arr[mid].compareTo(0) == 0){
                int prev = mid-1;
                int next = mid+1;
                while (prev >= 0 && arr[prev].compareTo(0) == 0){
                    prev--;
                }
                while (next < arr.length && arr[next].compareTo(0) == 0){
                    next++;
                }
                if(prev == 0 && arr[prev].compareTo(key) >= 0){
                    return 0;
                }
                if(next == arr.length-1 && arr[next].compareTo(key) < 0){
                    return arr.length;
                }
                if (arr[prev].compareTo(key) < 0) {
                    start = prev;
                }
                else if (arr[prev].compareTo(key) > 0) {
                    end = prev;
                }
                if(arr[prev].compareTo(key) < 0 && arr[next].compareTo(key) >= 0){
                    return prev+1;
                }

            }else{
                if (arr[mid].compareTo(key) < 0) {
                    start = mid + 1;
                }
                else if (arr[mid].compareTo(key) > 0) {
                    end = mid - 1;
                }
                if(mid == 0 && arr[mid].compareTo(key) >= 0){
                    return 0;
                }
                if(mid == arr.length-1 && arr[mid].compareTo(key) < 0){
                    return arr.length;
                }
                if(mid == 0){
                    return 0;
                }
                if(arr[mid].compareTo(key) == 0){
                    int temp = mid -1;
                    while(arr[temp].compareTo(key)>=0 || arr[temp].compareTo(0) == 0){
                        if(temp == 0){
                            break;
                        }
                        temp--;
                    }
                    return temp+1;
                }
                if(arr[mid-1].compareTo(0) ==0){
                    continue;
                }
                if(arr[mid-1].compareTo(key) < 0 && arr[mid].compareTo(key) >= 0){
                    return mid;
                }

            }

        }
        return -1;
    }
}
