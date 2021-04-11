package tp.caperucita.search.ambiente;

import java.util.Arrays;

import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class AmbienteEstado extends EnvironmentState {
	
	private PosicionCelda posicionCaperucita;
	
	//Solo te deja inicializar con constantes aca.
	private ContenidoCelda[][] mapaAmbiente= {
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE}
	};;
	
	public AmbienteEstado() {
		this.posicionCaperucita = new PosicionCelda();
		this.initState();
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		this.posicionCaperucita.setPosicionFila(0);
		this.posicionCaperucita.setPosicionColumna(0);
	}

	@Override
	public String toString() {

		String mapaString = "\n";

		for(int fila = 0; fila < mapaAmbiente.length; fila++){
			for(int col = 0; col < mapaAmbiente[fila].length; col++){
				mapaString += "|";
				if(posicionCaperucita.getPosicionFila() == fila && posicionCaperucita.getPosicionColumna() == col){
					mapaString += "CAPERUCITA "; //Espacio al final para que tenga 11 caracteres, como DESCONOCIDO.
				}else {
					mapaString += mapaAmbiente[fila][col].toString() + "";
				}
				mapaString += "| ";
			}
			mapaString += "\n";
		}

		return "AmbienteEstado{\n" +
				"posicionCaperucita=" + posicionCaperucita + "\n" +
				", mapaAmbiente=" + mapaString +
				"} fin AmbienteEstado\n";
	}

	public PosicionCelda getPosicionCaperucita() {
		return posicionCaperucita;
	}

	public void setPosicionCaperucita(PosicionCelda posicionCaperucita) {
		this.posicionCaperucita = posicionCaperucita;
	}

	public ContenidoCelda[][] getMapaAmbiente() {
		return mapaAmbiente;
	}

	public void setMapaAmbiente(ContenidoCelda[][] mapaAmbiente) {
		this.mapaAmbiente = mapaAmbiente;
	}
	
	public void actualizarMapaAmbiente(PosicionCelda celda, ContenidoCelda nuevoEstadoCelda) {
		this.mapaAmbiente[celda.getPosicionFila()][celda.getPosicionColumna()] = nuevoEstadoCelda;
	}

	
}
