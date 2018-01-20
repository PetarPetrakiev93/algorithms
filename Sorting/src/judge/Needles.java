package judge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Needles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputArgs = br.readLine();
        int n = Integer.parseInt(inputArgs.split(" ")[0]);
        int c = Integer.parseInt(inputArgs.split(" ")[1]);
        String[] arguments = br.readLine().split(" ");
        ArrayList<Integer> elements = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        int prev = -1;
        for(int i = 0; i < arguments.length; i++){
            if(prev <= Integer.parseInt(arguments[i]) && Integer.parseInt(arguments[i]) != 0){
                elements.add(Integer.parseInt(arguments[i]));
                prev = Integer.parseInt(arguments[i]);
                indexes.add(i);
            }
        }
        arguments = br.readLine().split(" ");

        for(int i = 0; i < c; i++){
            System.out.print(binarySearchModified(elements, indexes, Integer.parseInt(arguments[i]),0,elements.size()-1) + " ");
        }
    }

    public static int binarySearchModified(List<Integer> elements, List<Integer> indexes, int key, int start, int end){
        if (end < start)
            return 0;
        else {
            int mid = (start + end) / 2;
            if(mid >= elements.size()-1 && elements.get(mid) < key){
                return indexes.get(mid) + 1;
            }
            if(mid <= 0 && elements.get(mid) > key){
                return indexes.get(mid);
            }
            if(elements.get(mid)==key && mid != 0){
                while(elements.get(mid) == key && mid != 0){
                    mid--;
                }
                return indexes.get(mid) + 1;
            }
            if(elements.get(mid)<key && elements.get(mid+1)>key){
                return indexes.get(mid)+1;
            }
            if (elements.get(mid) > key)
                return binarySearchModified(elements, indexes, key, start, mid - 1);
            else if (elements.get(mid) < key)
                return binarySearchModified(elements, indexes, key, mid + 1, end);
            else
                return indexes.get(mid);

        }
    }
}