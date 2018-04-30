import java.util.HashSet;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Cubes{
    private int[] sticks;
    private HashSet<String> cubes;
    public int count;

    public static void main(String []args){
        Cubes cubes = new Cubes();
        cubes.loadArgs();
        cubes.per(0);
        System.out.println(cubes.count);
    }


    public void loadArgs(){
        this.cubes = new HashSet<>();
        Scanner scr = new Scanner(System.in);
        String[] args = scr.nextLine().split("\\s+");
        this.sticks = new int[args.length];
        for(int i = 0; i < args.length; i++){
            this.sticks[i] = Integer.parseInt(args[i]);
        }
    }

    public void per(int index) {
        if (index >= this.sticks.length) {
            int[] cube = this.sticks;
            if(this.cubes.contains(makeToString(cube))){

            }else{
                count++;
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateZ(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateZ(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateZ(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateY(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateY(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                cube = rotateY(cube);
                for(int i = 0; i < 4; i++){
                    cube = rotateX(cube);
                    //System.out.println(makeToString(cube));
                    this.cubes.add(makeToString(cube));
                }
                //System.out.println(this.cubes.size());
            }
        } else {
            HashSet<Integer> swapped = new HashSet<>();
            for (int i = index; i < this.sticks.length; i++) {
                if (!swapped.contains(sticks[i])) {
                    swap(index, i);
                    per(index + 1);
                    swap(index, i);
                    swapped.add(this.sticks[i]);
                }
            }
        }
    }

    private void swap(int index, int i) {
        Integer temp = this.sticks[index];
        this.sticks[index] = this.sticks[i];
        this.sticks[i] = temp;
    }

    private void print(){
        for(int s:this.sticks){
            System.out.print(s);
        }
        System.out.println();
    }

    private int[] rotateX(int[] cube){
        int[] temp = new int[cube.length];
        temp[9] = cube[1];
        temp[11] = cube[9];
        temp[3] = cube[11];
        temp[1] = cube[3];
        temp[5] = cube[0];
        temp[8] = cube[5];
        temp[4] = cube[8];
        temp[0] = cube[4];
        temp[6] = cube[2];
        temp[10] = cube[6];
        temp[7] = cube[10];
        temp[2] = cube[7];
        return temp;
    }

    private int[] rotateY(int[] cube){
        int[] temp = new int[cube.length];
        temp[0] = cube[2];
        temp[8] = cube[0];
        temp[10] = cube[8];
        temp[2] = cube[10];
        temp[5] = cube[1];
        temp[9] = cube[5];
        temp[6] = cube[9];
        temp[1] = cube[6];
        temp[4] = cube[3];
        temp[11] = cube[4];
        temp[7] = cube[11];
        temp[3] = cube[7];
        return temp;
    }

    private int[] rotateZ(int[] cube){
        int[] temp = new int[cube.length];
        temp[5] = cube[4];
        temp[6] = cube[5];
        temp[7] = cube[6];
        temp[4] = cube[7];
        temp[0] = cube[3];
        temp[1] = cube[0];
        temp[2] = cube[1];
        temp[3] = cube[2];
        temp[8] = cube[11];
        temp[9] = cube[8];
        temp[10] = cube[9];
        temp[11] = cube[10];
        return temp;
    }

    private String makeToString(int[] cube){
        StringBuilder sb = new StringBuilder();
        for(int i:cube){
            sb.append(String.valueOf(i));
        }
        return sb.toString();
    }
}