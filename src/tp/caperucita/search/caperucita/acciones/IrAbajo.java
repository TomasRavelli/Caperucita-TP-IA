package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrAbajo extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		int cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		boolean hayLobo = estadoCaperucita.getHayLoboAbajo();

		if(cantidadCeldasAbajo > 0 && cantidadDulces == 0){
			//Se cumplen las precondiciones
			if(hayLobo){
				//Está el lobo, entonces pierde una vida y vuelve al principio.
				nuevaPosicion.setPosicionFila(0);
				nuevaPosicion.setPosicionColumna(0);
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoCaperucita.setCantidadDulces(0);
				return estadoCaperucita;
			}
			//no hay lobo, entonces se mueve.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas haica abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			return estadoCaperucita;
		}
		//No se cumplen las precondiciones, esta acción no es válida
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		int cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		boolean hayLobo = estadoCaperucita.getHayLoboAbajo();;

		if(cantidadCeldasAbajo > 0 && cantidadDulces == 0){
			//Se cumplen las precondiciones
			if(hayLobo){
				//Está el lobo, entonces pierde una vida y vuelve al principio.
				nuevaPosicion.setPosicionFila(0);
				nuevaPosicion.setPosicionColumna(0);
				estadoCaperucita.setPosicionActual(nuevaPosicion);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoCaperucita.setCantidadDulces(0);
				//Actualizo el ambiente
				estadoAmbiente.setPosicionCaperucita(nuevaPosicion);

				return estadoAmbiente;
			}
			//no hay lobo, entonces se mueve.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas haica abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			//Actualizo el ambiente
			estadoAmbiente.setPosicionCaperucita(nuevaPosicion);

			return estadoAmbiente;
		}
		//No se cumplen las precondiciones, esta acción no es válida
		return null;
	}

	@Override
	public String toString() {
		return "Ir abajo";
	}

}
