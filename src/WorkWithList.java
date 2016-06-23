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
        System.out.println("In witch way u want (true - upper way, false - down way)");
        Scanner sc = new Scanner(System.in);
        boolean trigger = sc.nextBoolean();


        if (getTargetList().isEmpty()) {
            throw new NullPointerException("Array is empty");
        }
        int size = targetList.size();
        int counter = 0;

        while (size != 0) {

            if (targetList.get(counter) > targetList.get(counter + 1) && trigger) {
                size = switcher(counter);
                counter++;
            } else if (targetList.get(counter) < targetList.get(counter + 1) && !trigger) {
                size = switcher(counter);
                counter++;
            } else {
                size--;
                counter++;
            }
            if (counter == targetList.size() - 1)
                counter = 0;

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

    public int switcher(int index) {
        int switcherVar1 = targetList.get(index);
        targetList.set(index, targetList.get(index + 1));
        targetList.set(index + 1, switcherVar1);
        return targetList.size();
    }
}
