package tudai.prog3.colecciones;

public class Tunel {

	private Integer origen;
	private Integer destino;
	private Integer etiqueta;

	public Tunel(Integer origen, Integer destino, Integer etiqueta) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.etiqueta = etiqueta;
	}

	public Integer getOrigen() {
		return origen;
	}

	public Integer getDestino() {
		return destino;
	}

	public Integer getEtiqueta() {
		return etiqueta;
	}

}
