package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BestLecture {

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        int totalLectures = Integer.parseInt(scr.nextLine().substring(10));
        List<Lecture> allLectures = new ArrayList<>();
        for (int i = 0; i < totalLectures; i++){
            String[] lectureArgs = scr.nextLine().split(": ");
            allLectures.add(new Lecture(lectureArgs[0],
                    Integer.parseInt(lectureArgs[1].split(" - ")[0]),
                    Integer.parseInt(lectureArgs[1].split(" - ")[1])
                    ));
        }
        List<Lecture> selectedLectures = new ArrayList<>();
        allLectures = allLectures.stream().sorted((a,b) -> a.getEndTime() - b.getEndTime())
                .collect(Collectors.toList());

        selectedLectures.add(allLectures.get(0));
        for (Lecture lecture : allLectures) {
            if(lecture.getStartTime() > selectedLectures.get(selectedLectures.size()-1).getEndTime()){
                selectedLectures.add(lecture);
            }
        }

        System.out.printf("Lectures (%d):%n", selectedLectures.size());
        for (Lecture selectedLecture : selectedLectures) {
            System.out.printf("%d-%d -> %s%n", selectedLecture.getStartTime(), selectedLecture.getEndTime(), selectedLecture.getName());
        }
    }
}

class Lecture{
    private String name;
    private int startTime;
    private int endTime;

    public Lecture(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
