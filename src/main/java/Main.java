package test.java;

import java.WorkWithList;

public class Main {

    public static void main(String[] args) {




        WorkWithList userArray = new WorkWithList();
        int[] array = {10, 150, 55, 16, 2, 52, 4, 452, 5, 22, 484, 5};
        userArray.fillArrayList(array);
        userArray.getTargetList();

        userArray.findMax();
        userArray.sorter();
        userArray.emptyArrayList();

        System.out.println(userArray.getTargetList());
    }
}
