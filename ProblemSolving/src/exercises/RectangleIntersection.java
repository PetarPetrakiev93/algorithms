package exercises;

import java.util.*;
import java.util.stream.Stream;

public class RectangleIntersection {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        Rectangle[] rectangles = new Rectangle[n];
        for(int i = 0; i < n; i++){
            String[] lineArgs = scr.nextLine().split("\\s+");
            rectangles[i] = new Rectangle(Integer.parseInt(lineArgs[0]),
                    Integer.parseInt(lineArgs[1]),
                    Integer.parseInt(lineArgs[2]),
                    Integer.parseInt(lineArgs[3]));
        }
        List<Integer> xCoordinates = new ArrayList<>();
        Stream<Integer> streamMaxX = Arrays.stream(rectangles).map(a -> a.maxX);
        Stream<Integer> streamMinX = Arrays.stream(rectangles).map(a -> a.minX);
        Stream.concat(streamMaxX, streamMinX)
                .distinct()
                .forEach(a -> {
                        xCoordinates.add(a);
                        xCoordinates.add(a);
                });
        Collections.sort(xCoordinates);
        List<Rectangle>[] xRect = getIntersectingRectanglesByX(rectangles, xCoordinates);
        long totalArea = 0;
        for(int i = 0; i < xRect.length; i++){
            List<Rectangle> currentXRect = xRect[i];
            if(currentXRect.size() < 2){
                continue;
            }
            List<Integer> yCoordinates = new ArrayList<>();
            Stream<Integer> streamMaxY = Arrays.stream(rectangles).map(a -> a.maxY);
            Stream<Integer> streamMinY = Arrays.stream(rectangles).map(a -> a.minY);
            Stream.concat(streamMaxY, streamMinY)
                    .distinct()
                    .forEach(a -> {
                        yCoordinates.add(a);
                        yCoordinates.add(a);
                    });
            Collections.sort(yCoordinates);
            int[] intersectingCount = calcIntersectingCountByY(currentXRect, yCoordinates);

            for(int k = 0; k < intersectingCount.length; k++){
                if(intersectingCount[k] < 2){
                    continue;
                }
                Rectangle intersection = new Rectangle(xCoordinates.get(i),
                        xCoordinates.get(i+1),
                        yCoordinates.get(k),
                        yCoordinates.get(k+1));

                totalArea += intersection.area();
            }
        }

        System.out.println(totalArea);
    }

    private static int[] calcIntersectingCountByY(List<Rectangle> currentXRect, List<Integer> yCoordinates) {
        int[] intersectingCount = new int[yCoordinates.size() - 1];
        for (int y = 0; y < yCoordinates.size(); y++) {
            for (Rectangle rectangle : currentXRect) {
                if (rectangle.maxY > yCoordinates.get(y)
                        && rectangle.minY < yCoordinates.get(y + 1)){
                    intersectingCount[y]++;
                }
            }
        }
        return intersectingCount;

    }

    private static List<Rectangle>[] getIntersectingRectanglesByX(Rectangle[] rectangles, List<Integer> xCoordinates) {
        List<Rectangle>[] xRect = new ArrayList[xCoordinates.size()-1];
        for(int i = 0; i < xRect.length; i++){
            xRect[i] = new ArrayList<>();
            for (Rectangle rectangle : rectangles) {
                if(rectangle.minX < xCoordinates.get(i+1)
                        && rectangle.maxX > xCoordinates.get(i)){
                    xRect[i].add(rectangle);
                }
            }
        }
        return xRect;
    }
}

class Rectangle{
    int minX;
    int maxX;
    int minY;
    int maxY;

    public Rectangle(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int area(){
        return Math.abs((this.maxX - this.minX) * (this.maxY - this.minY));
    }
}
