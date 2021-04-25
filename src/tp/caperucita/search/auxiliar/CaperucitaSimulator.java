package tp.caperucita.search.auxiliar;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.state.AgentState;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class CaperucitaSimulator extends SearchBasedAgentSimulator {

	public CaperucitaSimulator(Environment environment, Agent agent) {
		super(environment, agent);
	}
	
	
	@Override
	public boolean agentSucceeded(Action actionReturned) {
		
		SearchBasedAgent sa = (SearchBasedAgent) getAgents().firstElement();
	    Problem p = sa.getProblem();
	    GoalTest gt = p.getGoalState();
	    CaperucitaEstado aSt = (CaperucitaEstado) p.getAgentState();
	
	    return gt.isGoalState(aSt) && aSt.getCantidadVidas()>0;
//		return super.agentSucceeded(actionReturned);
	}
}
