import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithList {
    public ArrayList<Integer> arrayListbuilder(int[] array) {
        ArrayList userArrayList = new ArrayList();
        for (int i = 0; i < array.length; i++)
            userArrayList.add(array[i]);
        return userArrayList;
    }


    public ArrayList<Integer> sorter(ArrayList<Integer> arrayListThatWeWillOpperate) {
        System.out.println("In witch way u want (true - upper way, false - down way)");
        Scanner sc = new Scanner(System.in);
        boolean trigger = sc.nextBoolean();
        boolean Flag = false;
        int switcherVar1 = 0;
        int switcherVar2 = 0;
        int i = 0;
        int size = arrayListThatWeWillOpperate.size() + 20;

        if (trigger == true && !arrayListThatWeWillOpperate.isEmpty()) {

            for (; ; ) {

                if (arrayListThatWeWillOpperate.get(i) > arrayListThatWeWillOpperate.get(i + 1)) {     //Switch variables untill thay will be in right order
                    switcherVar1 = arrayListThatWeWillOpperate.get(i);
                    switcherVar2 = arrayListThatWeWillOpperate.get(i + 1);
                    arrayListThatWeWillOpperate.set(i + 1, switcherVar1);
                    arrayListThatWeWillOpperate.set(i, switcherVar2);
                    i++;
                    size = arrayListThatWeWillOpperate.size() + 200;
                } else {
                    size--;
                    i++;
                }
                if (i == arrayListThatWeWillOpperate.size() - 1)
                    i = 0;
                if (size == 0)
                    break;
            }
        }
        if (trigger == false && !arrayListThatWeWillOpperate.isEmpty()) {
            for (; ; ) {

                if (arrayListThatWeWillOpperate.get(i) < arrayListThatWeWillOpperate.get(i + 1)) {     //Same cycle for reverse sort
                    switcherVar1 = arrayListThatWeWillOpperate.get(i);
                    switcherVar2 = arrayListThatWeWillOpperate.get(i + 1);
                    arrayListThatWeWillOpperate.set(i + 1, switcherVar1);
                    arrayListThatWeWillOpperate.set(i, switcherVar2);
                    i++;
                    size = arrayListThatWeWillOpperate.size() + 200;
                } else {
                    size--;
                    i++;
                }
                if (i == arrayListThatWeWillOpperate.size() - 1)
                    i = 0;
                if (size == 0)
                    break;
            }
        }
        System.out.println(arrayListThatWeWillOpperate.toString());
        return arrayListThatWeWillOpperate;
    }

    public int findMax(ArrayList<Integer> arrayListThatWeWillOpperate) {
        int max = 0;
        if (arrayListThatWeWillOpperate.isEmpty()) {
            throw new NullPointerException("Array is empty");

        }
        for (int i = 0; i < arrayListThatWeWillOpperate.size(); i++) {
            if (arrayListThatWeWillOpperate.get(i) > max) {
                max = arrayListThatWeWillOpperate.get(i);
            }
        }
        System.out.println("Max element " + max);
        return max;
    }

    public ArrayList<Integer> emptyArrayList(ArrayList<Integer> arrayListThatWeWillOpperate) {
        arrayListThatWeWillOpperate.clear();
        return arrayListThatWeWillOpperate;
    }
}
