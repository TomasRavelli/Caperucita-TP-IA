package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrIzquierda extends SearchAction{
	private int cantidadCeldasLibreIzquierda;
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)s;
		cantidadCeldasLibreIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual(), nuevaPosicion = new PosicionCelda();
		int cantidadDulcesIzquierda = estadoCaperucita.getCantidadDulcesIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
	
		if(cantidadVidas > 0 && cantidadCeldasLibreIzquierda > 0 && cantidadDulcesIzquierda==0) {
//			if(!hayLoboIzquierda) {
				
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()-cantidadCeldasLibreIzquierda);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				
				estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
				return estadoCaperucita;
				
//			}
////			//Esta el lobo, caperucita piede una vida y todos los dulces.
//			nuevaPosicion.setPosicionColumna(ConfiguracionInicial.posicionInicialCaperucita.getPosicionColumna());
//			nuevaPosicion.setPosicionFila(ConfiguracionInicial.posicionInicialCaperucita.getPosicionFila());
//			estadoCaperucita.setCantidadDulces(0);
//			estadoCaperucita.setPosicionActual(nuevaPosicion);
//			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
//			estadoCaperucita.setCosto(cantidadCeldasLibreIzquierda*1.50);
//			estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
//			return estadoCaperucita;
		
		}
		
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return (double)cantidadCeldasLibreIzquierda;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado)ast;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesIzquierda = estadoCaperucita.getCantidadDulcesIzquierda();
		cantidadCeldasLibreIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
		
		AmbienteEstado estadoAmbiente =  (AmbienteEstado) est;
	
		if(cantidadVidas > 0 && cantidadCeldasLibreIzquierda > 0 && cantidadDulcesIzquierda == 0) {
			if(!hayLoboIzquierda) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()-cantidadCeldasLibreIzquierda);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());

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
		// TODO Auto-generated method stub
		return "Ir izquierda";
	}

}
