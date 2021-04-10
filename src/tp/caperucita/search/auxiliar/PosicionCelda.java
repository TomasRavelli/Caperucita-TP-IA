package tp.caperucita.search.auxiliar;

public class PosicionCelda {
	
	private Integer posicionFila;
	private Integer posicionColumna;
	
	public PosicionCelda(Integer fila, Integer columna) {
		this.posicionFila = fila;
		this.posicionColumna = columna;
	}
	
	public PosicionCelda() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPosicionFila() {
		return posicionFila;
	}
	public void setPosicionFila(Integer posicionFila) {
		this.posicionFila = posicionFila;
	}
	public Integer getPosicionColumna() {
		return posicionColumna;
	}
	public void setPosicionColumna(Integer posicionColumna) {
		this.posicionColumna = posicionColumna;
	}
	
	public PosicionCelda clone() {
		return new PosicionCelda(this.posicionFila, this.posicionColumna);
	}

	@Override
	public String toString() {
		return "PosicionCelda{\n" +
				"posicionFila=" + posicionFila + "\n" +
				", posicionColumna=" + posicionColumna + "\n" +
				'}';
	}
}
