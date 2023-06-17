package tudai.prog3.algoritmo;

import java.util.ArrayList;
import java.util.List;

import tudai.prog3.colecciones.Estado;
import tudai.prog3.colecciones.Tunel;

/**
 * 
 * @author Lauge Guillermina, Gentil Ricardo
 *
 */
public class Backtracking {
	private int km;
	private int iteraciones;

	public Backtracking() {
	}

	public List<Tunel> back(Estado estado) {
		this.iteraciones = 0;
		this.km = Integer.MAX_VALUE;

		List<Tunel> tuneles_visitados = new ArrayList<Tunel>();
		List<Integer> estaciones_conectadas = new ArrayList<Integer>();

		List<Tunel> conexion_parcial = new ArrayList<Tunel>();
		List<Tunel> conexion_final = new ArrayList<Tunel>();
		int km_parcial = 0;

		_back(estado, tuneles_visitados, estaciones_conectadas, conexion_parcial, conexion_final, km_parcial);

		return conexion_final;
	}

	private void _back(Estado estado, List<Tunel> tuneles_visitados, List<Integer> estaciones_conectadas,
			List<Tunel> conexion_parcial, List<Tunel> conexion_final, int km_parcial) {
		// Contador de veces que itera la recursión
		iteraciones++;

		if (tuneles_visitados.size() == estado.getCantidadTuneles()) {// Si se vesitaron todos los tuneles
			if (estaciones_conectadas.size() == estado.getCantidadEstaciones()) {// Si se usaron todas las estaciones
				if (estado.coneccionCompleta()) {// Si todas las estaciones quedaron conectadoas.
					if (km_parcial < this.km) {// Se guarda el camino más corto en total de kms.
						conexion_final.clear();
						conexion_final.addAll(conexion_parcial);
						km = km_parcial;
					}
				}
			}
		} else {
			// Se van usar todos los tuneles para encontrar el camino óptimo.
			for (Tunel tunel_actual : estado.getTuneles()) {
				if (!tuneles_visitados.contains(tunel_actual)) {
					// Para no volver a usar un mismo tunel.
					tuneles_visitados.add(tunel_actual);

					// Datos del tunel actual.
					Integer origen = tunel_actual.getOrigen();
					Integer destino = tunel_actual.getDestino();
					Integer etiqueta = tunel_actual.getEtiqueta();

					// Si el tunel conecta alguna estación que no se haya conectado, ya sea
					// origen o destino, ese tunel se puede usar para la solución.
					if (!estaciones_conectadas.contains(origen) || !estaciones_conectadas.contains(destino)) {

						// Variables locales al método usadas al momento de volver de la recursión y
						// sacar la estación de la lista de estaciones conectadas.
						boolean marco_origen = false;
						boolean marco_destino = false;

						// Si destino y/u origen no se han conectado se conectan
						if (!estaciones_conectadas.contains(origen)) {
							marco_origen = true;
							estaciones_conectadas.add(origen);
						}

						if (!estaciones_conectadas.contains(destino)) {
							marco_destino = true;
							estaciones_conectadas.add(destino);
						}

						// Usa algoritmo union-find para agrupar en un conjunto al origen y al destino y
						// luego poder verificar si todas las estaciones fueron conectadas.
						estado.addUnion(origen, destino);

						// Actualiza los datos para el siguiente estado.
						km_parcial += etiqueta;
						conexion_parcial.add(tunel_actual);

						// if (marco_origen || marco_destino) {// Poda, hay alguna estacion sin
						// conectar.
						_back(estado, tuneles_visitados, estaciones_conectadas, conexion_parcial, conexion_final,
								km_parcial);
						// }

						// Cuando vuelve la recursión tomando la desicción de usar el tunel actual en la
						// posible solucion, se desasen los cambios para volver a llamar recursivamente
						// sin usar el tunel actual.
						if (marco_origen) {
							marco_origen = false;
							estaciones_conectadas.remove(origen);
						}

						if (marco_destino) {
							marco_destino = false;
							estaciones_conectadas.remove(destino);
						}

						conexion_parcial.remove(tunel_actual);
						km_parcial -= etiqueta;
						estado.addUnion(origen, destino);

						_back(estado, tuneles_visitados, estaciones_conectadas, conexion_parcial, conexion_final,
								km_parcial);

					}
					tuneles_visitados.remove(tuneles_visitados.size() - 1);
				}
			}
		}
	}

	public int getKm() {
		return this.km;
	}

	public int getItereaciones() {
		return this.iteraciones;
	}
}
