package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class IrDerechaYJuntarDulce extends SearchAction{
	private int  cantCeldasDerecha;
	private int cantDulcesDerecha;
	private boolean hayLoboDerecha;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		cantDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();

		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantCeldasDerecha);

		if (cantidadVidas > 0 && cantCeldasDerecha > 0 && cantDulcesDerecha > 0) {
			
			//No se verifica que este el lobo, porque si el lobo está el algoritmo en Search.java no funciona porque solo retorna acciones que le permitan a caperucita llegar al objetivo, y mientras es buscando, el lobo no cambia su posicion.
			//Entonces, mientras esta realizando busqueda, caperucita piensa que el lobo no esta en ningun lado, pero en el mundo real aparecera y perdera una vida.
		
			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulcesDerecha);
			estadoCaperucita.setPosicionActual(nuevaPosicion);
			//Actualizamos el mapa de caperucita para que, cuando esta realizando la busqueda, no vuelva a elegir este camino ya que tendra un costo muy bajo porque caperucita sigue pensando que el dulce sigue estando.
			estadoCaperucita.eliminarDulcesEnCamino(nuevaPosicion, posicionActual);
			
			return estadoCaperucita;
		}

		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;
		AmbienteEstado estadoAmbiente = (AmbienteEstado) est;
		PosicionCelda nuevaPosicion = new PosicionCelda();
		PosicionCelda posicionActual = estadoCaperucita.getPosicionActual();
		cantCeldasDerecha = estadoCaperucita.getCantidadCeldasDerecha();
		cantDulcesDerecha = estadoCaperucita.getCantidadDulcesDerecha();
		int cantidadVidas = estadoCaperucita.getCantidadVidas();
		hayLoboDerecha = estadoCaperucita.getHayLoboDerecha();

		nuevaPosicion.setPosicionFila(posicionActual.getPosicionFila());
		nuevaPosicion.setPosicionColumna(posicionActual.getPosicionColumna() + cantCeldasDerecha);

		if (cantidadVidas > 0 && cantCeldasDerecha > 0 && cantDulcesDerecha > 0) {
			if (hayLoboDerecha) {
				cantDulcesDerecha = 0;
				estadoCaperucita.setCantidadDulces(0);
				estadoCaperucita.setPosicionActual(ConfiguracionInicial.posicionInicialCaperucita);
				estadoCaperucita.setCantidadVidas(estadoCaperucita.getCantidadVidas() - 1);
				estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

				return estadoAmbiente;
			}

			estadoCaperucita.setCantidadDulces(estadoCaperucita.getCantidadDulces() + cantDulcesDerecha);
			estadoCaperucita.setPosicionActual(nuevaPosicion);

			estadoAmbiente.eliminarDulcesEnCamino(posicionActual, nuevaPosicion);
			estadoAmbiente.setPosicionCaperucita(estadoCaperucita.getPosicionActual());

			return estadoAmbiente;
		}

		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return (double)cantCeldasDerecha-(cantDulcesDerecha*(hayLoboDerecha?1:0));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ir derecha y juntar dulces";
	}

}
