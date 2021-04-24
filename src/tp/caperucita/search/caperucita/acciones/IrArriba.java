package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrArriba extends SearchAction {
	
	private int cantidadCeldasLibreArriba;
	/**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)s;
		cantidadCeldasLibreArriba = estadoCaperucita.getCantidadCeldasArriba();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual(), nuevaPosicion = new PosicionCelda();
		int cantidadDulcesArriba = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboArriba = estadoCaperucita.getHayLoboArriba();
	
		if(cantidadVidas > 0 && cantidadCeldasLibreArriba > 0 && cantidadDulcesArriba == 0) {
//			if(!hayLoboArriba) {
			
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna());
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()-cantidadCeldasLibreArriba);
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
				return estadoCaperucita;
//			
//			}
//			//Esta el lobo, caperucita piede una vida y todos los dulces.
//			nuevaPosicion.setPosicionColumna(ConfiguracionInicial.posicionInicialCaperucita.getPosicionColumna());
//			nuevaPosicion.setPosicionFila(ConfiguracionInicial.posicionInicialCaperucita.getPosicionFila());
//			estadoCaperucita.setCantidadDulces(0);
//			estadoCaperucita.setPosicionActual(nuevaPosicion);
//			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
//			estadoCaperucita.setCosto(cantidadCeldasLibreArriba*1.5);
//			estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
//			return estadoCaperucita;
//		
		}
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return (double) cantidadCeldasLibreArriba;
	}

	
	 /**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)ast;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesArriba = estadoCaperucita.getCantidadDulcesArriba();
		cantidadCeldasLibreArriba = estadoCaperucita.getCantidadCeldasArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboArriba = estadoCaperucita.getHayLoboArriba();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(cantidadVidas > 0 && cantidadCeldasLibreArriba > 0 && cantidadDulcesArriba == 0 ) {
			if(!hayLoboArriba) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna());
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()-cantidadCeldasLibreArriba);
				
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}
			//Esta el lobo, caperucita pierde una vida y todos los dulces
			estadoCaperucita.setCantidadDulces(0);
			
			estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
		
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
			
			return estadoAmbiente;
		
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Ir arriba";
	}

}
