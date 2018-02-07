import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

import static org.apache.commons.lang3.ArrayUtils.add;

/**
 * Created by dkhomenko on 07.02.2018.
 */
public class Leilighem {
		public static void main(String[] args) {
			int array[]  = new int[10];
			for (int i = 0; i < array.length; i++) {

				int randomNumber = new Random().nextInt(10);

				System.out.print("Вывод рандомного числа: " + randomNumber + "\n");

				array[i] = randomNumber;

				System.out.print("Вывод измененного массива: " + ArrayUtils.toString(array) + "\n");
			}
		}
	}
