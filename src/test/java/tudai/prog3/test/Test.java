package tudai.prog3.test;

import java.util.List;

import tudai.prog3.algoritmo.Backtracking;
import tudai.prog3.colecciones.Estado;
import tudai.prog3.colecciones.Tunel;
import tudai.prog3.util.CSVReader;
import tudai.prog3.util.Timer;

/**
 * 
 * @author Lauge Guillermina, Gentil Ricardo
 *
 */
public class Test {
	public static void main(String[] args) {

		System.out.println("Dataset 1");
		String path = "src/main/recursos/datasets/dataset1.txt";
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
		System.out.println("Prueba time: " + tiempo);

//		System.out.println("\nDataset 2");
//		String path2 = "src/main/recursos/datasets/dataset2.txt";
//		CSVReader reader2 = new CSVReader(path2);
//		Grafo<Integer> red2 = reader2.read();
//		// red2.imprimirPonderado();
//		System.out.println("Backtracking");
//		Backtracking backtracking2 = new Backtracking(red2);
//		Timer reloj2 = new Timer();
//		reloj2.start();
//		List<String> caminoBack2 = backtracking2.back();
//		double tiempo2 = reloj2.stop();
//		System.out.println(caminoBack2.toString());
//		System.out.println(backtracking2.getKm() + " kms");
//		System.out.println("Cantidad de iteraciones: " + backtracking2.getItereaciones());
//		System.out.println("Prueba time: " + tiempo2);
//
//		System.out.println("\nDataset 3");
//		String path3 = "src/main/recursos/datasets/dataset3.txt";
//		CSVReader reader3 = new CSVReader(path3);
//		Grafo<Integer> red3 = reader3.read();
//		// red3.imprimirPonderado();
//
//		System.out.println("Backtracking");
//		Backtracking backtracking3 = new Backtracking(red3);
//		Timer reloj3 = new Timer();
//		reloj3.start();
//		List<String> caminoBack3 = backtracking3.back();
//		double tiempo3 = reloj3.stop();
//		System.out.println(caminoBack3.toString());
//		System.out.println(backtracking3.getKm() + " kms");
//		System.out.println("Cantidad de iteraciones: " + backtracking3.getItereaciones());
//		System.out.println("Prueba time: " + tiempo3);
	}
}
