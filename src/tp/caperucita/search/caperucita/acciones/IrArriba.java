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
		PosicionCelda nuevaPosicion = new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila() - estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(),estadoCaperucita.getPosicionActual().getPosicionColumna());
		int cantidadDulcesArriba = estadoCaperucita.getPercepcion().getCantidadDulcesArriba();
		boolean hayLoboArriba = estadoCaperucita.getPercepcion().getHayLoboArriba();
		
		if(!hayLoboArriba) {
			if(cantidadDulcesArriba==0) {
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				//TODO NO hay que usar la percepción acá, el estado de caperucita tiene que calcular cuántas celdas puede moverse.
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

		//TODO hay que retornar null si no se cumplen las precondiciones, así le decimos a faia que esta acción no es válida.
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
		PosicionCelda nuevaPosicion = new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila() - estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(),estadoCaperucita.getPosicionActual().getPosicionColumna());
		int cantidadDulcesArriba = estadoCaperucita.getPercepcion().getCantidadDulcesArriba();
		boolean hayLoboArriba = estadoCaperucita.getPercepcion().getHayLoboArriba();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(!hayLoboArriba) {
			if(cantidadDulcesArriba==0) {
				//TODO Cuidado! la nueva posición puede tener valores negativos is caperucita está en el 0. Hay que validar las precondiciones para evitar esto
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				//Problema! Si la cantidad de celdas libres arriba se ejecuta este for y no debería. Hay que validar las precondiciones antes.
				for(int i = 0; i<=estadoCaperucita.getPercepcion().getCantidadCeldasLibresArriba(); i++) {
					estadoCaperucita.actualizarMapaConocidoAgente(new PosicionCelda(estadoCaperucita.getPosicionActual().getPosicionFila()+i, estadoCaperucita.getPosicionActual().getPosicionColumna()), estadoAmbiente.getMapaAmbiente()[estadoCaperucita.getPosicionActual().getPosicionFila()+i][ estadoCaperucita.getPosicionActual().getPosicionColumna()]);

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
		//TODO Hay que retornar null si no se cumplen las precondiciones
		return estadoAmbiente;
	}

	@Override
	public String toString() {
		return "Ir arriba";
	}

}
