package tp.caperucita.search.ambiente;

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
		this.posicionCaperucita.setPosicionFila(0);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toString();
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
