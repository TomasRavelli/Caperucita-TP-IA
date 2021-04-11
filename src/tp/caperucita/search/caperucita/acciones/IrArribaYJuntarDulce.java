package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;
import tp.caperucita.search.caperucita.CaperucitaPercepcion;

public class IrArribaYJuntarDulce extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		CaperucitaPercepcion percepcion = estadoCaperucita.getPercepcion();
		int cantDulces = percepcion.getCantidadDulcesArriba();
		// La nueva posicion es "cantidad de cledas libres" arriba de capeucita (arriba es restar filas)
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() - percepcion.getCantidadCeldasLibresArriba());
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //la columna no cambia.

		if(cantDulces > 0) {
			if (percepcion.getHayLoboArriba()) {
				//Hay lobo pierdo una vida
				estadoCaperucita.setCantidadDulces(0);
				//TODO cambiar posición 0,0 por la posición inicial.
				estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
				return estadoCaperucita;
			}
			//No hay lobo, me puedo mover y juntar dulces
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulces);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			return estadoCaperucita;
		}

		//Si no se cumplen las precondiciones (Que haya dulces) retorno null (como en el ejemplo del pacman)
		//Creo que así le decís a faia que esta acción no se puede elegir, no es válida para el estado actual del agente
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
