package principal;

import modelos.Auto;
import modelos.Moto;

public class AppEjemplo {

	public static void main(String[] args) {
		Auto auto1 = new Auto("ford","mustang",2025,2,false);
		auto1.mostrarInfo();
		
		Moto moto1 = new Moto("Harley Davidson","fatbob",2018,false);
		moto1.mostrarInfo();
	}

}
