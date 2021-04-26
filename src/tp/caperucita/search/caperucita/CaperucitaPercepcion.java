package tp.caperucita.search.caperucita;


import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import tp.caperucita.search.ambiente.AmbienteCaperucita;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class CaperucitaPercepcion extends Perception {

	
	private List<ContenidoCelda> celdasArriba;
	private List<ContenidoCelda> celdasAbajo;
	private List<ContenidoCelda> celdasIzquierda;
	private List<ContenidoCelda> celdasDerecha;
	

	public CaperucitaPercepcion() {
	}

	public CaperucitaPercepcion(Agent agent, Environment environment) {
		super(agent, environment);
	}
	
	@Override
	public void initPerception(Agent agentIn, Environment environmentIn) {

		Caperucita agent = (Caperucita) agentIn;
        AmbienteCaperucita ambiente = (AmbienteCaperucita) environmentIn;
        AmbienteEstado environmentState = ambiente.getEnvironmentState();
        ContenidoCelda[][] mapaAmbiente = environmentState.getMapaAmbiente();
        PosicionCelda posicionCaperucita = environmentState.getPosicionCaperucita();

        //movi esto a otro método para usarlo en el ambiente.getPercept()
        contarCeldasLibresYDulces(mapaAmbiente, posicionCaperucita);
      
	}


	public List<ContenidoCelda> getCeldasArriba(){
		return celdasArriba;
	}
	public List<ContenidoCelda> getCeldasAbajo(){
		return celdasAbajo;
	}
	public List<ContenidoCelda> getCeldasIzquierda(){
		return celdasIzquierda;
	}
	public List<ContenidoCelda> getCeldasDerecha(){
		return celdasDerecha;
	}

	public void setCeldasArriba(List<ContenidoCelda> celdasArriba) {
		this.celdasArriba = celdasArriba;
	}

	public void setCeldasAbajo(List<ContenidoCelda> celdasAbajo) {
		this.celdasAbajo = celdasAbajo;
	}

	public void setCeldasIzquierda(List<ContenidoCelda> celdasIzquierda) {
		this.celdasIzquierda = celdasIzquierda;
	}

	public void setCeldasDerecha(List<ContenidoCelda> celdasDerecha) {
		this.celdasDerecha = celdasDerecha;
	}

	public void contarCeldasLibresYDulces(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita){
		
		//Entrega-19-14. Implementacion de las percepciones.
		//inicializar parametros
		celdasArriba = new ArrayList<>();
		celdasDerecha = new ArrayList<>();
		celdasAbajo = new ArrayList<>();
		celdasIzquierda = new ArrayList<>();
		
		//setear los atributos de la percepcion. Llenar las listas con que hay en cada direccion desde la posicion actual de caperucita.
		calcularCeldasArriba(mapaAmbiente, posicionCaperucita);
		calcularCeldasDerecha(mapaAmbiente, posicionCaperucita);
		calcularCeldasAbajo(mapaAmbiente, posicionCaperucita);
		calcularCeldasIzquierda(mapaAmbiente, posicionCaperucita);
	}

	private void calcularCeldasArriba(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita ){
		
		int filaActual = posicionCaperucita.getPosicionFila();
		int columnaActual = posicionCaperucita.getPosicionColumna();
		//Empiezo a ver desde la celda actual porque el lobo puede estar en la misma celda que caperucita, y así va a ver al lobo en todos los caminos.
		for(int i = filaActual; i >= 0; i-- ) {
			celdasArriba.add(mapaAmbiente[i][columnaActual]);
		}
	}
	private void calcularCeldasAbajo(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita ){
		
		int filaActual = posicionCaperucita.getPosicionFila();
		int columnaActual = posicionCaperucita.getPosicionColumna();
		int ultimaFila = mapaAmbiente.length-1;
		
		for(int i = filaActual; i <= ultimaFila; i++ ) {
			celdasAbajo.add(mapaAmbiente[i][columnaActual]);
		}
	}
	private void calcularCeldasIzquierda(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita ){
		
		int filaActual = posicionCaperucita.getPosicionFila();
		int columnaActual = posicionCaperucita.getPosicionColumna();
	
		for(int i = columnaActual; i >= 0; i-- ) {
			celdasIzquierda.add(mapaAmbiente[filaActual][i]);
		}
	}
	private void calcularCeldasDerecha(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita ){
		
		int filaActual = posicionCaperucita.getPosicionFila();
		int columnaActual = posicionCaperucita.getPosicionColumna();
		int ultimaColumna = mapaAmbiente[0].length-1;
		
		for(int i = columnaActual; i <= ultimaColumna; i++ ) {
			celdasDerecha.add(mapaAmbiente[filaActual][i]);
		}
	}

	@Override
	public String toString() {
		return "CaperucitaPercepcion{" +
				"\nceldasArriba=" + celdasArriba +
				"\n, celdasAbajo=" + celdasAbajo +
				"\n, celdasIzquierda=" + celdasIzquierda +
				"\n, celdasDerecha=" + celdasDerecha +
				"\n} fin CaperucitaPercepcion";
	}
}
