package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ParkingZones {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int n = Integer.parseInt(scr.nextLine());
        List<Zone> zones = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String lineArgs = scr.nextLine();
            String[] coordinates = lineArgs.split(": ")[1].split(", ");
            zones.add(new Zone(lineArgs.split(":")[0],
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]),
                    Integer.parseInt(coordinates[2]),
                    Integer.parseInt(coordinates[3]),
                    Double.parseDouble(coordinates[4])));
        }
        String[] spotsArgs = scr.nextLine().split("; ");
        List<Spot> spots = new ArrayList<>();
        for(int i = 0; i < spotsArgs.length; i++){
            Spot spot = new Spot(Integer.parseInt(spotsArgs[i].split(", ")[0]),
                    Integer.parseInt(spotsArgs[i].split(", ")[1]));
            for (Zone zone : zones) {
                if(zone.isInZone(spot.x, spot.y)){
                    spot.zone = zone;
                }
            }
            spots.add(spot);
        }
        spotsArgs = scr.nextLine().split(", ");
        int time = Integer.parseInt(scr.nextLine());
        Spot shop = new Spot(Integer.parseInt(spotsArgs[0]), Integer.parseInt(spotsArgs[1]));
        for (Spot spot : spots) {
            int distance = (Math.abs(spot.x-shop.x) + Math.abs(spot.y-shop.y)) - 1;
            spot.time = time * distance * 2;
            int roundedTime = spot.time % 60 != 0 ? spot.time + (60 - (spot.time % 60)): spot.time;
            spot.price = (roundedTime / 60) * spot.zone.price;
        }
        spots = spots.stream()
                .sorted((a, b) -> {
                    if(a.price .compareTo(b.price) == 0){
                        return a.time - b.time;
                    }
                    return a.price.compareTo(b.price);
                }).collect(Collectors.toList());
        Spot finalSpot = spots.get(0);
        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f%n",
                finalSpot.zone.name, finalSpot.x, finalSpot.y, finalSpot.price);
    }
}

class Spot{
    Zone zone;
    int x;
    int y;
    int time;
    Double price;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class Zone{
    String name;
    int x;
    int y;
    int width;
    int height;
    double price;

    public Zone(String name, int x, int y, int width, int height, double price) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.price = price;
    }

    public boolean isInZone(int xPosition, int yPosition){
        return (xPosition >= this.x && yPosition >= this.y
                && xPosition <= this.x + this.width && yPosition <= this.y + this.height);
    }
}