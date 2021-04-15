package tp.caperucita.search.caperucita;

import java.util.Arrays;
import java.util.List;
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
		
		//El mapa de caperucita se actualiza con los caminos que percibe desde el ambiente.
		actualizarMapaCaminoArriba(percepcion.getCeldasArriba());
		actualizarMapaCaminoDerecha(percepcion.getCeldasDerecha());
		actualizarMapaCaminoAbajo(percepcion.getCeldasAbajo());
		actualizarMapaCaminoIzquierda(percepcion.getCeldasIzquierda());
		
	}
	
	private void actualizarMapaCaminoArriba(List<ContenidoCelda> celdasArriba) {
		//TODO debo inicializar celdasArriba como ArrayList?
		int primerFila = this.posicionActual.getPosicionFila()-1, columna=this.posicionActual.getPosicionColumna(), index = 0;

		//TODO que posicion de caperucita hay que usar? El ambiente devuelve la lista de caminos con la posicion que conoce de caperucita. Y caperucita actualiza su mapa a partir de su posicion actual. Estas dos posiciones deberian ser siempre las mismas, sino no anda.
		for(int i = primerFila-1; i>=0;i--) {
			this.mapaConocidoAgente[i][columna] = celdasArriba.get(index);
			index++;
		}
		
	}

	private void actualizarMapaCaminoDerecha(List<ContenidoCelda> celdasDerecha) {
		
		int columnaActual = this.posicionActual.getPosicionColumna(), ultimaColumna = this.mapaConocidoAgente[0].length-1, filaActual=this.posicionActual.getPosicionFila();
	
		for(int i = columnaActual+1; i<=ultimaColumna;i--) {
			this.mapaConocidoAgente[filaActual][i] = celdasDerecha.get(i);
		}
		
	}


	private void actualizarMapaCaminoIzquierda(List<ContenidoCelda> celdasIzquierda) {
		int columnaActual = this.posicionActual.getPosicionColumna(),  filaActual=this.posicionActual.getPosicionFila(), index=0;
		
		for(int i = columnaActual-1; i>=0;i--) {
			this.mapaConocidoAgente[filaActual][i] = celdasIzquierda.get(index);
			index++;
		}
		
	}


	private void actualizarMapaCaminoAbajo(List<ContenidoCelda> celdasAbajo) {
		int primerFila = this.posicionActual.getPosicionFila(), ultimaFila = mapaConocidoAgente.length-1, columnaActual=this.posicionActual.getPosicionColumna(), index = 0;
		
		for(int i = primerFila+1; i<=ultimaFila;i++) {
			this.mapaConocidoAgente[i][columnaActual] = celdasAbajo.get(index);
			index++;
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


	public int getCantidadCeldasArriba() {
		//TODO calcular la cantidad de celdas libres arriba según la posición de caperucita y el mapa.
		int cantidadCeldasLibres = 0,filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		//TODO hay que sumar celdas libres mientras no llegue a un obstaculo.
		//TODO Si la próxima celda es una flor, sumo uno y corto el while. Para poder llegar a la flor pero no pasarme.
		while(filaActual > 0 && mapaConocidoAgente[filaActual-1][columnaActual] != ContenidoCelda.OBSTACULO) {
			filaActual--;
			cantidadCeldasLibres++;
			if(mapaConocidoAgente[filaActual][columnaActual] == ContenidoCelda.FLORES) {
				//Salgo del while para quedar en una celda con flores.
				return cantidadCeldasLibres;
			}
		}
		
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasAbajo() {
		//TODO calcular la cantidad de celdas libres abajo según la posición de caperucita y el mapa.
		int cantidadCeldasLibres = 0, ultimaFila = mapaConocidoAgente.length - 1, filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		//TODO hay que sumar celdas libres mientras no llegue a un obstaculo.
		//TODO Si la próxima celda es una flor, sumo uno y corto el while. Para poder llegar a la flor pero no pasarme.
		while(filaActual<ultimaFila && mapaConocidoAgente[filaActual+1][columnaActual] != ContenidoCelda.OBSTACULO) {
			filaActual++;
			cantidadCeldasLibres++;
			
			if(mapaConocidoAgente[filaActual][columnaActual]==ContenidoCelda.FLORES) {
				//Salgo del while porque ya estoy en una celda con flores.
				return cantidadCeldasLibres;
			}
		}
	
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasIzquierda() {
		int cantidadCeldasLibres = 0,filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		while(columnaActual>0 && mapaConocidoAgente[filaActual][columnaActual-1] != ContenidoCelda.OBSTACULO) {
			columnaActual--;
			cantidadCeldasLibres++;
			if(mapaConocidoAgente[filaActual][columnaActual] == ContenidoCelda.FLORES){
				return cantidadCeldasLibres;
			}
		}
		
		return cantidadCeldasLibres;
	}
	public int getCantidadCeldasDerecha() {
		int cantidadCeldasLibres = 0, ultimaColumna = mapaConocidoAgente[0].length - 1, filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		//TODO hay que sumar celdas libres mientras no llegue a un obstaculo.
		//TODO Si la próxima celda es una flor, sumo uno y corto el while. Para poder llegar a la flor pero no pasarme.
		while(columnaActual < ultimaColumna && mapaConocidoAgente[filaActual][columnaActual+1] != ContenidoCelda.OBSTACULO) {
			columnaActual++;
			cantidadCeldasLibres++;
			if(mapaConocidoAgente[filaActual][columnaActual] == ContenidoCelda.FLORES) {
				return cantidadCeldasLibres;
			}
		}
		
		return cantidadCeldasLibres;
	}

	public int getCantidadDulcesArriba() {
		
		int cantidadDulcesArriba = 0, fila = posicionActual.getPosicionFila(), columna = posicionActual.getPosicionColumna();

		while(fila > 0 && !mapaConocidoAgente[fila-1][columna].equals(ContenidoCelda.OBSTACULO)){
			fila--;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.DULCE)) {
				cantidadDulcesArriba++;
			}
		}

		return cantidadDulcesArriba;
	}
	public int getCantidadDulcesAbajo() {

		int cantidadDulcesAbajo = 0, fila = posicionActual.getPosicionFila(), ultimaFila = mapaConocidoAgente.length-1, columna = posicionActual.getPosicionColumna();

		while(fila < ultimaFila && !mapaConocidoAgente[fila+1][columna].equals(ContenidoCelda.OBSTACULO)){
			fila++;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.DULCE)) {
				cantidadDulcesAbajo++;
			}
		}
		return cantidadDulcesAbajo;
	}
	public int getCantidadDulcesIzquierda() {

		int cantidadDulcesIzquierda = 0, fila = posicionActual.getPosicionFila(), columna = posicionActual.getPosicionColumna();

		while(columna > 0 && !mapaConocidoAgente[fila][columna-1].equals(ContenidoCelda.OBSTACULO)){
			columna--;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.DULCE)) {
				cantidadDulcesIzquierda++;
			}
		}
		return cantidadDulcesIzquierda;
	}
	public int getCantidadDulcesDerecha() {

		int cantidadDulcesDerecha = 0, fila = posicionActual.getPosicionFila(), ultimaColumna = mapaConocidoAgente[0].length-1, columna = posicionActual.getPosicionColumna();

		while(columna < ultimaColumna && !mapaConocidoAgente[fila][columna+1].equals(ContenidoCelda.OBSTACULO)){
			columna++;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.DULCE)) {
				cantidadDulcesDerecha++;
			}
		}
		return cantidadDulcesDerecha;
	}

	public boolean getHayLoboArriba() {
		boolean hayLoboArriba = false;
		int fila = posicionActual.getPosicionFila(), columna = posicionActual.getPosicionColumna();

		while(fila > 0 && !mapaConocidoAgente[fila-1][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboArriba){
			fila--;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboArriba=true;
			}
		}
		return hayLoboArriba;
	}
	public boolean getHayLoboAbajo() {
	
		int  fila = posicionActual.getPosicionFila(), ultimaFila = mapaConocidoAgente.length-1, columna = posicionActual.getPosicionColumna();
		boolean hayLoboAbajo = false;
		
		while(fila < ultimaFila && !mapaConocidoAgente[fila+1][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboAbajo){
			fila++;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboAbajo = true;
			}
		}
		return hayLoboAbajo;
	}
	public boolean getHayLoboIzquierda() {
		int fila = posicionActual.getPosicionFila(), columna = posicionActual.getPosicionColumna();
		boolean hayLoboIzquierda = false;
	
		while(columna > 0 && !mapaConocidoAgente[fila][columna-1].equals(ContenidoCelda.OBSTACULO) && !hayLoboIzquierda){
			columna--;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboIzquierda = true;
			}
		}
		return hayLoboIzquierda;
	}
	public boolean getHayLoboDerecha() {
		int fila = posicionActual.getPosicionFila(), ultimaColumna = mapaConocidoAgente[0].length-1, columna = posicionActual.getPosicionColumna();
		boolean hayLoboDerecha = false;
		while(columna < ultimaColumna && !mapaConocidoAgente[fila][columna+1].equals(ContenidoCelda.OBSTACULO) && !hayLoboDerecha){
			columna++;
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboDerecha = true;
			}
		}
		return hayLoboDerecha;
	}
}
