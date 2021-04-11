package tp.caperucita.search.caperucita;

import java.util.Arrays;
import java.util.Objects;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class CaperucitaEstado extends SearchBasedAgentState {

	private Integer cantidadVidas;
	private Integer cantidadDulces;
	private PosicionCelda posicionActual;
	private ContenidoCelda[][] mapaConocidoAgente;
	
	//Segun el codigo de FAIA, el Estado de caperucita tiene que tener como atributo  las percepciones que recibe del ambiente
	//o tendrian que actualizarse varibles dentro de esta clase.
	private CaperucitaPercepcion percepcion;
	//No sabemos si esta bien, para actualizar el estado deberiamos tener las mismas variables de la percepcion dentro de esta clase.
	
	
	
	public CaperucitaEstado() {
		this.mapaConocidoAgente = new ContenidoCelda[9][14];
		this.posicionActual = new PosicionCelda();
		this.initState();
	}
	

	public Integer getCantidadVidas() {
		return cantidadVidas;
	}
	public void setCantidadVidas(Integer cantidadVidas) {
		this.cantidadVidas = cantidadVidas;
	}
	public Integer getCantidadDulces() {
		return cantidadDulces;
	}
	public void setCantidadDulces(Integer cantidadDulces) {
		this.cantidadDulces = cantidadDulces;
	}
	public PosicionCelda getPosicionActual() {
		return posicionActual;
	}
	public void setPosicionActual(PosicionCelda posicionActual) {
		this.posicionActual = posicionActual;
	}
	public ContenidoCelda[][] getMapaConocidoAgente() {
		return mapaConocidoAgente;
	}
	public void setMapaConocidoAgente(ContenidoCelda[][] mapaConocidoAgente) {
		this.mapaConocidoAgente = mapaConocidoAgente;
	}
	
	public void actualizarMapaConocidoAgente(PosicionCelda celda, ContenidoCelda contenido) {
		this.mapaConocidoAgente[celda.getPosicionFila()][celda.getPosicionColumna()] = contenido;
	}
	

	public CaperucitaPercepcion getPercepcion() {
		return percepcion;
	}


	public void setPercepcion(CaperucitaPercepcion percepcion) {
		this.percepcion = percepcion;
	}


	@Override
	public void initState() {
		this.cantidadVidas = 3;
		this.cantidadDulces = 0;
		this.posicionActual.setPosicionFila(0);
		this.posicionActual.setPosicionColumna(0);
		
		for (int i = 0 ; i<9; i++) {
			for(int j=0;j<14; j++) {
				this.mapaConocidoAgente[i][j] = ContenidoCelda.DESCONOCIDO;
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		//TODO creo que el equals no debería tener en cuenta la cantidad de dulces y vidas.
		//porque lo usa el framework para saber si el ya paso por este estado, si ya pasoi pero junto dulces en el medio,
		// va a creer que puede volver a este estado y se va a provocar un bucle infinito
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CaperucitaEstado that = (CaperucitaEstado) o;
		return Objects.equals(cantidadVidas, that.cantidadVidas) && Objects.equals(cantidadDulces, that.cantidadDulces) && Objects.equals(posicionActual, that.posicionActual) && Arrays.equals(mapaConocidoAgente, that.mapaConocidoAgente) && Objects.equals(percepcion, that.percepcion);
	}

	@Override
	public SearchBasedAgentState clone() {
		
		CaperucitaEstado newState = new CaperucitaEstado();
    	
    	
		newState.setCantidadVidas(this.cantidadVidas);
		newState.setCantidadDulces(this.cantidadDulces);
		newState.setPercepcion(this.percepcion);
		
		//Los atributos que son objetos se pasan por
    	//referencia; luego, es necesario clonarlos
		newState.setPosicionActual(this.posicionActual.clone());
    	
		ContenidoCelda[][] newMapaConocidoAgente = new ContenidoCelda[9][14];
		
		for (int i = 0 ; i<9; i++) {
			for(int j=0;j<14; j++) {
				newMapaConocidoAgente[i][j]= mapaConocidoAgente[i][j];
			}
		}
		newState.setMapaConocidoAgente(newMapaConocidoAgente);

    	
        return newState;
        
	}


	@Override
	public void updateState(Perception p) {
		
		//Esto e ejecuta en el see(Percepcion) de GoalBasedAgentSimulator, cada vez que percibe el agente.
		//TODO Acá hay que actualizar el estado de caperucita (principalmente el mapa), con los datos que hay en la percepción
		
		percepcion=(CaperucitaPercepcion)p;
		
	}

	@Override
	public String toString() {

		String mapaString = "\n";

		for(int fila = 0; fila < mapaConocidoAgente.length; fila++){
			for(int col = 0; col < mapaConocidoAgente[fila].length; col++){
				mapaString += "|";
				if(posicionActual.getPosicionFila() == fila && posicionActual.getPosicionColumna() == col){
					mapaString += "CAPERUCITA "; //Espacio al final para que tenga 11 caracteres, como DESCONOCIDO.
				}else {
					mapaString += mapaConocidoAgente[fila][col].toString() + "";
				}
				mapaString += "| ";
			}
			mapaString += "\n";
		}

		return "CaperucitaEstado{" +
				"cantidadVidas=" + cantidadVidas +
				", cantidadDulces=" + cantidadDulces +
				", posicionActual=" + posicionActual +
				", mapaConocidoAgente=" + mapaString +
				", percepcion=" + percepcion +
				'}';
	}

	//Estos no pueden ser variables, porque hay que calcularlo según la posición de caperucita.
	public int getCantidadCeldasArriba() {
		//TODO calcular la cantidad de celdas libres arriba según la posición de caperucita y el mapa.
		return 0;
	}
	public int getCantidadCeldasAbajo() {
		//TODO calcular la cantidad de celdas libres abajo según la posición de caperucita y el mapa.
		return 0;
	}
	public int getCantidadCeldasIzquierda() {
		//TODO calcular la cantidad de celdas libres a la izquierda según la posición de caperucita y el mapa.
		return 0;
	}
	public int getCantidadCeldasDerecha() {
		//TODO calcular la cantidad de celdas libres a la derecha según la posición de caperucita y el mapa.
		return 0;
	}

	public int getCantidadDulcesArriba() {
		//TODO calcular la cantidad de dulces arriba según el mapa y la posición de caperucita.
		return 0;
	}
	public int getCantidadDulcesAbajo() {
		//TODO calcular la cantidad de dulces abajo según el mapa y la posición de caperucita.
		return 0;
	}
	public int getCantidadDulcesIzquierda() {
		//TODO calcular la cantidad de dulces a la izquierda según el mapa y la posición de caperucita.
		return 0;
	}
	public int getCantidadDulcesDerecha() {
		//TODO calcular la cantidad de dulces a la derecha según el mapa y la posición de caperucita.
		return 0;
	}

	public boolean getHayLoboArriba() {
		//TODO, al igual que los otros, hay que ver si el lobo está arriba de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return false;
	}
	public boolean getHayLoboAbajo() {
		//TODO, al igual que los otros, hay que ver si el lobo está abajo de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return false;
	}
	public boolean getHayLoboIzquierda() {
		//TODO, al igual que los otros, hay que ver si el lobo está a la izquierda de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return false;
	}
	public boolean getHayLoboDerecha() {
		//TODO, al igual que los otros, hay que ver si el lobo está a la derecha de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return false;
	}
}
