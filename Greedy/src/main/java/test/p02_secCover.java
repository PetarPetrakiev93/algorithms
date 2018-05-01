package test;

import java.util.*;

public class p02_secCover {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(set);
        }

        List<int[]> choosenSets = chooseSets(sets, universe);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> selectedSets = new ArrayList<>();
        HashSet<Integer> universeSet = new HashSet<>();
        List<int[]> setsTemp = new ArrayList<>();
        for (int[] set : sets) {
            setsTemp.add(set);
        }
        for (int i : universe) {
            universeSet.add((Integer) i);
        }
        while (universeSet.size() > 0){
            setsTemp.sort((a, b) -> b.length - a.length);
            int[] currentSet = null;
            for (int[] set : setsTemp) {
                boolean isTrue = false;
                for (int i : set) {
                    if(universeSet.contains((Integer) i)){
                        isTrue = true;
                    }
                }
                if(isTrue){
                    currentSet = set;
                    break;
                }
            }

            selectedSets.add(currentSet);
            setsTemp.remove(currentSet);

            for (int i : currentSet) {
                universeSet.remove((Integer) i);
            }
        }
        return selectedSets;
    }
}
