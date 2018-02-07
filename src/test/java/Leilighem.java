import org.apache.commons.lang3.ArrayUtils;

import static org.apache.commons.lang3.ArrayUtils.add;

/**
 * Created by dkhomenko on 05.02.2018.
 */
class Leilighem {
	public static void main(String[] args) {
		int array[]  = new int[1];;
		for (int i = 0; i < array.length; i++) {
		    String toPrint = "array[" + i + "]=" + array[i];
			System.out.print("Вывод массива: " + toPrint + "\n");

			int randomNumber =  (int) (Math.random() * 10);
			System.out.print("Вывод рандомного числа: " + randomNumber + "\n");

			array = add(array, randomNumber);

			System.out.print("Вывод измененного массива: " + ArrayUtils.toString(array) + "\n");
			if(array.length == 10) {
				break;
			}
		}
	}
}
