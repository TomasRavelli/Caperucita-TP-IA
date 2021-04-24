package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrDerecha extends SearchAction {
	private int cantidadCeldasLibreDerecha;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		cantidadCeldasLibreDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		PosicionCelda nuevaPosicion = new PosicionCelda();
		int cantidadDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();


		if ( cantidadVidas > 0 && cantidadCeldasLibreDerecha > 0 && cantidadDulcesDerecha == 0) {
//			if (!hayLoboDerecha) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantidadCeldasLibreDerecha);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				
				estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
				return estadoCaperucita;
//			}
//////			Esta el lobo, caperucita pierde una vida y todos los dulces.
//			nuevaPosicion.setPosicionColumna(ConfiguracionInicial.posicionInicialCaperucita.getPosicionColumna());
//			nuevaPosicion.setPosicionFila(ConfiguracionInicial.posicionInicialCaperucita.getPosicionFila());
//			estadoCaperucita.setCantidadDulces(0);
//			estadoCaperucita.setPosicionActual(nuevaPosicion);
//			estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
//			estadoCaperucita.setCosto(cantidadCeldasLibreDerecha*1.5);
//			estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
//			return estadoCaperucita;
		}

		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		cantidadCeldasLibreDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;

		if (cantidadVidas > 0 && cantidadCeldasLibreDerecha > 0 && cantidadDulcesDerecha == 0) {
			if (!hayLoboDerecha) {
				nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantidadCeldasLibreDerecha);
				nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}
			//Esta el lobo, caperucita pierde una vida y todos los dulces
			estadoCaperucita.setCantidadDulces(0);
	
			estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
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
		return (double)cantidadCeldasLibreDerecha;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ir Derecha";
	}

}
