package lab;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ShortestPath {
    public static char[] directions = new char[]{'L', 'R', 'S'};
    public static int k;
    public static int variIndex[];
    public static char[] vari;
    public static Set<String> paths;
    public static String path;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        path = scr.nextLine();
        if(!path.contains("*")){
            System.out.println(1);
            System.out.println(path);
        }else{
            paths = new LinkedHashSet<>();
            k = path.length() - path.replaceAll("\\*", "").length();
            variIndex = new int[k];
            vari = new char[k];
            int currentIndex = 0;
            for(int i = 0; i < k; i++){
                int index = path.indexOf('*', currentIndex);
                variIndex[i] = index;
                currentIndex = index+1;
            }
            gen(0);
            System.out.println(paths.size());
            for (String s : paths) {
                System.out.println(s);
            }
        }

    }

    public static void gen(int index) {
        if (index >= k) {
            addPath();
        }else {
            for (int i = 0; i < directions.length; i++) {
                vari[index] = directions[i];
                gen(index + 1);
            }
        }
    }

    private static void addPath() {
        StringBuilder newPath = new StringBuilder(path);
        for(int i = 0; i < k; i++){
            newPath.setCharAt(variIndex[i], vari[i]);
        }
        paths.add(newPath.toString());
    }
}
