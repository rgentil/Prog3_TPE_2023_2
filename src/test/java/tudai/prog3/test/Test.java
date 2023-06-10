package tudai.prog3.test;

import tudai.pro3.util.CSVReader;

public class Test {
	public static void main(String[] args) {

		String path = "src/main/recursos/datasets/";
		CSVReader reader = new CSVReader(path);
		reader.read();

	}
}
