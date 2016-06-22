import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayListForTranforming = new ArrayList();
        WorkWithList userArray = new WorkWithList();
        int [] array = {10,150,55,16,2};
        arrayListForTranforming =  userArray.fillArrayList(array);


        System.out.println("Max int in our Array is " +  userArray.findMax(arrayListForTranforming));
        arrayListForTranforming = userArray.sorter(arrayListForTranforming);
        arrayListForTranforming = userArray.emptyArrayList(arrayListForTranforming);

        System.out.println(userArray.toString());
    }
}
