import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayListForTranforming = new ArrayList();
        WorkWithList userArray = new WorkWithList();
        int [] array = {10,150,55,16,2,52,4,452,5,22,484,5};
        userArray.fillArrayList(array);
        arrayListForTranforming = userArray.getTargetList();

        userArray.findMax();
        userArray.sorter();
        userArray.emptyArrayList();

        System.out.println(userArray.getTargetList());
    }
}
