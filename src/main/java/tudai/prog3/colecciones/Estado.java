package tudai.prog3.colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tudai.prog3.util.UnionFind;

public class Estado {

	private List<Tunel> tuneles;
	private List<Integer> estaciones;
	private UnionFind uf;
	private HashMap<Tunel, Boolean> tuneles_visitados;
	private HashMap<Integer, Boolean> estaciones_conectadas;

	public Estado() {
		this.tuneles = new ArrayList<Tunel>();
		this.estaciones = new ArrayList<Integer>();
		this.tuneles_visitados = new HashMap<Tunel, Boolean>();
		this.estaciones_conectadas = new HashMap<Integer, Boolean>();
	}

	public void add(Integer origen, Integer destino, Integer etiqueta) {
		Tunel tunel = new Tunel(origen, destino, etiqueta);
		tuneles.add(tunel);
		tuneles_visitados.put(tunel, false);
		agregarEstacion(origen);
		agregarEstacion(destino);
	}

	public void agregarEstacion(Integer estacion) {
		if (!estaciones.contains(estacion)) {
			estaciones.add(estacion);
			estaciones_conectadas.put(estacion, false);
		}
	}

	public int getCantidadEstaciones() {
		return estaciones.size();
	}

	public void addUnion(Integer origen, Integer destino) {
		if (this.uf == null) {
			this.uf = new UnionFind(this.getCantidadEstaciones());
		}
		this.uf.union(origen - 1, destino - 1);
	}

	public void split(Integer origen, Integer destino) {
		this.uf.split(getCantidadEstaciones(), getCantidadEstaciones());
	}

	public boolean coneccionCompleta() {
		return this.uf.connected();
	}

	public void imprimir(List<Tunel> caminoBack) {
		System.out.println("Camino");
		for (Tunel t : caminoBack) {
			System.out.print("E" + t.getOrigen() + "-E" + t.getDestino() + " ");
		}
		System.out.println("");
	}

	public List<Tunel> getTuneles() {
		return this.tuneles;
	}

	public boolean tunelesTodosVisitados() {
		for (boolean valor : tuneles_visitados.values()) {
			if (!valor) {
				return false;
			}
		}
		return true;
	}

	public boolean estacionesTodasConectadas() {
		for (boolean valor : estaciones_conectadas.values()) {
			if (!valor) {
				return false;
			}
		}
		return true;
	}

	public boolean tunelVisitado(Tunel tunel) {
		return tuneles_visitados.get(tunel);
	}

	public boolean estacionConectada(Integer estacion) {
		return estaciones_conectadas.get(estacion);
	}

	public void setTunelVisitado(Tunel tunel, boolean b) {
		tuneles_visitados.put(tunel, b);
	}

	public void setEstacionConectada(Integer estacion, boolean b) {
		estaciones_conectadas.put(estacion, b);
	}

}
