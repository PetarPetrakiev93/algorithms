package judge;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Words {
    static int count;
    static char[] symbols;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        symbols = br.readLine().toCharArray();
        boolean isValid = true;
        HashSet<Character> elements = new HashSet<>();
        for(char c: symbols){
            if(elements.contains(c)){
                isValid = false;
                break;
            }
            elements.add(c);
        }
        if(!isValid) {
            GeneratePermutations(0);
            System.out.println(count);
        }else{
            long factor = 1;
            for(int i = 1; i <= symbols.length; i++){
                factor = factor * i;
            }
            System.out.println(factor);
        }
    }

    private static void GeneratePermutations(int index){
        if(index == symbols.length){
            for(int i = 0; i < symbols.length - 1; i++){
                if(symbols[i] == symbols[i+1]){
                    return;
                }
            }
            count++;
        }
        HashSet<Character> swapped = new HashSet<>();
        for(int i = index; i < symbols.length; i++){
            if(!swapped.contains(symbols[i])){
                char currentSymbol = symbols[index];
                symbols[index] = symbols[i];
                symbols[i] = currentSymbol;

                GeneratePermutations(index + 1);
                swapped.add(symbols[index]);

                symbols[i] = symbols[index];
                symbols[index] = currentSymbol;
            }
        }
    }
}
