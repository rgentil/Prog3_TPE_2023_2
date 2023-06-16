package tudai.prog3.colecciones;

import java.util.ArrayList;
import java.util.List;

import tudai.prog3.util.UnionFind;

public class Estado {

	private List<Tunel> tuneles;
	private List<Integer> estaciones;
	private UnionFind uf;

	public Estado() {
		this.tuneles = new ArrayList<Tunel>();
		this.estaciones = new ArrayList<Integer>();
	}

	public void add(Integer origen, Integer destino, Integer etiqueta) {
		tuneles.add(new Tunel(origen, destino, etiqueta));
		agregarEstacion(origen);
		agregarEstacion(destino);
	}

	public void agregarEstacion(Integer e) {
		if (!estaciones.contains(e)) {
			estaciones.add(e);
		}
	}

	public int getCantidadEstaciones() {
		return estaciones.size();
	}

	public Tunel removeTunel() {
		return tuneles.remove(0);
	}

	public void addTunel(Tunel tunel) {
		tuneles.add(0, tunel);
	}

	public void removeEstacion(Tunel t) {
		estaciones.remove(t.getOrigen());
		estaciones.remove(t.getDestino());
	}

	public boolean removeEstacion(Integer e) {
		return estaciones.remove(e);
	}

	public void addUnion(Integer origen, Integer destino) {
		if (this.uf == null) {
			this.uf = new UnionFind(this.getCantidadEstaciones());
		}
		this.uf.union(origen, destino);
	}

	public boolean coneccionCompleta() {
		return this.uf.coneccionCompleta();
	}

	public boolean sinTuneles() {
		return (tuneles == null || tuneles.size() == 0);
	}

	public boolean sinEstaciones() {
		return (estaciones == null || estaciones.size() == 0);
	}

	public void imprimir(List<Tunel> caminoBack) {
		System.out.println("Camino");
		for (Tunel t : caminoBack) {
			System.out.print("E" + t.getOrigen() + "-E" + t.getDestino() + " ");
		}
		System.out.println("");
	}

	public boolean estacionesHabilitadas(Tunel tunel_actual) {
		return estaciones.contains(tunel_actual.getOrigen()) || estaciones.contains(tunel_actual.getDestino());
	}

	public int getCantidadTuneles() {
		return this.tuneles.size();
	}

	public List<Tunel> getTuneles(){
		return this.tuneles;
	}
	
}
