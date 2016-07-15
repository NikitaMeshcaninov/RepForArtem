package java;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithList {
    private ArrayList<Integer> targetList = new ArrayList<Integer>();

    public ArrayList<Integer> getTargetList() {
        return targetList;
    }

    public void fillArrayList(int[] array) {

        for (int i = 0; i < array.length; i++)
            targetList.add(array[i]);

    }


    public void sorter() {
        System.out.println("In witch way u want (descend - any key, or type ascend)");
        Scanner sc = new Scanner(System.in);
        boolean ascend = false;
        if (sc.next().equals("ascend")) {
            ascend = true;
        }

        if (getTargetList().isEmpty()) {
            throw new NullPointerException("Array is empty");
        }

        int counter = 0;

        while (targetList.size() - counter != 0) {
            for (int i = 0; i < targetList.size() - 1; i++) {
                if (targetList.get(i) > targetList.get(i + 1) && ascend) {
                    switcher(i);
                } else if (targetList.get(i) < targetList.get(i + 1) && !ascend) {
                    switcher(i);
                }
            }
            counter++;
        }

        System.out.println(targetList.toString());

    }

    public void findMax() {
        int max = 0;
        if (targetList.isEmpty()) {
            throw new NullPointerException("Array is empty");

        }
        for (int i = 0; i < targetList.size(); i++) {
            if (targetList.get(i) > max) {
                max = targetList.get(i);
            }
        }
        System.out.println("Max element " + max);

    }

    public void emptyArrayList() {
        targetList.clear();

    }

    public void switcher(int index) {
        int switcherVar1 = targetList.get(index);
        targetList.set(index, targetList.get(index + 1));
        targetList.set(index + 1, switcherVar1);
    }
}
