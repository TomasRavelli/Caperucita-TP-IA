package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrAbajoYJuntarDulce extends SearchAction{
	
	private int cantidadCeldasAbajo;
	private int cantidadDulces;
	private boolean hayLobo;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLobo = estadoCaperucita.getHayLoboAbajo();

		if(cantidadVidas > 0 && cantidadCeldasAbajo > 0 && cantidadDulces > 0){
			
			//No se verifica que este el lobo, porque si el lobo está el algoritmo en Search.java no funciona porque solo retorna acciones que le permitan a caperucita llegar al objetivo, y mientras es buscando, el lobo no cambia su posicion.
			//Entonces, mientras esta realizando busqueda, caperucita piensa que el lobo no esta en ningun lado, pero en el mundo real aparecera y perdera una vida.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas hacia abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			//Junta los dulces
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces()+cantidadDulces);

			//Actualizamos el mapa de caperucita para que, cuando esta realizando la busqueda, no vuelva a elegir este camino ya que tendra un costo muy bajo porque caperucita sigue pensando que el dulce sigue estando.
			estadoCaperucita.eliminarDulcesEnCamino(nuevaPosicion, posicionActual);
			return estadoCaperucita;
		}

		//No se cumplen las precondiciones, esta acción no es válida.
		return null;
	}

	@Override
	public Double getCost() {
		
		//TODO corregir en los otros.
		return (double)cantidadCeldasAbajo-(cantidadDulces*((!hayLobo)?1:0));
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLobo = estadoCaperucita.getHayLoboAbajo();

		if(cantidadVidas > 0 && cantidadCeldasAbajo > 0 && cantidadDulces > 0) {
			//Se cumplen las precondiciones
			if (hayLobo){
				//Está el lobo, entonces pierde una vida y vuelve al principio.
//				nuevaPosicion.setPosicionFila(0);
//				nuevaPosicion.setPosicionColumna(0);
				estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoCaperucita.setCantidadDulces(0);
				//Si ahy lobo el estado del ambiente no cambia, pero hay que setearle la nueva posicion de caperucita.
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}
			//no hay lobo, entonces se mueve y junta dulces.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas hacia abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			//Junta los dulces
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces()+cantidadDulces);

			//Elimino los dulces en el que camino que recorrió caperucita.
			estadoAmbiente.eliminarDulcesEnCamino(posicionActual, nuevaPosicion);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

			return estadoAmbiente;
		}

		//No se cumplen las precondiciones, esta acción no es válida.
		return null;
	}

	@Override
	public String toString() {
		return "Ir abajo y juntar dulce";
	}

}
