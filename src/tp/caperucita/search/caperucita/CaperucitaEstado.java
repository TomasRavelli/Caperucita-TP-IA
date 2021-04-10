package tp.caperucita.search.caperucita;

import java.util.ArrayList;
import java.util.List;


import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.auxiliar.ContenidoCelda;

public class CaperucitaEstado extends SearchBasedAgentState {

	private Integer cantidadVidas;
	private Integer cantidadDulces;
	private PosicionCelda posicionActual;
	private ContenidoCelda[][] mapaConocidoAgente;
	
	public CaperucitaEstado() {
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
		cantidadVidas = 3;
		cantidadDulces = 0;
		posicionActual = new PosicionCelda(0,0);
		mapaConocidoAgente = new ContenidoCelda[9][14];
		for (int i = 0 ; i<9; i++) {
			for(int j=0;j<14; i++) {
				mapaConocidoAgente[i][j] = ContenidoCelda.NOCONOCIDO;
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
			for(int j=0;j<14; i++) {
				newMapaConocidoAgente[i][j]= mapaConocidoAgente[i][j];
			}
		}
		newState.setMapaConocidoAgente(newMapaConocidoAgente);
    	  
    	
        return newState;
        
	}


	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		CaperucitaPercepcion percepcion = (CaperucitaPercepcion) p;
		
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
