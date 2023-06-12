package tudai.prog3.algoritmo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tudai.prog3.colecciones.Arco;
import tudai.prog3.colecciones.Grafo;
import tudai.prog3.util.UnionFind;

/**
 * 
 * @author Lauge Guillermina, Gentil Ricardo
 *
 */
public class Backtracking {
	private Grafo<Integer> red;
	private int cant_vertices;
	private List<String> camino_final;
	private int km;

	public Backtracking(Grafo<Integer> red) {
		this.red = red;
		this.cant_vertices = red.cantidadVertices();
	}

	public List<String> back() {
		this.camino_final = new ArrayList<String>();
		this.km = Integer.MAX_VALUE;
		for (Iterator<Integer> it = red.obtenerVertices(); it.hasNext();) {
			UnionFind uf = new UnionFind(cant_vertices + 1);
			List<Integer> visitados = new ArrayList<Integer>();
			List<String> camino = new ArrayList<String>();
			Integer vertice = it.next();
			_back(vertice, uf, visitados, camino, 0);
		}
		return camino_final;
	}

	private void _back(int vertice_actual, UnionFind uf, List<Integer> visitados, List<String> camino,
			int km_parcial) {

		visitados.add(vertice_actual);

		if (visitados.size() == cant_vertices) {
			if (todosLosVerticesEstanConectados(uf)) {
				if (km_parcial <= this.km) {
					camino_final.clear();
					camino_final.addAll(camino);
					km = km_parcial;
				}
			}
		}

		for (Iterator<Arco<Integer>> iterator = red.obtenerArcos(vertice_actual); iterator.hasNext();) {
			Arco<Integer> arco = (Arco<Integer>) iterator.next();
			Integer vertice_ady = arco.getVerticeDestino();
			int etiqueta = arco.getEtiqueta();
			String conexion = "E" + vertice_actual + "-E" + vertice_ady;
			if (!visitados.contains(vertice_ady)) {
				uf.union(vertice_actual, vertice_ady);
				km_parcial += etiqueta;
				camino.add(conexion);	

				_back(vertice_ady, uf, visitados, camino, km_parcial);

				km_parcial -= etiqueta;
				uf.union(vertice_actual, vertice_ady);
				camino.remove(camino.size() - 1);
			}
		}

		visitados.remove(visitados.size() - 1);

	}

	/**
	 * Verifica si todos los vértices están conectados
	 * 
	 * @param uf Uinon Find
	 * @return true si todos estan conectados.
	 */
	private boolean todosLosVerticesEstanConectados(UnionFind uf) {
		int raiz = uf.find(1); // Encuentra la raíz del primer vértice
		for (int i = 2; i < cant_vertices + 1; i++) {
			if (uf.find(i) != raiz) {
				return false;// No todos los vértices están conectados
			}
		}
		return true;
	}

	public int getKm() {
		return this.km;
	}

}
