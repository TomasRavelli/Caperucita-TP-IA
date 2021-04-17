package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;
import tp.caperucita.search.caperucita.CaperucitaPercepcion;

public class IrArribaYJuntarDulce extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasArriba = estadoCaperucita.getCantidadCeldasArriba();
		int cantDulces = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLobo = estadoCaperucita.getHayLoboArriba();
		// La nueva posición es "cantidad de celdas libres" arriba de caperucita (arriba es restar filas)
		//No hay que usar la percepción acá, porque este método se llama varias veces sin percibir antes, entonces la percepción no es válida.
		//Hay que usar el mapa de caperucita
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() - cantCeldasArriba);
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //la columna no cambia.

		if(cantidadVidas > 0 && cantCeldasArriba > 0 && cantDulces > 0) {
			//Hay celdas arriba y hay dulces, se cumplen las precondiciones.
			if (hayLobo) {
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
		// TODO Ver como calcular el costo
		// Perder una vida aumenta el costo?
		// Juntar dulces lo baja?
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		int cantCeldasArriba = estadoCaperucita.getCantidadCeldasArriba();
		int cantDulces = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		boolean hayLobo = estadoCaperucita.getHayLoboArriba();
		// La nueva posición es "cantidad de celdas libres" arriba de caperucita (arriba es restar filas)
		//No hay que usar la percepción acá, porque este método se llama varias veces sin percibir antes, entonces la percepción no es válida.
		//Hay que usar el mapa de caperucita
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() - cantCeldasArriba);
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //la columna no cambia.

		if(cantidadVidas > 0 && cantCeldasArriba > 0 && cantDulces > 0) {
			//Hay celdas arriba y hay dulces, se cumplen las precondiciones.
			if (hayLobo) {
				//Hay lobo pierdo una vida
				estadoCaperucita.setCantidadDulces(0);
				//TODO cambiar posición 0,0 por la posición inicial.
				estadoCaperucita.setPosicionActual(new PosicionCelda(0, 0));
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas()-1);
				//Si ahy lobo el estado del ambiente no cambia, pero hay que setearle la nueva posicion de caperucita.
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());
				
				//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
				return estadoAmbiente;
			}
			//No hay lobo, me puedo mover y juntar dulces

			//actualizo el estado de caperucita
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulces);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			
			//Actualizar el estado del ambiente.
			estadoAmbiente.eliminarDulcesEnCamino(posicionActual, nuevaPosicion);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

			//TODO Entrega-19-14. setear la posicion del lobo en el ambiente
			return estadoAmbiente;
		}

		//Si no se cumplen las precondiciones esta acción no es válida.
		return null;
	}

	@Override
	public String toString() {
		return "Ir arriba y juntar dulce";
	}

}
