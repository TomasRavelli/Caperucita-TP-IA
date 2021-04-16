package tp.caperucita.search.ambiente;

import java.util.Arrays;

import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class AmbienteEstado extends EnvironmentState {
	
	
	//TODO Entrega-19-14. Estado del ambiente.
	private PosicionCelda posicionCaperucita;
	
	//Solo te deja inicializar con constantes aca.
	private ContenidoCelda[][] mapaAmbiente= {
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE},
			{ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE,ContenidoCelda.LIBRE, ContenidoCelda.LIBRE,ContenidoCelda.LIBRE}
	};;
	
	public AmbienteEstado() {
		this.posicionCaperucita = new PosicionCelda();
		this.initState();
	}

	@Override
	public void initState() {
		this.posicionCaperucita.setPosicionFila(0);
		this.posicionCaperucita.setPosicionColumna(0);
		
		//TODO Entrega-19-14. Setear posisicon aleatoria del lobo.
	}

	@Override
	public String toString() {

		String mapaString = "\n";

		for(int fila = 0; fila < mapaAmbiente.length; fila++){
			for(int col = 0; col < mapaAmbiente[fila].length; col++){
				mapaString += "|";
				if(posicionCaperucita.getPosicionFila() == fila && posicionCaperucita.getPosicionColumna() == col){
					mapaString += "CAPERUCITA "; //Espacio al final para que tenga 11 caracteres, como DESCONOCIDO.
				}else {
					mapaString += mapaAmbiente[fila][col].toString() + "";
				}
				mapaString += "| ";
			}
			mapaString += "\n";
		}

		return "AmbienteEstado{\n" +
				"posicionCaperucita=" + posicionCaperucita + "\n" +
				", mapaAmbiente=" + mapaString +
				"} fin AmbienteEstado\n";
	}

	public PosicionCelda getPosicionCaperucita() {
		return posicionCaperucita;
	}

	public void setPosicionCaperucita(PosicionCelda posicionCaperucita) {
		this.posicionCaperucita = posicionCaperucita;
	}

	public ContenidoCelda[][] getMapaAmbiente() {
		return mapaAmbiente;
	}

	public void setMapaAmbiente(ContenidoCelda[][] mapaAmbiente) {
		this.mapaAmbiente = mapaAmbiente;
	}
	
	public void actualizarMapaAmbiente(PosicionCelda celda, ContenidoCelda nuevoEstadoCelda) {
		this.mapaAmbiente[celda.getPosicionFila()][celda.getPosicionColumna()] = nuevoEstadoCelda;
	}


	public void eliminarDulcesEnCamino(PosicionCelda inicioCamino, PosicionCelda finCamino) {
		int filaInicio = inicioCamino.getPosicionFila();
		int columnaInicio = inicioCamino.getPosicionColumna();
		int filaFin = finCamino.getPosicionFila();
		int columnaFin = finCamino.getPosicionColumna();

		if(filaInicio == filaFin && columnaInicio == columnaFin){
			//no es un camino válido porque la celda de inicio y fin son iguales.
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
			eliminarDulcesHorizontal(filaFin, filaInicio, columnaInicio);
			return;
		}
		//Si llega acá no es un camino válido porque no es ni vertical ni horizontal, puede ser un diagonal pero caperucita no se puede mover en diagonal
	}

	private void eliminarDulcesVertical(int inicio, int fin, int columna){
		if(inicio > fin){
			//No debería pasar, pero por las dudas. Es inválido.
			return;
		}

		for(int i = inicio; i <= fin; i++){
			if(mapaAmbiente[i][columna] == ContenidoCelda.DULCE){
				mapaAmbiente[i][columna] = ContenidoCelda.LIBRE;
			}
		}

	}

	private void eliminarDulcesHorizontal(int inicio, int fin, int fila){
		if(inicio > fin){
			//No debería pasar, pero por las dudas. Es inválido.
			return;
		}

		for(int j = inicio; j <= fin; j++){
			if(mapaAmbiente[fila][j] == ContenidoCelda.DULCE){
				//Si en esta posición del mapa había un dulce lo quito
				mapaAmbiente[fila][j] = ContenidoCelda.LIBRE;
			}
		}
	}
}
