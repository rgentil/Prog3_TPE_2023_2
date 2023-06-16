package tudai.prog3.colecciones;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(destino, etiqueta, origen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tunel other = (Tunel) obj;
		return Objects.equals(destino, other.destino) && Objects.equals(etiqueta, other.etiqueta)
				&& Objects.equals(origen, other.origen);
	}

}
