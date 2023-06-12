package tudai.prog3.colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Lauge Guillermina, Gentil Ricardo
 *
 */
public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, HashSet<Arco<T>>> vertices;
	private int cantVertices;
	private int cantArcos;

	public GrafoDirigido() {
		this.vertices = new HashMap<Integer, HashSet<Arco<T>>>();
		cantVertices = 0;
		cantArcos = 0;
	}

	@Override
	public void agregarVertice(int verticeId) {
		if (!this.vertices.containsKey(verticeId)) {
			this.vertices.put(verticeId, new HashSet<>());
			cantVertices++;
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		if (this.contieneVertice(verticeId)) {
			for (Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();) {
				Arco<T> arco = (Arco<T>) it.next();
				this.borrarArco(verticeId, arco.getVerticeDestino());
			}
			vertices.remove(verticeId);
			cantVertices--;
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		this.agregarVertice(verticeId1);
		this.agregarVertice(verticeId2);
		Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
		this.vertices.get(verticeId1).add(arco);
		cantArcos++;
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Arco<T> arco = this.obtenerArco(verticeId1, verticeId2);
		if (arco != null) {
			this.vertices.get(verticeId1).remove(arco);
			cantArcos--;
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return this.obtenerArco(verticeId1, verticeId2) == null ? false : true;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		for (Iterator<Arco<T>> it = vertices.get(verticeId1).iterator(); it.hasNext();) {
			Arco<T> arco = (Arco<T>) it.next();
			if (arco.getVerticeDestino() == verticeId2) {
				return arco;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.cantVertices;
	}

	@Override
	public int cantidadArcos() {
		return this.cantArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if (this.vertices.containsKey(verticeId)) {
			List<Integer> adyacentes = new ArrayList<Integer>();
			for (Iterator<Arco<T>> it = vertices.get(verticeId).iterator(); it.hasNext();) {
				Arco<T> arco = (Arco<T>) it.next();
				adyacentes.add(arco.getVerticeDestino());
			}
			return adyacentes.iterator();
		}
		return new ArrayList<Integer>().iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> arcos = new ArrayList<Arco<T>>();
		for (Iterator<Integer> iterator = this.obtenerVertices(); iterator.hasNext();) {
			Integer vertice = (Integer) iterator.next();
			arcos.addAll(vertices.get(vertice));
		}
		return arcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if (!this.vertices.containsKey(verticeId))
			return null;
		return this.vertices.get(verticeId).iterator();
	}

	@Override
	public void imprimir() {
		for (Iterator<Integer> iteratorV = this.obtenerVertices(); iteratorV.hasNext();) {
			Integer vertice = (Integer) iteratorV.next();
			System.out.print(vertice + ": ");
			for (Iterator<Integer> iteratorA = this.obtenerAdyacentes(vertice); iteratorA.hasNext();) {
				Integer ady = (Integer) iteratorA.next();
				System.out.print(ady + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void imprimirPonderado() {
		System.out.println("Grafo ponderado");
		for (Iterator<Integer> iteratorV = this.obtenerVertices(); iteratorV.hasNext();) {
			Integer vertice = (Integer) iteratorV.next();
			System.out.print(vertice + " -> ");
			for (Iterator<Arco<T>> iteratorA = this.obtenerArcos(vertice); iteratorA.hasNext();) {
				Arco<T> ady = (Arco<T>) iteratorA.next();
				System.out.print(ady.getVerticeDestino() + "(" + ady.getEtiqueta() + ") ");
			}
			System.out.println();
		}
	}
}
