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
		int cantidadCeldasLibreArriba = estadoCaperucita.getCantidadCeldasArriba();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual(), nuevaPosicion = new PosicionCelda();
		int cantidadDulcesArriba = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboArriba = estadoCaperucita.getHayLoboArriba();
		
		if(cantidadVidas > 0 && cantidadCeldasLibreArriba > 0 && cantidadDulcesArriba == 0) {
			if(!hayLoboArriba) {
			
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna());
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()-cantidadCeldasLibreArriba);
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				return estadoCaperucita;
			
			}
			//Esta el lobo, caperucita piede una vida y todos los dulces.
			nuevaPosicion.setPosicionColumna(0);
			nuevaPosicion.setPosicionFila(0);
			estadoCaperucita.setCantidadDulces(0);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
			return estadoCaperucita;
		
		}
		return null;
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
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesArriba = estadoCaperucita.getCantidadDulcesArriba(), cantidadCeldasLibresArriba = estadoCaperucita.getCantidadCeldasArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboArriba = estadoCaperucita.getHayLoboArriba();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(cantidadVidas > 0 && cantidadCeldasLibresArriba > 0 && cantidadDulcesArriba == 0) {
			if(!hayLoboArriba) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna());
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()-cantidadCeldasLibresArriba);
				//TODO Cuidado! la nueva posición puede tener valores negativos is caperucita está en el 0. Hay que validar las precondiciones para evitar esto
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
				
				//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
				return estadoAmbiente;
			}
			//Esta el lobo, caperucita pierde una vida y todos los dulces
			estadoCaperucita.setCantidadDulces(0);
			posicionActual.setPosicionColumna(0);
			posicionActual.setPosicionFila(0);
			estadoCaperucita.setPosicionActual(posicionActual);
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
			
			//TODO Entrega-19-14. Setear a estadoAmbiente la nueva posicion del lobo
			return estadoAmbiente;
		
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Ir arriba";
	}

}
