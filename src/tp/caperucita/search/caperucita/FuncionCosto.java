package tp.caperucita.search.caperucita;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionCosto implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		
		return  node.getAction().getCost();
	}

}
