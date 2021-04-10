package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrArriba extends SearchAction {

	/**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)s;
		PosicionCelda nuevaPosicion = new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila()+estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(),estadoCaperucita.getPosicionActual().getPosicionColumna());
		int cantidadDulcesArriba = estadoCaperucita.getPercepcion().getCantidadDulcesArriba();
		boolean hayLoboArriba = estadoCaperucita.getPercepcion().getHayLoboArriba();
		
		if(!hayLoboArriba) {
			if(cantidadDulcesArriba==0) {
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				for(int i = 0; i<=estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(); i++) {
					estadoCaperucita.actualizarMapaConocidoAgente(new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila()+i, estadoCaperucita.getPosicionActual().getPosicionColumna()), ContenidoCelda.CONOCIDO);
				}
			}
		}
		else {
			estadoCaperucita.setCantidadDulces(0);
			estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
		}
		return estadoCaperucita;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	
	 /**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)ast;
		PosicionCelda nuevaPosicion = new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila()+estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(),estadoCaperucita.getPosicionActual().getPosicionColumna());
		int cantidadDulcesArriba = estadoCaperucita.getPercepcion().getCantidadDulcesArriba();
		boolean hayLoboArriba = estadoCaperucita.getPercepcion().getHayLoboArriba();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(!hayLoboArriba) {
			if(cantidadDulcesArriba==0) {
				
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			for(int i = 0; i<=estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(); i++) {
				estadoCaperucita.actualizarMapaConocidoAgente(new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila()+i, estadoCaperucita.getPosicionActual().getPosicionColumna()), ContenidoCelda.CONOCIDO);
				
//				Esto va en IrArribaYJuntarDulce
//				//Si hay dulce en la celda actual, lo consume				
//				if(estadoAmbiente.getMapaAmbiente()[estadoCaperucita.getPosicionActual().getPosicionFila()+i][estadoCaperucita.getPosicionActual().getPosicionColumna()].equals(ContenidoCelda.DULCE)){
//					ContenidoCelda[][] mapaAmbienteActualizado = estadoAmbiente.getMapaAmbiente();
//					mapaAmbienteActualizado[estadoCaperucita.getPosicionActual().getPosicionFila()+i][estadoCaperucita.getPosicionActual().getPosicionColumna()] = ContenidoCelda.LIBRE; 
//					estadoAmbiente.setMapaAmbiente(mapaAmbienteActualizado);
//				}
			}
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
			}
		}
		else {
			estadoCaperucita.setCantidadDulces(0);
			estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
		}
		return est;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
