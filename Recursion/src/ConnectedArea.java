import java.util.*;
import java.util.stream.Collectors;


public class ConnectedArea {
    static int rows;
    static int cols;
    static String[][] matrix;
    static LinkedList<Integer[]> list;
    static int size;
    static int areaNumber = 1;

    public static void main(String[] args){
        list = new LinkedList<>();
        readMatrix();
        findArea();
         printMap();
    }

    private static void printMap() {
        System.out.printf("Total areas found: %d\n", list.size());

        list.sort((e1, e2) -> {
            if(e1[2].equals(e2[2])){
                if(e1[0].equals(e2[0])){
                    return e1[1].compareTo(e2[1]);
                }else{
                    return e1[0].compareTo(e2[0]);
                }
            }else{
                return e2[2].compareTo(e1[2]);
            }
        });

        int index = 1;
        for(Integer[] args : list){
            System.out.printf("Area #%d at (%d, %d), size: %d\n", index, args[0], args[1], args[2]);
            index++;
        }
    }



    private static void readMatrix(){
        Scanner scr = new Scanner(System.in);
        rows = Integer.parseInt(scr.nextLine());
        cols = Integer.parseInt(scr.nextLine());
        matrix = new String[rows][cols];
        for(int i = 0; i < rows; i++){
            String line = scr.nextLine();
            for(int j = 0; j < cols; j++){
                matrix[i][j] = String.valueOf(line.charAt(j));
            }
        }
    }

    private static void findArea(){
        size = 1;
        boolean isFound = false;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j].equals("-")){
                    isFound = true;
                    findPath(i, j);
                }
            }
        }
        if(isFound){
            findArea();
        }
    }

    private static void findPath(int row, int col) {
        matrix[row][col] = String.valueOf(areaNumber);
        findPathAdj(row, col - 1);
        findPathAdj(row, col + 1);
        findPathAdj(row - 1, col);
        findPathAdj(row + 1, col);
        Integer[] coord = new Integer[3];
        coord[0] = row;
        coord[1] = col;
        coord[2] = size;
        list.push(coord);
        size = 1;
        areaNumber++;
    }

    private static void findPathAdj(int row, int col) {
        if(!isInBounds(row, col)) {
            return;
        }
        //System.out.println(matrix[row][col]);
        if(matrix[row][col].equals(String.valueOf(areaNumber))
                || matrix[row][col].equals("*")){
            return;
        }else{
            matrix[row][col] = String.valueOf(areaNumber);
            findPathAdj(row, col - 1);
            findPathAdj(row, col + 1);
            findPathAdj(row - 1, col);
            findPathAdj(row + 1, col);
            size++;
        }
    }

    private static boolean isInBounds(int row, int col) {
        if(row < rows && row >= 0 && col < cols && col >= 0){
            return true;
        }
        return false;
    }
}
