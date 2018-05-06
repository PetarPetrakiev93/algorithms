package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProcessorScheduling {

    public static void main(String[] args){

        Scanner scr = new Scanner(System.in);
        int numberOfTasks = Integer.parseInt(scr.nextLine().substring(7));
        List<Task> tasks = new ArrayList<>();
        for(int i = 0; i < numberOfTasks; i++){
            String[] taskArgs = scr.nextLine().split(" - ");
            tasks.add(new Task(i + 1, Integer.parseInt(taskArgs[0]), Integer.parseInt(taskArgs[1])));
        }

        tasks = tasks.stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .collect(Collectors.toList());

        int totalValue = 0;
        int index = 0;
        List<Task> scheduledTasks = new ArrayList<>();
        Deadline deadline = new Deadline();

        while (index < tasks.size()){
            Task task = tasks.get(index);
            if(checkTask(deadline, scheduledTasks, task)){
                totalValue += task.getValue();
                scheduledTasks.add(task);
            }
            index++;
        }

        scheduledTasks = scheduledTasks.stream()
                .sorted((a, b) -> {
                    if(a.getDeadline() == b.getDeadline()){
                        return b.getValue()-a.getValue();
                    }
                    return a.getDeadline() - b.getDeadline();
                })
                .collect(Collectors.toList());

        String joinedIndex = scheduledTasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining(" -> "));
        System.out.println("Optimal schedule: " + joinedIndex);
        System.out.println("Total value: " + totalValue);
    }

    private static boolean checkTask(Deadline deadline, List<Task> scheduledTasks, Task task) {
        if(deadline.getDeadline() >= task.getDeadline()){
            if(deadline.getDeadline()-scheduledTasks.size() <= 0){
                return false;
            }
        }

        if(deadline.getDeadline() < task.getDeadline()){
            deadline.setDeadline(task.getDeadline());
        }

        return true;
    }


}

class Task{
    private int index;
    private int value;
    private int deadline;

    Task(int index, int value, int deadline){
        this.index = index;
        this.value = value;
        this.deadline = deadline;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getIndex());
    }
}

class Deadline{
    private int deadline;

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
}