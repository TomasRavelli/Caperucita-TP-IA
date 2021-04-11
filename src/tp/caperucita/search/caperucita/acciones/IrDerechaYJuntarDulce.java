package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrDerechaYJuntarDulce extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		int cantDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();

		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantCeldasDerecha);

		if (cantCeldasDerecha > 0 && cantDulcesDerecha > 0) {
			if (hayLoboDerecha) {
				estadoCaperucita.setCantidadDulces(0);
				estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				return estadoCaperucita;
			}

			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulcesDerecha);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			return estadoCaperucita;
		}

		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		int cantDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		boolean hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();

		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantCeldasDerecha);

		if (cantCeldasDerecha > 0 && cantDulcesDerecha > 0) {
			if (hayLoboDerecha) {
				estadoCaperucita.setCantidadDulces(0);
				estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
				return estadoAmbiente;
			}

			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulcesDerecha);
			estadoCaperucita.setPosicionActual(nuevaPosicion);

			estadoAmbiente.eliminarDulcesEnCamino(posicionActual, nuevaPosicion);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
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
		return "Ir derecha y juntar dulces";
	}

}
