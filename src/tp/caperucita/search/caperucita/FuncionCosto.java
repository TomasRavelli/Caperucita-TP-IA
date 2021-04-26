package tp.caperucita.search.caperucita;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionCosto implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		
		CaperucitaEstado estadoActual = (CaperucitaEstado)node.getAgentState();
		CaperucitaEstado estadoAnterior = (CaperucitaEstado)node.getParent().getAgentState();
		
		//Verificamos el nodo actual y el nodo padre para ver si caperucita junto dulces en esa decision.
		int cantidadDulcesRecogidos = estadoActual.getCantidadDulces()-estadoAnterior.getCantidadDulces();
		
		int celdasHorizontales = Math.abs(estadoActual.getPosicionActual().getPosicionFila() - estadoAnterior.getPosicionActual().getPosicionFila());
		int celdasVerticales = Math.abs(estadoActual.getPosicionActual().getPosicionColumna() - estadoAnterior.getPosicionActual().getPosicionColumna());
		
		int costoTotal = 0;
	
		//Si solo se le puede mover en linea recta, alguna de las celdas tiene que dar ceros porque se mantiene en la msima fila o la misma columna.
		if(celdasHorizontales == 0 || celdasVerticales==0) {
			
			//Cuando caperucita junta dulces, el camino tiene un costo de cero, para premiarla por elegir este camino, ya que el mapa
			// es pequeño y tambien hacemos que el costo nunca sea negativo.
			if(cantidadDulcesRecogidos>0) {
				costoTotal = 0; 
			} 
			else{
				costoTotal = Math.max(celdasHorizontales, celdasVerticales);
			}
			
			return costoTotal;
	
		}
		
		
		//Si todos los movimientos estan bien, no deberia llegar aca.
		return costoTotal;
	}

}
