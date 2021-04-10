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
		
		
		if(mapaAgente[estadoCaperucita.getPosicionActual().getPosicionFila()][estadoCaperucita.getPosicionActual().getPosicionColumna()].equals(ContenidoCelda.FLORES) && estadoCaperucita.getCantidadVidas()>0) {
			objetivoCumplido = true;
		}
		return objetivoCumplido;
	}

}
