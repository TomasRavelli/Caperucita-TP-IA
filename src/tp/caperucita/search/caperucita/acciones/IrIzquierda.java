package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrIzquierda extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)s;
		int cantidadCeldasLibreIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual(), nuevaPosicion = new PosicionCelda();
		int cantidadDulcesIzquierda = estadoCaperucita.getCantidadDulcesIzquierda();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
		
		if(cantidadCeldasLibreIzquierda > 0 && cantidadDulcesIzquierda==0) {
			if(!hayLoboIzquierda) {
				
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()-cantidadCeldasLibreIzquierda);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
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

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)ast;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesIzquierda = estadoCaperucita.getCantidadDulcesIzquierda(), cantidadCeldasLibresIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(cantidadCeldasLibresIzquierda > 0 && cantidadDulcesIzquierda == 0) {
			if(!hayLoboIzquierda) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()-cantidadCeldasLibresIzquierda);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
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
			
			//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
			return estadoAmbiente;
		
		}
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ir izquierda";
	}

}
