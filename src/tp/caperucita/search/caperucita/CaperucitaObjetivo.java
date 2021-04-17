package tp.caperucita.search.caperucita;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import tp.caperucita.search.auxiliar.ContenidoCelda;

public class CaperucitaObjetivo extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) agentState;
		boolean objetivoCumplido = false;

		ContenidoCelda[][] mapaAgente = estadoCaperucita.getMapaConocidoAgente();


		int posicionFila = estadoCaperucita.getPosicionActual().getPosicionFila();
		int posicionColumna = estadoCaperucita.getPosicionActual().getPosicionColumna();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		return mapaAgente[posicionFila][posicionColumna].equals(ContenidoCelda.FLORES) && cantidadVidas > 0;
	}
}
