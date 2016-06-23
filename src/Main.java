import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayListForTranforming = new ArrayList();
        WorkWithList userArray = new WorkWithList();
        int [] array = {10,150,55,16,2};
        userArray.fillArrayList(array);
        arrayListForTranforming = userArray.getTargetList();

        System.out.println("Max int in our Array is " +  userArray.findMax(arrayListForTranforming));
        userArray.sorter();
        userArray.emptyArrayList();

        System.out.println(userArray.getTargetList());
    }
}
