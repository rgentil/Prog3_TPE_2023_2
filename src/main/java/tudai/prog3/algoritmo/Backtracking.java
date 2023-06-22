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
	private int iteraciones_solucion;

	public Backtracking() {
	}

	public List<Tunel> back(Estado estado) {
		this.iteraciones = 0;
		this.iteraciones_solucion = 0;
		this.km = Integer.MAX_VALUE;

		List<Tunel> conexion_parcial = new ArrayList<Tunel>();
		List<Tunel> conexion_final = new ArrayList<Tunel>();
		int km_parcial = 0;

		_back(estado, conexion_parcial, conexion_final, km_parcial);

		return conexion_final;
	}

	private void _back(Estado estado, List<Tunel> conexion_parcial, List<Tunel> conexion_final, int km_parcial) {
		// Contador de veces que itera la recursión
		iteraciones++;
		// System.out.println("Cantidad de iteraciones: " + iteraciones);
		if (estado.tunelesTodosVisitados()) {// Si se vesitaron todos los tuneles
			if (estado.estacionesTodasConectadas()) {// Si se usaron todas las estaciones
				if (estado.coneccionCompleta()) {// Si todas las estaciones quedaron conectadoas.
					if (km_parcial < this.km) {// Se guarda el camino más corto en total de kms.
						conexion_final.clear();
						conexion_final.addAll(conexion_parcial);
						km = km_parcial;
						iteraciones_solucion++;
						// System.out.println("Iteraciones_solucion: " + iteraciones_solucion);
					}
				}
			}
		} else {
			// Se van usar todos los tuneles para encontrar el camino óptimo.
			for (Tunel tunel_actual : estado.getTuneles()) {
				if (!estado.tunelVisitado(tunel_actual)) {
					// Para no volver a usar un mismo tunel.
					estado.setTunelVisitado(tunel_actual, true);

					// Datos del tunel actual.
					Integer origen = tunel_actual.getOrigen();
					Integer destino = tunel_actual.getDestino();
					Integer etiqueta = tunel_actual.getEtiqueta();

					// Si el tunel conecta alguna estación que no se haya conectado, ya sea
					// origen o destino, ese tunel se puede usar para la solución.
					if (!estado.estacionConectada(origen) || !estado.estacionConectada(destino)) {

						// Variables locales al método usadas al momento de volver de la recursión y
						// sacar la estación de la lista de estaciones conectadas.
						boolean marco_origen = false;
						boolean marco_destino = false;

						// Si destino y/u origen no se han conectado se conectan
						if (!estado.estacionConectada(origen)) {
							marco_origen = true;
							estado.setEstacionConectada(origen, true);
						}

						if (!estado.estacionConectada(destino)) {
							marco_destino = true;
							estado.setEstacionConectada(destino, true);
						}

						// Usa algoritmo union-find para agrupar en un conjunto al origen y al destino y
						// luego poder verificar si todas las estaciones fueron conectadas.
						estado.addUnion(origen, destino);

						// Actualiza los datos para el siguiente estado.
						km_parcial += etiqueta;
						conexion_parcial.add(tunel_actual);

						if (km_parcial < this.km) {
							_back(estado, conexion_parcial, conexion_final, km_parcial);
						}

						// Cuando vuelve la recursión tomando la desición de usar el tunel actual en la
						// posible solucion, se desasen los cambios para volver a llamar recursivamente
						// sin usar el tunel actual.
						if (marco_origen) {
							marco_origen = false;
							estado.setEstacionConectada(origen, false);
						}

						if (marco_destino) {
							marco_destino = false;
							estado.setEstacionConectada(destino, false);
						}

						conexion_parcial.remove(tunel_actual);
						km_parcial -= etiqueta;
						// Deshace la union
						//estado.split(origen, destino);
						 estado.addUnion(origen, destino);

						_back(estado, conexion_parcial, conexion_final, km_parcial);

					}
					estado.setTunelVisitado(tunel_actual, false);

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

	public int getItereacionesSolucion() {
		return this.iteraciones_solucion;
	}
}
