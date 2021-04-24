package tp.caperucita.search.caperucita;

import java.util.ArrayList;

import java.util.Collections;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class Heuristica implements IEstimatedCostFunction {

	@Override
	public double getEstimatedCost(NTree node) {
		CaperucitaEstado estadoCaperucita =(CaperucitaEstado)node.getAgentState();
		int fila = estadoCaperucita.getPosicionActual().getPosicionFila();
		int columna = estadoCaperucita.getPosicionActual().getPosicionColumna();
	
		ContenidoCelda posicionCaperucita = estadoCaperucita.getMapaConocidoAgente()[fila][columna];
		
		if(posicionCaperucita.equals(ContenidoCelda.FLORES)) {
			return 0;
		}else {
			return calcularDistanciaDirectaAlObjetivo(estadoCaperucita);
		}
		
	}

	private double calcularDistanciaDirectaAlObjetivo(CaperucitaEstado estadoCaperucita) {
		// TODO Auto-generated method stub
		ContenidoCelda[][] mapaCaperucita = estadoCaperucita.getMapaConocidoAgente();
		
		ArrayList<PosicionCelda> listaDeCeldasConFlores = new ArrayList();
		
		//Obtener celdas con flores del mapa
		for(int i=0; i<mapaCaperucita.length;i++) {
			for(int j=0; j<mapaCaperucita[0].length; j++) {
				if(mapaCaperucita[i][j].equals(ContenidoCelda.FLORES)) {
					listaDeCeldasConFlores.add(new PosicionCelda(i,j));
				}
			}
		}
		
		ArrayList<Double> distanciasEntreNodos = new ArrayList<>();
		for(PosicionCelda pos: listaDeCeldasConFlores) {
			//Para cada celda con flores, calculo la distancia entre esta, y la posicion de caperucita.
			distanciasEntreNodos.add(calcularDistanciaLineaRecta(estadoCaperucita.getPosicionActual(),pos)); 
		}
		
//		for(Double d: distanciasEntreNodos) {
//			System.out.println(d);
//		}
		Collections.sort(distanciasEntreNodos); //Ordenar el array
//		System.out.println("Calculo de las distancias");
		
//		System.out.println("Distancia minima = " + distanciasEntreNodos.get(0));
		return distanciasEntreNodos.get(0); //Retorno la distancia minima.
	}

	private double calcularDistanciaLineaRecta(PosicionCelda posicionActual, PosicionCelda pos) {
		// Calculo de la distancia entre dos puntos. Cada celda se considera un punto
		int x1 = pos.getPosicionFila(), x0 = posicionActual.getPosicionColumna(), y1 = pos.getPosicionColumna(), y0 = posicionActual.getPosicionColumna();
		return Math.sqrt(Math.pow((x1-x0), 2)+Math.pow((y1-y0), 2));
	}

}
