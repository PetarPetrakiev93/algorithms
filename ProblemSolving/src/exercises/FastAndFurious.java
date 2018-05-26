package exercises;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class FastAndFurious {
    static Map<String, List<String>> cameras = new HashMap<>();
    static Map<String, Road> roads = new HashMap<>();
    static Map<String, TreeSet<Car>> cars = new HashMap<>();
    static Map<String, Double> shortestTimes = new HashMap<>();
    static TreeSet<String> speedingCars = new TreeSet<>();
    static DateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

    public static void main(String[] args) throws ParseException {
        readInput();
        for (Map.Entry<String, TreeSet<Car>> car : cars.entrySet()) {
            List<Car> cameras = new ArrayList<>(car.getValue());
            for(int firstCamera = 0; firstCamera < cameras.size(); firstCamera++){
                for(int secondCamera = firstCamera + 1; secondCamera < cameras.size(); secondCamera++){
                    double secondRecord = cameras.get(secondCamera).recordingTime.getHours()
                            + cameras.get(secondCamera).recordingTime.getMinutes() / 60D
                            + cameras.get(secondCamera).recordingTime.getSeconds() / 360D;
                    double firstRecord = cameras.get(firstCamera).recordingTime.getHours()
                            + cameras.get(firstCamera).recordingTime.getMinutes() / 60D
                            + cameras.get(firstCamera).recordingTime.getSeconds() / 360D;
                    double time = secondRecord - firstRecord;
                    double shortestTime = getShortestTimeBetweenCameras(cameras.get(secondCamera).cameraName , cameras.get(firstCamera).cameraName);

                    if(shortestTime < Double.MAX_VALUE && !speedingCars.contains(car.getKey()) && time < shortestTime){
                        speedingCars.add(car.getKey());
                    }
                }
            }
        }

        for (String speedingCar : speedingCars) {
            System.out.println(speedingCar);
        }
    }

    private static double getShortestTimeBetweenCameras(String firstCamera, String secondCamera) {
        String key = firstCamera + "-" + secondCamera;
        if(shortestTimes.containsKey(key)){
            return shortestTimes.get(key);
        }

        List<String> start = cameras.get(firstCamera);
        HashMap<String, Double> times = new HashMap<>();
        List<String> otherCameras = new ArrayList<>();
        for (Map.Entry<String, List<String>> camera : cameras.entrySet()) {
            if(camera.getKey().equals(firstCamera)){
                times.put(camera.getKey(), Double.MIN_VALUE);
            }else{
                times.put(camera.getKey(), Double.MAX_VALUE);
            }
            otherCameras.add(camera.getKey());
        }

        while (otherCameras.size() != 0){
            String closestCamera = otherCameras.get(0);
            double closestCameraDistance = times.get(otherCameras.get(0));
            for (String otherCamera : otherCameras) {
                if(times.get(otherCamera) < closestCameraDistance){
                    closestCamera = otherCamera;
                    closestCameraDistance = times.get(otherCamera);
                }
            }

            otherCameras.remove(closestCamera);

            if(times.get(closestCamera) == Double.MAX_VALUE){
               break;
            }

            for (String neighbourCamera : cameras.get(closestCamera)) {
                double alternativeTime = times.get(closestCamera) + roads.get(neighbourCamera + "-" + closestCamera).minimumTime;
                if(alternativeTime < times.get(neighbourCamera)){
                    times.put(neighbourCamera, alternativeTime);
                }
            }
        }

        for (Map.Entry<String, Double> time : times.entrySet()) {
            String shortestTimeKey = firstCamera + "-" + time.getKey();
            if(!shortestTimes.containsKey(shortestTimeKey) || time.getValue() < shortestTimes.get(shortestTimeKey)){
                shortestTimes.put(firstCamera + "-" + time.getKey(), time.getValue());
                shortestTimes.put(time.getKey() + "-" + firstCamera, time.getValue());
            }
        }

        return times.get(secondCamera);
    }

    private static void readInput() throws ParseException {
        Scanner scr = new Scanner(System.in);
        scr.nextLine();
        String line;
        while (!(line=scr.nextLine()).equals("Records:")){
            String[] lineArgs = line.split(" ");
            if(!cameras.containsKey(lineArgs[0])){
                cameras.put(lineArgs[0], new ArrayList<>());
            }
            if(!cameras.containsKey(lineArgs[1])){
                cameras.put(lineArgs[1], new ArrayList<>());
            }

            cameras.get(lineArgs[0]).add(lineArgs[1]);
            cameras.get(lineArgs[1]).add(lineArgs[0]);

            Road road = new Road(lineArgs[0], lineArgs[1], Double.parseDouble(lineArgs[2]), Double.parseDouble(lineArgs[3]));
            roads.put(road.firstCameraName + "-" + road.secondCameraName, road);
            roads.put(road.secondCameraName + "-" + road.firstCameraName, road);
        }

        while (!(line=scr.nextLine()).equals("End")){
            String[] lineArgs = line.split(" ");
            if(!cars.containsKey(lineArgs[1])){
                cars.put(lineArgs[1], new TreeSet<>());
            }

            Car carInfo = new Car(lineArgs[0], df.parse(lineArgs[2]));
            cars.get(lineArgs[1]).add(carInfo);
        }
    }
}

class Road{
    String firstCameraName;
    String secondCameraName;
    Double distance;
    Double speedLimit;
    double minimumTime;

    public Road(String firstCameraName, String secondCameraName, Double distance, Double speedLimit) {
        this.firstCameraName = firstCameraName;
        this.secondCameraName = secondCameraName;
        this.distance = distance;
        this.speedLimit = speedLimit;
        this.minimumTime = distance/speedLimit;
    }
}

class Car implements Comparable<Car>{
    String cameraName;
    Date recordingTime;

    public Car(String cameraName, Date recordingTime) {
        this.cameraName = cameraName;
        this.recordingTime = recordingTime;
    }

    @Override
    public int compareTo(Car o) {
        return this.recordingTime.compareTo(o.recordingTime);
    }
}
