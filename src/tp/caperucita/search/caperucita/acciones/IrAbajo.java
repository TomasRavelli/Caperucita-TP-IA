package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrAbajo extends SearchAction{

	private int cantidadCeldasAbajo;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		int cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLobo = estadoCaperucita.getHayLoboAbajo();
		
		if(cantidadVidas > 0 && cantidadCeldasAbajo > 0 && cantidadDulces == 0){
			//No se verifica que este el lobo, porque si el lobo está el algoritmo en Search.java no funciona porque solo retorna acciones que le permitan a caperucita llegar al objetivo, y mientras es buscando, el lobo no cambia su posicion.
			//Entonces, mientras esta realizando busqueda, caperucita piensa que el lobo no esta en ningun lado, pero en el mundo real aparecera y perdera una vida.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas haica abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			estadoCaperucita.sumarVisitaACelda(nuevaPosicion);
			return estadoCaperucita;
		}
		//No se cumplen las precondiciones, esta acción no es válida
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return (double)cantidadCeldasAbajo;
		
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantidadCeldasAbajo = estadoCaperucita.getCantidadCeldasAbajo();
		int cantidadDulces = estadoCaperucita.getCantidadDulcesAbajo();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLobo = estadoCaperucita.getHayLoboAbajo();;

		if(cantidadVidas > 0 && cantidadCeldasAbajo > 0 && cantidadDulces == 0 ){
			//Se cumplen las precondiciones
			if(hayLobo){
				//Está el lobo, entonces pierde una vida y vuelve al principio.
				estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoCaperucita.setCantidadDulces(0);
			
				//Actualizo el ambiente
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
				return estadoAmbiente;
			}
			//no hay lobo, entonces se mueve.
			nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //La columna no cambia
			nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() + cantidadCeldasAbajo); //Se mueve cantidadCeldasAbajo celdas haica abajo (sumando)
			estadoCaperucita.setPosicionActual(nuevaPosicion);
		
			//Actualizo el ambiente
			estadoAmbiente.setPosicionCaperucita(nuevaPosicion);

			
			//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
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
