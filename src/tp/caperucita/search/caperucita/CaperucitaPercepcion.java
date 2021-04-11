package tp.caperucita.search.caperucita;


import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import tp.caperucita.search.ambiente.AmbienteCaperucita;
import tp.caperucita.search.ambiente.AmbienteEstado;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class CaperucitaPercepcion extends Perception {
	
	private Integer cantidadCeldasLibresArriba;
	private Integer cantidadCeldasLibresDerecha;
	private Integer cantidadCeldasLibresAbajo;
	private Integer cantidadCeldasLibresIzquierda;
	
	private Integer cantidadDulcesArriba;
	private Integer cantidadDulcesDerecha;
	private Integer cantidadDulcesAbajo;
	private Integer cantidadDulcesIzquierda;
	
	private Boolean hayLoboArriba;
	private Boolean hayLoboDerecha;
	private Boolean hayLoboAbajo;
	private Boolean hayLoboIzquierda;

	public CaperucitaPercepcion() {
	}

	public CaperucitaPercepcion(Agent agent, Environment environment) {
		super(agent, environment);
	}
	
	@Override
	public void initPerception(Agent agentIn, Environment environmentIn) {
		
		cantidadCeldasLibresArriba=0;
		cantidadCeldasLibresDerecha=0;
		cantidadCeldasLibresAbajo = 0;
		cantidadCeldasLibresIzquierda=0;
		
		cantidadDulcesArriba=0;
		cantidadDulcesDerecha=0;
		cantidadDulcesAbajo=0;
		cantidadDulcesIzquierda=0;
		
		hayLoboArriba = false;
		hayLoboDerecha = false;
		hayLoboAbajo = false;
		hayLoboIzquierda = false;
		
		Caperucita agent = (Caperucita) agentIn;
        AmbienteCaperucita ambiente = (AmbienteCaperucita) environmentIn;
        AmbienteEstado environmentState = ambiente.getEnvironmentState();
        ContenidoCelda[][] mapaAmbiente = environmentState.getMapaAmbiente();
        PosicionCelda posicionCaperucita = environmentState.getPosicionCaperucita();

        //movi esto a otro método para usarlo en el ambiente.getPercept()
        contarCeldasLibresYDulces(mapaAmbiente, posicionCaperucita);
      
	}

	public void contarCeldasLibresYDulces(ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita){
		//inicializar parametros
		cantidadCeldasLibresArriba=0;
		cantidadCeldasLibresDerecha=0;
		cantidadCeldasLibresAbajo = 0;
		cantidadCeldasLibresIzquierda=0;
		//0=arriba, 1=derecha, 2=abajo, 3=izquierda
		contarCeldasLibresYDulces(0, mapaAmbiente, posicionCaperucita);
		contarCeldasLibresYDulces(1, mapaAmbiente, posicionCaperucita);
		contarCeldasLibresYDulces(2, mapaAmbiente, posicionCaperucita);
		contarCeldasLibresYDulces(3, mapaAmbiente, posicionCaperucita);
	}

	//TODO este método es bastante feo, creo que se podría dividir en 4, o hacer que no reciba una dirección si igual hay que calcular las 4 siempre
	//TODO REVISAR, me parece que si caperucita está en algún borde esto no funciona bien.
	private void contarCeldasLibresYDulces(int orientacion, ContenidoCelda[][] mapaAmbiente, PosicionCelda posicionCaperucita ) {
		int filaActual = posicionCaperucita.getPosicionFila();
		int columnaActual = posicionCaperucita.getPosicionColumna();
		int ultimaFila = mapaAmbiente.length, ultimaColumna = mapaAmbiente[0].length;
		
		switch(orientacion){
			case 0:{//Arriba

				while(filaActual > 0 && !mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.OBSTACULO) && !hayLoboArriba) {
					if(mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.LOBO)) {
						hayLoboArriba = true;
					}
					if(mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.DULCE)) {
						cantidadDulcesArriba++;
					}
					cantidadCeldasLibresArriba++;
					filaActual--;
				}
				break;
			}
			case 1:{//Derecha

				//TODO esto genera un out of bounds, porque cuando columnaActual es ultimaColumna-1 (el último valor válido), intenta accesder a la posicion ultimaColumna (columnaActual+1)
				while(columnaActual < ultimaColumna && !mapaAmbiente[filaActual][columnaActual+1].equals(ContenidoCelda.OBSTACULO) && !hayLoboDerecha) {
					if(mapaAmbiente[filaActual][columnaActual+1].equals(ContenidoCelda.LOBO)) {
						hayLoboDerecha = true;
					}
					if(mapaAmbiente[filaActual][columnaActual+1].equals(ContenidoCelda.DULCE)) {
						cantidadDulcesDerecha++;
					}
					cantidadCeldasLibresDerecha++;
					columnaActual++;
				}
				break;
			}
			case 2:{//Abajo
				
				while(filaActual < ultimaFila && !mapaAmbiente[filaActual+1][columnaActual].equals(ContenidoCelda.OBSTACULO) && !hayLoboAbajo) {
					if(mapaAmbiente[filaActual+1][columnaActual].equals(ContenidoCelda.LOBO)) {
						hayLoboAbajo = true;
					}
					if(mapaAmbiente[filaActual+1][columnaActual].equals(ContenidoCelda.DULCE)) {
						cantidadDulcesAbajo++;
					}
					cantidadCeldasLibresAbajo++;
					filaActual++;
				}
				break;
			}
			case 3:{
				//Izquierda
				while(columnaActual > 0 && !mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.OBSTACULO) && !hayLoboIzquierda) {
					if(mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.LOBO)) {
						hayLoboIzquierda = true;
					}
					if(mapaAmbiente[filaActual-1][columnaActual].equals(ContenidoCelda.DULCE)) {
						cantidadDulcesIzquierda++;
					}
					cantidadCeldasLibresIzquierda++;
					columnaActual--;
				}
				break;
			}
		}
	}
	public Integer getCantidadCeldasLibresArriba() {
		return cantidadCeldasLibresArriba;
	}
	public void setCantidadCeldasLibresArriba(Integer cantidadCeldasLibresArriba) {
		this.cantidadCeldasLibresArriba = cantidadCeldasLibresArriba;
	}
	public Integer getCantidadCeldasLibresDerecha() {
		return cantidadCeldasLibresDerecha;
	}
	public void setCantidadCeldasLibresDerecha(Integer cantidadCeldasLibresDerecha) {
		this.cantidadCeldasLibresDerecha = cantidadCeldasLibresDerecha;
	}
	public Integer getCantidadCeldasLibresAbajo() {
		return cantidadCeldasLibresAbajo;
	}
	public void setCantidadCeldasLibresAbajo(Integer cantidadCeldasLibresAbajo) {
		this.cantidadCeldasLibresAbajo = cantidadCeldasLibresAbajo;
	}
	public Integer getCantidadCeldasLibresIzquierda() {
		return cantidadCeldasLibresIzquierda;
	}
	public void setCantidadCeldasLibresIzquierda(Integer cantidadCeldasLibresIzquierda) {
		this.cantidadCeldasLibresIzquierda = cantidadCeldasLibresIzquierda;
	}
	public Integer getCantidadDulcesArriba() {
		return cantidadDulcesArriba;
	}
	public void setCantidadDulcesArriba(Integer cantidadDulcesArriba) {
		this.cantidadDulcesArriba = cantidadDulcesArriba;
	}
	public Integer getCantidadDulcesDerecha() {
		return cantidadDulcesDerecha;
	}
	public void setCantidadDulcesDerecha(Integer cantidadDulcesDerecha) {
		this.cantidadDulcesDerecha = cantidadDulcesDerecha;
	}
	public Integer getCantidadDulcesAbajo() {
		return cantidadDulcesAbajo;
	}
	public void setCantidadDulcesAbajo(Integer cantidadDulcesAbajo) {
		this.cantidadDulcesAbajo = cantidadDulcesAbajo;
	}
	public Integer getCantidadDulcesIzquierda() {
		return cantidadDulcesIzquierda;
	}
	public void setCantidadDulcesIzquierda(Integer cantidadDulcesIzquierda) {
		this.cantidadDulcesIzquierda = cantidadDulcesIzquierda;
	}
	public Boolean getHayLoboArriba() {
		return hayLoboArriba;
	}
	public void setHayLoboArriba(Boolean hayLoboArriba) {
		this.hayLoboArriba = hayLoboArriba;
	}
	public Boolean getHayLoboDerecha() {
		return hayLoboDerecha;
	}
	public void setHayLoboDerecha(Boolean hayLoboDerecha) {
		this.hayLoboDerecha = hayLoboDerecha;
	}
	public Boolean getHayLoboAbajo() {
		return hayLoboAbajo;
	}
	public void setHayLoboAbajo(Boolean hayLoboAbajo) {
		this.hayLoboAbajo = hayLoboAbajo;
	}
	public Boolean getHayLoboIzquierda() {
		return hayLoboIzquierda;
	}
	public void setHayLoboIzquierda(Boolean hayLoboIzquierda) {
		this.hayLoboIzquierda = hayLoboIzquierda;
	}

	@Override
	public String toString() {
		return "CaperucitaPercepcion{" +
				"cantidadCeldasLibresArriba=" + cantidadCeldasLibresArriba +
				", cantidadCeldasLibresDerecha=" + cantidadCeldasLibresDerecha +
				", cantidadCeldasLibresAbajo=" + cantidadCeldasLibresAbajo +
				", cantidadCeldasLibresIzquierda=" + cantidadCeldasLibresIzquierda +
				", cantidadDulcesArriba=" + cantidadDulcesArriba +
				", cantidadDulcesDerecha=" + cantidadDulcesDerecha +
				", cantidadDulcesAbajo=" + cantidadDulcesAbajo +
				", cantidadDulcesIzquierda=" + cantidadDulcesIzquierda +
				", hayLoboArriba=" + hayLoboArriba +
				", hayLoboDerecha=" + hayLoboDerecha +
				", hayLoboAbajo=" + hayLoboAbajo +
				", hayLoboIzquierda=" + hayLoboIzquierda +
				'}';
	}
}
