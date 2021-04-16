package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrDerecha extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		int cantidadCeldasLibreDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		PosicionCelda nuevaPosicion = new PosicionCelda();
		int cantidadDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();

		if (cantidadCeldasLibreDerecha > 0 && cantidadDulcesDerecha == 0) {
			if (!hayLoboDerecha) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantidadCeldasLibreDerecha);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				return estadoCaperucita;
			}
			//Esta el lobo, caperucita pierde una vida y todos los dulces.
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
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		int cantidadCeldasLibreDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;

		if (cantidadCeldasLibreDerecha > 0 && cantidadDulcesDerecha == 0) {
			if (!hayLoboDerecha) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantidadCeldasLibreDerecha);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
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
			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
			
			//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
			return estadoAmbiente;
		}

		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ir Derecha";
	}

}
