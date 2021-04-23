package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.Configuracion;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrIzquierdaYJuntarDulce extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasIquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		int cantDulces = estadoCaperucita.getCantidadDulcesIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
		
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()); //la fila no cambia.
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()  - cantCeldasIquierda); 

		if(cantidadVidas > 0 && cantCeldasIquierda > 0 && cantDulces > 0) {
			//Hay celdas a la izquierda y hay dulces, se cumplen las precondiciones.
			if (hayLoboIzquierda) {
				//Hay lobo, pierdo una vida
				estadoCaperucita.setCantidadDulces(0);
				estadoCaperucita.setPosicionActual(new PosicionCelda(Configuracion.filaInicialCaperucita, Configuracion.columnaInicialCaperucita));
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

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		int cantDulcesIzquierda = estadoCaperucita.getCantidadDulcesIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
	
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());//la fila no cambia.

		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() - cantCeldasIzquierda); 
		
		if(cantidadVidas > 0 && cantCeldasIzquierda > 0 && cantDulcesIzquierda > 0) {
			//Hay celdas a la izquierda y hay dulces, se cumplen las precondiciones.
			if (hayLoboIzquierda) {
				//Hay lobo pierdo una vida
				estadoCaperucita.setCantidadDulces(0);
				estadoCaperucita.setPosicionActual(new PosicionCelda(Configuracion.filaInicialCaperucita, Configuracion.columnaInicialCaperucita));
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
				//Si ahy lobo el estado del ambiente no cambia, pero hay que setearle la nueva posicion de caperucita.
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}
			//No hay lobo, me puedo mover y juntar dulces

			//actualizo el estado de caperucita
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulcesIzquierda);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			
			//Actualizar el estado del ambiente.
			estadoAmbiente.eliminarDulcesEnCamino(posicionActual, nuevaPosicion);
		
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

			return estadoAmbiente;
		}

		//Si no se cumplen las precondiciones esta acción no es válida.
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ir izquierda y juntar dulces";
	}

}
