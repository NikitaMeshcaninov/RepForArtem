import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkWithList {
    public ArrayList<Integer> arrayListbuilder(int [] array){
        ArrayList someArrayList = new ArrayList();
        for (int i = 0;i<array.length;i++)
            someArrayList.add(array[i]);
        return someArrayList;
    }


    public ArrayList<Integer> sorter(ArrayList <Integer> arrayListThatWeWillOpperate) {
        System.out.println("Как вы хотите отсортировать?(true - если по возростанию false - если по убыванию)");
        Scanner sc = new Scanner(System.in);
        boolean Trigger = sc.nextBoolean();
        boolean Flag = false;
        int switcherVar1 = 0;
        int switcherVar2 = 0;
        int i = 0;
        int size = arrayListThatWeWillOpperate.size() + 20;

        if (Trigger == true && !arrayListThatWeWillOpperate.isEmpty()) {

            for (; ; ) {

                if (arrayListThatWeWillOpperate.get(i) > arrayListThatWeWillOpperate.get(i + 1)) {     //меняем местами элементы пока не отсортируеться
                    switcherVar1 = arrayListThatWeWillOpperate.get(i);
                    switcherVar2 = arrayListThatWeWillOpperate.get(i + 1);
                    arrayListThatWeWillOpperate.set(i + 1, switcherVar1);
                    arrayListThatWeWillOpperate.set(i, switcherVar2);
                    i++;
                    size = arrayListThatWeWillOpperate.size() + 200;                         //после последней перестановки должно пройти
                } else {                                                    //стлько итераций сколько членов в коллекции
                    size--;
                    i++;
                }
                if (i == arrayListThatWeWillOpperate.size() - 1)
                    i = 0;                        //прежде чем  метод прервет работу и вернет значение
                if (size == 0)
                    break;                                   //чтобы убедиться что сортировка завершина успешно
            }
        } if (Trigger == false && !arrayListThatWeWillOpperate.isEmpty()) {
            for (; ; ) {

                if (arrayListThatWeWillOpperate.get(i) < arrayListThatWeWillOpperate.get(i + 1)) {     //все тоже самое для обратной сортировки
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

    public int ourMax(ArrayList<Integer>arrayListThatWeWillOpperate) {
        int max = 0;
        if (arrayListThatWeWillOpperate.isEmpty()) {
            System.out.println("Поступила пустая коллекция - вывод метода ошибочный");
        }
        for (int i = 0; i < arrayListThatWeWillOpperate.size(); i++) {
            if (arrayListThatWeWillOpperate.get(i) > max) {
                max = arrayListThatWeWillOpperate.get(i);
            }
        }
        System.out.println("самый большой элемент " + max);
        return max;
    }

    public ArrayList <Integer> emptyArrayList(ArrayList<Integer>arrayListThatWeWillOpperate) {
        arrayListThatWeWillOpperate.clear();
        System.out.println("отработал метод emptyArrayList, теперь от " +
                "вашего масива осталось только " + arrayListThatWeWillOpperate.toString());
        return arrayListThatWeWillOpperate;
    }
}
