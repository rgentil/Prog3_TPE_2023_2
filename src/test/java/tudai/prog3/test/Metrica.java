package tudai.prog3.test;

import java.util.List;

import tudai.prog3.algoritmo.Backtracking;
import tudai.prog3.colecciones.Estado;
import tudai.prog3.colecciones.Tunel;
import tudai.prog3.util.CSVReader;
import tudai.prog3.util.Timer;

public class Metrica {

	public Metrica() {

	}

	public void generarSolucion(int i) {
		System.out.println("Dataset " + i);
		String path = "src/main/recursos/datasets/dataset" + 0 + ".txt";
		CSVReader reader = new CSVReader(path);
		Estado estado = reader.read();
		System.out.println("Backtracking");
		Backtracking backtracking = new Backtracking();
		Timer reloj = new Timer();
		reloj.start();
		List<Tunel> caminoBack = backtracking.back(estado);
		double tiempo = reloj.stop();
		estado.imprimir(caminoBack);
		System.out.println(backtracking.getKm() + " kms");
		System.out.println("Cantidad de iteraciones: " + backtracking.getItereaciones());

		System.out.println("Iteraciones_solucion: " + backtracking.getItereacionesSolucion());

		System.out.println("Prueba time: " + tiempo);
	}
}
