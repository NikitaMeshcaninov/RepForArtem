import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithList {
    private ArrayList<Integer> targetList = new ArrayList<Integer>();

    public ArrayList<Integer> getTargetList() {
        return targetList;
    }

    private int switcherVar1 = 0;
    private int switcherVar2 = 0;
    private int i = 0;

    public void fillArrayList(int[] array) {

        for (int i = 0; i < array.length; i++)
            targetList.add(array[i]);

    }


    public void sorter() {
        System.out.println("In witch way u want (true - upper way, false - down way)");
        Scanner sc = new Scanner(System.in);
        boolean trigger = sc.nextBoolean();


        if (getTargetList().isEmpty()) {
            throw new NullPointerException("Array is empty");
        }
        int size = targetList.size();

        if (trigger) {

            while (size != 0) {

                if (targetList.get(i) > targetList.get(i + 1)) {     //Switch variables untill thay will be in right order
                    size = switcher();
                } else {
                    size--;
                    i++;
                }
                if (i == targetList.size() - 1)
                    i = 0;

            }
        }
        if (!trigger) {

            while (size != 0) {

                if (targetList.get(i) < targetList.get(i + 1)) {     //Same cycle for reverse sort
                    size = switcher();
                } else {
                    size--;
                    i++;
                }
                if (i == targetList.size() - 1)
                    i = 0;

            }
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

    public int switcher() {
        switcherVar1 = targetList.get(i);
        switcherVar2 = targetList.get(i + 1);
        targetList.set(i + 1, switcherVar1);
        targetList.set(i, switcherVar2);
        i++;
        return targetList.size();
    }
}
