package tp.caperucita.search.caperucita;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionCosto implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		
		CaperucitaEstado estadoActual = (CaperucitaEstado)node.getAgentState();
		CaperucitaEstado estadoAnterior = (CaperucitaEstado)node.getParent().getAgentState();
		int cantidadDulcesRecogidos = estadoActual.getCantidadDulces()-estadoAnterior.getCantidadDulces();
		
		int celdasHorizontales = Math.abs(estadoActual.getPosicionActual().getPosicionFila() - estadoAnterior.getPosicionActual().getPosicionFila());
		int celdasVerticales = Math.abs(estadoActual.getPosicionActual().getPosicionColumna() - estadoAnterior.getPosicionActual().getPosicionColumna());
		
		int costoTotal = 0;
		if(celdasHorizontales == 0 || celdasVerticales==0) {
			
//			costoTotal = Math.max(celdasHorizontales, celdasVerticales) - ((cantidadDulcesRecogidos>0)?(2*cantidadDulcesRecogidos):0);
			
			//Cuando caperucita junta dulces, el camino tiene un costo de cero, para premiarla por elegir este camino.
			if(cantidadDulcesRecogidos>0) {
				costoTotal = 0; 
			} 
			else{
				costoTotal = Math.max(celdasHorizontales, celdasVerticales);
			}
//			if(costoTotal > 0) {
//			
//				return costoTotal;
//			
//			}
			
			return costoTotal;
	
		}
		
		System.out.println("Si todos los movimientos estan bien, no deberia llegar aca.");
		
		return costoTotal;
	}

}
