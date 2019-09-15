import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

import static org.apache.commons.lang3.ArrayUtils.add;

/**
 * Created by dkhomenko on 07.02.2018.
 */
public class Leilighem {

	static int[] randomArray(int size) {
		int array[] = new int[size];
		for (int i = 0; i < array.length; i++) {
			int randomNumber = new Random().nextInt(10);
			array[i] = randomNumber;
		}
		return array;
	}


	public static void main(String[] args) {

		Personage Huan = new Personage("half-human", "monk");

		System.out.print("Вывод измененного массива: " + ArrayUtils.toString(randomArray(10)) + "\n");

	}

}
