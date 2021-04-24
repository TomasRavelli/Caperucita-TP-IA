package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrIzquierdaYJuntarDulce extends SearchAction {
	private int cantCeldasIzquierda;
	private int cantDulces;
	private boolean hayLoboIzquierda;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		cantDulces = estadoCaperucita.getCantidadDulcesIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
		
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila()); //la fila no cambia.
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()  - cantCeldasIzquierda); 

		if(cantidadVidas > 0 && cantCeldasIzquierda > 0 && cantDulces > 0) {
			//Hay celdas a la izquierda y hay dulces, se cumplen las precondiciones.
			if (hayLoboIzquierda) {
//				//Hay lobo, pierdo una vida
//				estadoCaperucita.setCantidadDulces(0);
//				//TODO cambiar posición 0,0 por la posición inicial.
//				estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
//				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
//				return estadoCaperucita;
				cantDulces = 0;
			}
			//No hay lobo, me puedo mover y juntar dulces
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulces);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			//Actualizamos el mapa de caperucita para que, cuando esta realizando la busqueda, no vuelva a elegir este camino ya que tendra un costo muy bajo porque caperucita sigue pensando que el dulce sigue estando.
			estadoCaperucita.eliminarDulcesEnCamino(nuevaPosicion, posicionActual);
			return estadoCaperucita;
		}

		//Si no se cumplen las precondiciones (Que haya dulces) retorno null (como en el ejemplo del pacman)
		//Creo que así le decís a faia que esta acción no se puede elegir, no es válida para el estado actual del agente
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return (double)cantCeldasIzquierda-(cantDulces*(hayLoboIzquierda?1:0));
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasIzquierda = estadoCaperucita.getCantidadCeldasIzquierda();
		cantDulces = estadoCaperucita.getCantidadDulcesIzquierda();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLoboIzquierda = estadoCaperucita.getHayLoboIzquierda();
	
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());//la fila no cambia.

		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() - cantCeldasIzquierda); 
		
		if(cantidadVidas > 0 && cantCeldasIzquierda > 0 && cantDulces > 0) {
			//Hay celdas a la izquierda y hay dulces, se cumplen las precondiciones.
			if (hayLoboIzquierda) {
				//Hay lobo pierdo una vida
				cantDulces = 0;
				estadoCaperucita.setCantidadDulces(0);
				//TODO cambiar posición 0,0 por la posición inicial.
				estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
				//Si ahy lobo el estado del ambiente no cambia, pero hay que setearle la nueva posicion de caperucita.
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}
			//No hay lobo, me puedo mover y juntar dulces

			//actualizo el estado de caperucita
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulces);
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
