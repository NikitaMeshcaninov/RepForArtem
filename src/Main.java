import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayListForTranforming = new ArrayList();
        WorkWithList ourObject = new WorkWithList();
        int [] array = {105,16,23,12};
        arrayListForTranforming =  ourObject.arrayListbuilder(array);


        System.out.println("Max int in our Array is " +  ourObject.ourMax(arrayListForTranforming));
        arrayListForTranforming = ourObject.sorter(arrayListForTranforming);
        arrayListForTranforming = ourObject.emptyArrayList(arrayListForTranforming);

        System.out.println(ourObject.toString());
    }
}
