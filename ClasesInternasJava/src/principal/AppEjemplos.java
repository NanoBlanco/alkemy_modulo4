package principal;

import interfaz.Notificador;
import modelos.Billetera;
import modelos.Usuario;

public class AppEjemplos {

	public static void main(String[] args) {
		
		Usuario u = new Usuario("Ana");
		boolean ok = u.ok;
		System.out.println("Nombre Valido "+ok);
		
		
		Billetera b = new Billetera();
		Billetera.TarjetaVirtual tarjeta = b.new TarjetaVirtual();
		tarjeta.pagar(2000);
		
		Runnable tarea = new Runnable() {
			@Override
			public void run() {
				System.out.println("Ejecutando tarea...");
				
			}
		};
		
		new Thread(tarea).start();
		
		Notificador n = new Notificador() {

			@Override
			public void enviar(String msg) {
				System.out.println("Notificacion: "+msg);
				
			}
			
		};
		n.enviar("Pago Aprobado");
	}

}
