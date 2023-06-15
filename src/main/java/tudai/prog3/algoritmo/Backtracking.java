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

		List<Tunel> conexion_final = new ArrayList<Tunel>();
		List<Tunel> conexion_parcial = new ArrayList<Tunel>();

		_back(estado, conexion_parcial, conexion_final, 0);

		return conexion_final;
	}

	private void _back(Estado estado, List<Tunel> conexion_parcial, List<Tunel> conexion_final, int km_parcial) {
		iteraciones++;

		if (estado.sinTuneles()) {
			if (estado.sinEstaciones()) {
				if (estado.coneccionCompleta()) {
					if (km_parcial <= this.km) {
						conexion_final.clear();
						conexion_final.addAll(conexion_parcial);
						km = km_parcial;
					}
				}
			}
		} else {

			Tunel tunel_actual = estado.removeTunel();

			_back(estado, conexion_parcial, conexion_final, km_parcial);

			if (estado.estacionesHabilitadas(tunel_actual)) {
				estado.addUnion(tunel_actual.getOrigen(), tunel_actual.getDestino());
				km_parcial += tunel_actual.getEtiqueta();
				conexion_parcial.add(tunel_actual);
				boolean saco_origen = estado.removeEstacion(tunel_actual.getOrigen());
				boolean saco_destino = estado.removeEstacion(tunel_actual.getDestino());

				_back(estado, conexion_parcial, conexion_final, km_parcial);

				if (saco_origen) {
					estado.agregarEstacion(tunel_actual.getOrigen());
				}
				if (saco_destino) {
					estado.agregarEstacion(tunel_actual.getDestino());
				}
				conexion_parcial.remove(conexion_parcial.size() - 1);
				km_parcial -= tunel_actual.getEtiqueta();
				estado.addUnion(tunel_actual.getOrigen(), tunel_actual.getDestino());
			} else {
				_back(estado, conexion_parcial, conexion_final, km_parcial);
			}

			estado.addTunel(tunel_actual);
		}

	}

	public int getKm() {
		return this.km;
	}

	public int getItereaciones() {
		return this.iteraciones;
	}

}
