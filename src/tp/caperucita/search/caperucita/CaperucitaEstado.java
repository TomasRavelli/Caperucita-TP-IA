package tp.caperucita.search.caperucita;

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
				this.mapaConocidoAgente[i][j] = ContenidoCelda.NOCONOCIDO;
			}
		}
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public SearchBasedAgentState clone() {
		
		CaperucitaEstado newState = new CaperucitaEstado();
    	
    	
		newState.setCantidadVidas(this.cantidadVidas);
		newState.setCantidadDulces(this.cantidadDulces);
		
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
		
		//TODO estos e ejecuta en el see(Percepcion) de GoalBasedAgentSimulator, cada vez que percibe el agente.
		
		percepcion=(CaperucitaPercepcion)p;
		
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
