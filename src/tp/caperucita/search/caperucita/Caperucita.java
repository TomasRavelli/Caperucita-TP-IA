package tp.caperucita.search.caperucita;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import tp.caperucita.search.caperucita.acciones.*;

public class Caperucita extends SearchBasedAgent{
	
	public Caperucita() {
		  // The Agent Goal
        CaperucitaObjetivo agGoal = new CaperucitaObjetivo();

        // The Agent State
        CaperucitaEstado agState = new CaperucitaEstado();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();	
        operators.addElement(new IrArriba());
        operators.addElement(new IrDerecha());	
        operators.addElement(new IrAbajo());
        operators.addElement(new IrIzquierda());
        operators.addElement(new IrArribaYJuntarDulce());
        operators.addElement(new IrDerechaYJuntarDulce());	
        operators.addElement(new IrAbajoYJuntarDulce());
        operators.addElement(new IrIzquierdaYJuntarDulce());
        

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
	}

	@Override
	public void see(Perception p) {
		//TODO ver si en este caso es necesario porque el agente en nuestro problema solo se actualiza cuando realiza una accion.
		this.getAgentState().updateState(p);
		
	}

	@Override
	public Action selectAction() {
		// elegir la estrategia de busqueda, en este caso, por profundidad
    	DepthFirstSearch strategy = new DepthFirstSearch();          

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Caperucita.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
	}

}
