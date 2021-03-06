package tp.caperucita.search.caperucita;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class CaperucitaEstado extends SearchBasedAgentState {

	private Integer cantidadVidas;
	private Integer cantidadDulces;
	private PosicionCelda posicionActual;
	private ContenidoCelda[][] mapaConocidoAgente;
	

	public void sumarVisitaACelda(PosicionCelda posicion) {
			
		int fila = posicion.getPosicionFila();
		int columna = posicion.getPosicionColumna();
	}
	public CaperucitaEstado() {
		this.mapaConocidoAgente = new ContenidoCelda[ConfiguracionInicial.mapaAmbiente.length][ConfiguracionInicial.mapaAmbiente[0].length];
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
		this.cantidadVidas = ConfiguracionInicial.cantidadDeVidas;
		this.cantidadDulces = ConfiguracionInicial.cantidadDulces;
		this.posicionActual = ConfiguracionInicial.posicionInicialCaperucita;
		
		//Al principio, Caperucita conoce todo el mapa.
		this.mapaConocidoAgente = ConfiguracionInicial.mapaAmbiente;
	
	}
	
	@Override
	public boolean equals(Object o) {
		//TODO creo que el equals no deber??a tener en cuenta la cantidad de dulces y vidas.
		// porque lo usa el framework para saber si el agente ya pas?? por este estado, si ya pas?? pero junto dulces en el medio,
		// va a creer que puede volver a este estado y se va a provocar un bucle infinito
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		CaperucitaEstado that = (CaperucitaEstado) o;

		return  (this.posicionActual.equals(that.posicionActual) && that.getCantidadVidas().equals(this.cantidadVidas))  ;
	}

	@Override
	public SearchBasedAgentState clone() {
		
		CaperucitaEstado newState = new CaperucitaEstado();
    	
    	
		newState.setCantidadVidas(this.cantidadVidas);
		newState.setCantidadDulces(this.cantidadDulces);
		
		
		//Los atributos que son objetos se pasan por
    	//referencia; luego, es necesario clonarlos
		newState.setPosicionActual(this.posicionActual.clone());

		ContenidoCelda[][] newMapaConocidoAgente = new ContenidoCelda[mapaConocidoAgente.length][mapaConocidoAgente[0].length];

		for (int i = 0; i < mapaConocidoAgente.length; i++) {
			for (int j = 0; j < mapaConocidoAgente[i].length; j++) {
				newMapaConocidoAgente[i][j] = mapaConocidoAgente[i][j];
			}
		}
		newState.setMapaConocidoAgente(newMapaConocidoAgente);
		
        return newState;
        
	}


	@Override
	public void updateState(Perception p) {
		
		//Esto e ejecuta en el see(Percepcion) de GoalBasedAgentSimulator, cada vez que percibe el agente.
		
		CaperucitaPercepcion percepcion = (CaperucitaPercepcion) p;
		
		//El mapa de caperucita se actualiza con los caminos que percibe desde el ambiente.
		actualizarMapaCaminoArriba(percepcion.getCeldasArriba());
		actualizarMapaCaminoDerecha(percepcion.getCeldasDerecha());
		actualizarMapaCaminoAbajo(percepcion.getCeldasAbajo());
		actualizarMapaCaminoIzquierda(percepcion.getCeldasIzquierda());
		
	}
	
	private void actualizarMapaCaminoArriba(List<ContenidoCelda> celdasArriba) {
		int primerFila = this.posicionActual.getPosicionFila(), columna=this.posicionActual.getPosicionColumna(), index = 0;
		//Ac?? hay que actualizar desde primerFila, no desde primerFila-1,
		// porque la lista que nos da la percepci??n incluye la celda donde est?? caperucita (primerFila,columna)
		for(int i = primerFila; i>=0;i--) {
			this.mapaConocidoAgente[i][columna] = celdasArriba.get(index);
			index++;
		}
		
	}

	private void actualizarMapaCaminoDerecha(List<ContenidoCelda> celdasDerecha) {
		
		int index = 0, columnaActual = this.posicionActual.getPosicionColumna(), ultimaColumna = this.mapaConocidoAgente[0].length-1, filaActual=this.posicionActual.getPosicionFila();
	
		for(int i = columnaActual; i<=ultimaColumna;i++) {
			this.mapaConocidoAgente[filaActual][i] = celdasDerecha.get(index);
			index++;
		}
		
	}


	private void actualizarMapaCaminoIzquierda(List<ContenidoCelda> celdasIzquierda) {
		int columnaActual = this.posicionActual.getPosicionColumna(),  filaActual=this.posicionActual.getPosicionFila(), index=0;

		for(int i = columnaActual; i>=0;i--) {
			this.mapaConocidoAgente[filaActual][i] = celdasIzquierda.get(index);
			index++;
		}
		
	}


	private void actualizarMapaCaminoAbajo(List<ContenidoCelda> celdasAbajo) {
		int primerFila = this.posicionActual.getPosicionFila(), ultimaFila = mapaConocidoAgente.length-1, columnaActual=this.posicionActual.getPosicionColumna(), index = 0;
		
		for(int i = primerFila; i<=ultimaFila;i++) {
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
				"\ncantidadVidas=" + cantidadVidas +
				"\n, cantidadDulces=" + cantidadDulces +
				"\n, posicionActual=" + posicionActual +
				"\n, mapaConocidoAgente=" + mapaString +
				"} fin CaperucitaEstado";
	}


	public int getCantidadCeldasArriba() {
		//calcular la cantidad de celdas libres arriba seg??n la posici??n de caperucita y el mapa.
		int cantidadCeldasLibres = 0,filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

		//hay que sumar celdas libres mientras no llegue a un obstaculo.
		//Si la pr??xima celda es una flor, sumo uno y corto el while. Para poder llegar a la flor pero no pasarme.
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
		int cantidadCeldasLibres = 0, ultimaFila = mapaConocidoAgente.length - 1, filaActual=this.posicionActual.getPosicionFila(), columnaActual = this.posicionActual.getPosicionColumna();

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
		//Empiezo a ver desde la celda actual porque el lobo puede estar en la misma celda que caperucita, y as?? va a ver al lobo en todos los caminos.
		if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) return true;
		while(fila > 0 && !mapaConocidoAgente[fila][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboArriba){
			
			fila--;
			//TODO esto evita que caperucita piense que no puede llegar a las flores, cuando el lobo va a estar despues de las Flores, si es que esta.
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.FLORES)) {
				break;
			}
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboArriba=true;
			}
		}
		return hayLoboArriba;
	}
	public boolean getHayLoboAbajo() {
	
		int  fila = posicionActual.getPosicionFila(), ultimaFila = mapaConocidoAgente.length-1, columna = posicionActual.getPosicionColumna();
		boolean hayLoboAbajo = false;
		if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) return true;
		while(fila < ultimaFila && !mapaConocidoAgente[fila][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboAbajo){
			fila++;
			//TODO esto evita que caperucita piense que no puede llegar a las flores, cuando el lobo va a estar despues de las Flores, si es que esta.
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.FLORES)) {
				break;
			}
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboAbajo = true;
			}
		}
		return hayLoboAbajo;
	}
	public boolean getHayLoboIzquierda() {
		int fila = posicionActual.getPosicionFila(), columna = posicionActual.getPosicionColumna();
		boolean hayLoboIzquierda = false;
		if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) return true;
		while(columna > 0 && !mapaConocidoAgente[fila][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboIzquierda){
			columna--;
			//TODO esto evita que caperucita piense que no puede llegar a las flores, cuando el lobo va a estar despues de las Flores, si es que esta.
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.FLORES)) {
				break;
			}
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboIzquierda = true;
			}
		}
		return hayLoboIzquierda;
	}
	public boolean getHayLoboDerecha() {
		int fila = posicionActual.getPosicionFila(), ultimaColumna = mapaConocidoAgente[0].length-1, columna = posicionActual.getPosicionColumna();
		boolean hayLoboDerecha = false;
		if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) return true;
		while(columna < ultimaColumna && !mapaConocidoAgente[fila][columna].equals(ContenidoCelda.OBSTACULO) && !hayLoboDerecha){
			columna++;
			//TODO PRUEBAS. esto evita que caperucita piense que no puede llegar a las flores, cuando el lobo va a estar despues de las Flores, si es que esta.
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.FLORES)) {
				break;
			}
			if(mapaConocidoAgente[fila][columna].equals(ContenidoCelda.LOBO)) {
				hayLoboDerecha = true;
			}
		}
		return hayLoboDerecha;
	}


	public void eliminarDulcesEnCamino(PosicionCelda inicioCamino, PosicionCelda finCamino) {
		int filaInicio = inicioCamino.getPosicionFila();
		int columnaInicio = inicioCamino.getPosicionColumna();
		int filaFin = finCamino.getPosicionFila();
		int columnaFin = finCamino.getPosicionColumna();

		if(filaInicio == filaFin && columnaInicio == columnaFin){
			//no es un camino v??lido porque la celda de inicio y fin son iguales.
			return;
		}

		if(filaInicio == filaFin){
			//Es un camino horizontal (izquierda <-> derecha)

			if(columnaInicio < columnaFin){
				//Es izquierda -> derecha
				eliminarDulcesHorizontal(columnaInicio, columnaFin, filaInicio);
				return;
			}
			//columnaInicio > columnaFin, no pueden ser iguales.
			//Es derecha -> izquierda
			eliminarDulcesHorizontal(columnaFin, columnaInicio, filaInicio);
			return;
		}

		if(columnaInicio == columnaFin){
			//Es un camino vertical (Arriba <-> Abajo)
			if(filaInicio < filaFin){
				//Es Arriba -> Abajo (ir arriba es restar filas, entonces arriba es menor que abajo)
				eliminarDulcesVertical(filaInicio, filaFin, columnaInicio);
				return;
			}
			//columnaInicio > columnaFin, no pueden ser iguales.
			//Es derecha -> izquierda
			eliminarDulcesVertical(filaFin, filaInicio, columnaInicio);
			return;
		}
		//Si llega ac?? no es un camino v??lido porque no es ni vertical ni horizontal, puede ser un diagonal pero caperucita no se puede mover en diagonal
	}

	private void eliminarDulcesVertical(int inicio, int fin, int columna){
		if(inicio > fin){
			//No deber??a pasar, pero por las dudas. Es inv??lido.
			return;
		}
	
		for(int i = inicio; i <= fin; i++){
			if(mapaConocidoAgente[i][columna] == ContenidoCelda.DULCE){
				
				mapaConocidoAgente[i][columna] = ContenidoCelda.LIBRE;
			}
		}
		

	}

	private void eliminarDulcesHorizontal(int inicio, int fin, int fila){
		if(inicio > fin){
			//No deber??a pasar, pero por las dudas. Es inv??lido.
			return;
		}
		
		for(int j = inicio; j <= fin; j++){
			if(mapaConocidoAgente[fila][j] == ContenidoCelda.DULCE){
				
				//Si en esta posici??n del mapa hab??a un dulce lo quito
				mapaConocidoAgente[fila][j] = ContenidoCelda.LIBRE;
			}
		}
	}

}
