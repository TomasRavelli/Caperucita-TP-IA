package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;
import tp.caperucita.search.caperucita.CaperucitaPercepcion;

public class IrArribaYJuntarDulce extends SearchAction{

	private int cantCeldasArriba;
	private int cantDulces;
	private boolean hayLobo;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasArriba = estadoCaperucita.getCantidadCeldasArriba();
		cantDulces = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLobo = estadoCaperucita.getHayLoboArriba();
		// La nueva posición es "cantidad de celdas libres" arriba de caperucita (arriba es restar filas)
		//No hay que usar la percepción acá, porque este método se llama varias veces sin percibir antes, entonces la percepción no es válida.
		//Hay que usar el mapa de caperucita
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() - cantCeldasArriba);
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //la columna no cambia.

		if(cantidadVidas > 0 && cantCeldasArriba > 0 && cantDulces > 0) {
			//No se verifica que este el lobo, porque si el lobo está el algoritmo en Search.java no funciona porque solo retorna acciones que le permitan a caperucita llegar al objetivo, y mientras es buscando, el lobo no cambia su posicion.
			//Entonces, mientras esta realizando busqueda, caperucita piensa que el lobo no esta en ningun lado, pero en el mundo real aparecera y perdera una vida.

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
		// TODO Ver como calcular el costo
		// Perder una vida aumenta el costo?
		// Juntar dulces lo baja?
		return (double) cantCeldasArriba-(cantDulces*(hayLobo?1:0));
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda(), posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasArriba = estadoCaperucita.getCantidadCeldasArriba();
		cantDulces = estadoCaperucita.getCantidadDulcesArriba();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLobo = estadoCaperucita.getHayLoboArriba();
		// La nueva posición es "cantidad de celdas libres" arriba de caperucita (arriba es restar filas)
		//No hay que usar la percepción acá, porque este método se llama varias veces sin percibir antes, entonces la percepción no es válida.
		//Hay que usar el mapa de caperucita
		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila() - cantCeldasArriba);
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna()); //la columna no cambia.

		if(cantidadVidas > 0 && cantCeldasArriba > 0 && cantDulces > 0) {
			//Hay celdas arriba y hay dulces, se cumplen las precondiciones.
			if (hayLobo) {
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
		return "Ir arriba y juntar dulce";
	}

}
