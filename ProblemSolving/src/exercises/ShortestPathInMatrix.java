package exercises;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathInMatrix {
    //static Map<Cell, List<Cell>> graph;
    //static int[][] graphMatrix;

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int rows = Integer.parseInt(scr.nextLine());
        int cols = Integer.parseInt(scr.nextLine());
        int[][] graphMatrix = new int[rows][cols];

        for(int row = 0; row < rows; row++){
            String[] lineArgs = scr.nextLine().split("\\s+");
            for(int col = 0; col< cols; col++){
                graphMatrix[row][col] = Integer.parseInt(lineArgs[col]);
            }
        }
        Map<Cell, List<Cell>> graph = buildGraph(graphMatrix);
        List<Integer> path = dijkstraAlgorithm(graphMatrix, graph);
        int length = path.stream().reduce((a, b) -> a + b).get();
        System.out.println("Length: " + length);
        System.out.println("Path: " + String.join(" ", path.stream().map(a -> a + "").collect(Collectors.toList())));
    }

    private static List<Integer> dijkstraAlgorithm(int[][] graphMatrix, Map<Cell, List<Cell>> graph) {
        Queue<Cell> q = new PriorityQueue<>((e1, e2) -> e1.compareTo(e2));
        Map<Cell, Integer> dist = new HashMap<>();
        Map<Cell, Cell> previous = new HashMap<>();
        for (Cell cell : graph.keySet()) {
            if(cell.col == 0 && cell.row == 0){
                dist.put(cell, 0);
                q.add(cell);
            }else{
                dist.put(cell, Integer.MAX_VALUE);
            }
            previous.put(cell, null);
        }

        while(q.size() > 0) {
            Cell cell = q.poll();

            if(cell.row == graphMatrix.length-1 && cell.col == graphMatrix[0].length-1){
                break;
            }

            List<Cell> connectedCells = graph.get(cell);
            for (Cell neighbour : connectedCells) {
                if(cell.equals(neighbour)){
                    continue;
                }
                int alt =cell.value + neighbour.value;
                if(alt < dist.get(neighbour)){
                    neighbour.value = alt;
                    dist.put(neighbour, alt);
                    previous.put(neighbour, cell);
                    if(q.size() > 0) {
                        q.add(q.poll());
                    }
                    if(!q.contains(neighbour)){
                         q.add(neighbour);
                    }else {
                        for (Cell c : q) {
                            if(c.equals(neighbour)){
                                c.value = alt;
                            }
                        }
                    }
                }
            }

        }
        List<Integer> path = new ArrayList<>();
        int currentRow = graphMatrix.length -1;
        int currentCol = graphMatrix[0].length -1;
        Cell currentCell = null;
        for (Map.Entry<Cell, Cell> cellCellEntry : previous.entrySet()) {
            if(cellCellEntry.getKey().row == currentRow
                    && cellCellEntry.getKey().col == currentCol){
                path.add(cellCellEntry.getKey().value);
                currentCell = cellCellEntry.getValue();
            }
        }
        while (currentCell != null){
            if(previous.containsKey(currentCell)){
                for (Map.Entry<Cell, Cell> cellCellEntry : previous.entrySet()) {
                    if(cellCellEntry.getKey().row == currentCell.row
                            && cellCellEntry.getKey().col == currentCell.col){
                        path.add(cellCellEntry.getKey().value);
                    }
                }
                currentCell = previous.get(currentCell);
            }
        }
        Collections.reverse(path);
        return path;
    }

    private static Map<Cell, List<Cell>> buildGraph(int[][] graphMatrix) {
        Map<Cell, List<Cell>> graph = new HashMap<>();
        for (int row = 0; row < graphMatrix.length; row++) {
            for (int col = 0; col < graphMatrix[0].length; col++) {
                 Cell cell = new Cell(row, col, graphMatrix[row][col]);
                 List<Cell> neighbours = new ArrayList<>();
                 if(isInMatrix(row -1, col, graphMatrix)){
                    neighbours.add(new Cell(row-1, col, graphMatrix[row-1][col]));
                 }
                 if(isInMatrix(row, col-1, graphMatrix)){
                     neighbours.add(new Cell(row, col-1, graphMatrix[row][col-1]));
                 }
                 if(isInMatrix(row, col+1, graphMatrix)){
                     neighbours.add(new Cell(row, col+1, graphMatrix[row][col+1]));
                 }
                 if(isInMatrix(row+1, col, graphMatrix)){
                     neighbours.add(new Cell(row+1, col, graphMatrix[row+1][col]));
                 }
                 graph.put(cell, neighbours);
            }
        }

        return graph;
    }

    private static boolean isInMatrix(int row, int col, int[][] matrix){
        return (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length);
    }

}

class Cell implements Comparable<Cell>{
    public int row;
    public int col;
    public Integer value;

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    @Override
    public int compareTo(Cell c) {
        return this.value.compareTo(c.value);
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return 2*this.row * 2*this.col;
    }
}
