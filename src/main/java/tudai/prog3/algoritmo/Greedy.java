package tudai.prog3.algoritmo;

import java.util.ArrayList;
import java.util.List;

import tudai.prog3.colecciones.Grafo;

/**
 * 
 * @author Lauge Guillermina, Gentil Ricardo
 *
 */
public class Greedy {
	private Grafo<Integer> red;
	private int cant_vertices;
	private int km;
	private List<String> camino_final;

	public Greedy(Grafo<Integer> red) {
		this.red = red;
		this.cant_vertices = red.cantidadVertices();
		this.camino_final = new ArrayList<String>();
	}

	public int getKm() {
		return this.km;
	}

}
