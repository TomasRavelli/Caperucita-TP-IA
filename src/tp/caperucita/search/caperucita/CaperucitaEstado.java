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
	
	
	//Variables que se actualizan en updateState cada vez que se obtiene una percepcion.
	//TODO refactorizar esto, no hay que usar variables, se tienen que calcular según las posiciones de las cosas en el mapa.
	private Boolean hayLoboArriba;
	private Boolean hayLoboDerecha;
	private Boolean hayLoboAbajo;
	private Boolean hayLoboIzquierda;

	private Integer cantidadDulcesArriba;
	private Integer cantidadDulcesDerecha;
	private Integer cantidadDulcesAbajo;
	private Integer cantidadDulcesIzquierda;
	
	
	
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
		
		this.cantidadDulcesAbajo = 0;
		this.cantidadDulcesArriba = 0;
		this.cantidadDulcesDerecha = 0;
		this.cantidadDulcesIzquierda = 0;
		this.hayLoboAbajo = false;
		this.hayLoboArriba = false;
		this.hayLoboDerecha = false;
		this.hayLoboIzquierda = false;
	}

	@Override
	public boolean equals(Object o) {
		//TODO creo que el equals no debería tener en cuenta la cantidad de dulces y vidas.
		//porque lo usa el framework para saber si el ya paso por este estado, si ya pasoi pero junto dulces en el medio,
		// va a creer que puede volver a este estado y se va a provocar un bucle infinito
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CaperucitaEstado that = (CaperucitaEstado) o;
		return Objects.equals(cantidadVidas, that.cantidadVidas) && Objects.equals(cantidadDulces, that.cantidadDulces) && Objects.equals(posicionActual, that.posicionActual) && Arrays.equals(mapaConocidoAgente, that.mapaConocidoAgente);
	}

	@Override
	public SearchBasedAgentState clone() {
		
		CaperucitaEstado newState = new CaperucitaEstado();
    	
    	
		newState.setCantidadVidas(this.cantidadVidas);
		newState.setCantidadDulces(this.cantidadDulces);
		//TODO hay que clonar las variables relacionadas a la percepcion  tambien?
		
		//Los atributos que son objetos se pasan por
    	//referencia; luego, es necesario clonarlos
		newState.setPosicionActual(this.posicionActual.clone());

		//TODO cambiar tamaño del mapa hardcodeado
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
		//TODO Actualizar el mapa con las listas de celdas que va a tener la percepción
		
		CaperucitaPercepcion percepcion = (CaperucitaPercepcion) p;

		//deprecated
		actualizarMapaCaminoArriba(percepcion.getCantidadCeldasLibresArriba());
		actualizarMapaCaminoDerecha(percepcion.getCantidadCeldasLibresDerecha());
		actualizarMapaCaminoAbajo(percepcion.getCantidadCeldasLibresAbajo());
		actualizarMapaCaminoIzquierda(percepcion.getCantidadCeldasLibresIzquierda());
		this.hayLoboArriba = percepcion.getHayLoboArriba();
		this.hayLoboDerecha = percepcion.getHayLoboDerecha();
		this.hayLoboAbajo = percepcion.getHayLoboAbajo();
		this.hayLoboIzquierda = percepcion.getHayLoboIzquierda();
		this.cantidadDulcesArriba = percepcion.getCantidadDulcesArriba();
		this.cantidadDulcesDerecha = percepcion.getCantidadDulcesDerecha();
		this.cantidadDulcesAbajo = percepcion.getCantidadDulcesAbajo();
		this.cantidadDulcesIzquierda = percepcion.getCantidadDulcesIzquierda();
		
	}

	private void actualizarMapaCaminoDerecha(Integer cantidadCeldasLibresDerecha) {
		int primerCelda = this.posicionActual.getPosicionColumna(), ultimaCelda = primerCelda+cantidadCeldasLibresDerecha, fila=this.posicionActual.getPosicionFila();
		for(int i = primerCelda+1; i<=ultimaCelda;i++) {
			this.mapaConocidoAgente[fila][i] = ContenidoCelda.LIBRE;
		}
		
	}


	private void actualizarMapaCaminoIzquierda(Integer cantidadCeldasLibresIzquierda) {
		int primerCelda = this.posicionActual.getPosicionColumna(), ultimaCelda = primerCelda-cantidadCeldasLibresIzquierda, fila=this.posicionActual.getPosicionFila();
		for(int i = primerCelda-1; i>=ultimaCelda;i--) {
			this.mapaConocidoAgente[fila][i] = ContenidoCelda.LIBRE;
		}
		
	}


	private void actualizarMapaCaminoAbajo(Integer cantidadCeldasLibresAbajo) {
		int primerCelda = this.posicionActual.getPosicionFila(), ultimaCelda = primerCelda+cantidadCeldasLibresAbajo, columna=this.posicionActual.getPosicionColumna();
		for(int i = primerCelda+1; i<=ultimaCelda;i++) {
			this.mapaConocidoAgente[i][columna] = ContenidoCelda.LIBRE;
		}
		
	}


	private void actualizarMapaCaminoArriba(Integer cantidadCeldasLibresArriba) {
		
		int primerCelda = this.posicionActual.getPosicionFila(), ultimaCelda = primerCelda-cantidadCeldasLibresArriba, columna=this.posicionActual.getPosicionColumna();
		for(int i = primerCelda-1; i>=ultimaCelda;i--) {
			this.mapaConocidoAgente[i][columna] = ContenidoCelda.LIBRE;
		}
		
		
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
				'}';
	}

	//Estos no pueden ser variables, porque hay que calcularlo según la posición de caperucita.
	public int getCantidadCeldasArriba() {
		//TODO calcular la cantidad de celdas libres arriba según la posición de caperucita y el mapa.
		int cantidadCeldasLibres = 0,filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();
		
		while(filaActual>0 && mapaConocidoAgente[filaActual-1][columnaActual]==ContenidoCelda.LIBRE) {
			filaActual--;
			cantidadCeldasLibres++;
		}
		
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasAbajo() {
		//TODO calcular la cantidad de celdas libres abajo según la posición de caperucita y el mapa.
		int cantidadCeldasLibres = 0, ultimaFila = mapaConocidoAgente.length, filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		//TODO hay que sumar celdas libres mientras no llegue a un obstaculo.
		//TODO Si la próxima celda es una flor, sumo uno y corto el while. Para poder llegar a la flor pero no pasarme.
		while(filaActual<ultimaFila && mapaConocidoAgente[filaActual+1][columnaActual]==ContenidoCelda.LIBRE) {
			filaActual++;
			cantidadCeldasLibres++;
		}
	
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasIzquierda() {
		int cantidadCeldasLibres = 0,filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();
		
		
		while(columnaActual>0 && mapaConocidoAgente[filaActual][columnaActual-1]==ContenidoCelda.LIBRE) {
			columnaActual--;
			cantidadCeldasLibres++;
		}
		
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasDerecha() {
		int cantidadCeldasLibres = 0, ultimaColumna = mapaConocidoAgente[0].length, filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();
		
		while(columnaActual < ultimaColumna && mapaConocidoAgente[filaActual][columnaActual+1]==ContenidoCelda.LIBRE) {
			columnaActual++;
			cantidadCeldasLibres++;
		}
		
		return cantidadCeldasLibres;
	}

	public int getCantidadDulcesArriba() {
		//TODO calcular la cantidad de dulces arriba según el mapa y la posición de caperucita.
		return this.cantidadDulcesArriba;
	}
	public int getCantidadDulcesAbajo() {
		//TODO calcular la cantidad de dulces abajo según el mapa y la posición de caperucita.
		return this.cantidadDulcesAbajo;
	}
	public int getCantidadDulcesIzquierda() {
		//TODO calcular la cantidad de dulces a la izquierda según el mapa y la posición de caperucita.
		return this.cantidadDulcesIzquierda;
	}
	public int getCantidadDulcesDerecha() {
		//TODO calcular la cantidad de dulces a la derecha según el mapa y la posición de caperucita.
		return this.cantidadDulcesDerecha;
	}

	public boolean getHayLoboArriba() {
		//TODO, al igual que los otros, hay que ver si el lobo está arriba de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return this.hayLoboArriba;
	}
	public boolean getHayLoboAbajo() {
		//TODO, al igual que los otros, hay que ver si el lobo está abajo de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return this.hayLoboAbajo;
	}
	public boolean getHayLoboIzquierda() {
		//TODO, al igual que los otros, hay que ver si el lobo está a la izquierda de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return this.hayLoboIzquierda;
	}
	public boolean getHayLoboDerecha() {
		//TODO, al igual que los otros, hay que ver si el lobo está a la derecha de caperucita en el mapa, o ver una variable que se actualiza en updateState()
		return this.hayLoboDerecha;
	}
}
