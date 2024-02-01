package m6uf2activitatparchis;
import model.Casilla;
import model.Fitxa;
import model.Jugador;
public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Casilla casilla = new Casilla("normal", 13, 1);
		System.out.println(casilla.toString());
		
		Jugador j1 = new Jugador("Javier", "amarillo", 2);
		Jugador j2 = new Jugador("roger", "rojo", 0);
		//En la clase fitxa, en vez de un INT de IdJugador recibe un OBJETO jugador
		Fitxa f1 = new Fitxa(10, true, j2);
		System.out.println(f1.toString());
		
	}

}
