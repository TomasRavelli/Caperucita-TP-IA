package tp.caperucita.search.auxiliar;

import java.util.Objects;

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
				"} fin PosicionCelda";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PosicionCelda that = (PosicionCelda) o;
		return posicionFila.equals(that.posicionFila) && posicionColumna.equals(that.posicionColumna);
	}

}
