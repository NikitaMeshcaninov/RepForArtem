/**
 * Created by dkhomenko on 09.02.2018.
 */
public class Personage {

	String race;
	String clasS;


	 Personage (String race, String clasS) {
		this.race = race;
		this.clasS = clasS;
	 	String per = race + " and " + clasS;
		System.out.println(per);

	}

}
