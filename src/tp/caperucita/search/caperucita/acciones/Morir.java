package tp.caperucita.search.caperucita.acciones;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import tp.caperucita.search.caperucita.CaperucitaEstado;

public class Morir extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CaperucitaEstado estadoCaperucita = (CaperucitaEstado) s;

        if(estadoCaperucita.getCantidadVidas() <= 0) {
            return estadoCaperucita;
        }
        return null;
    }

    @Override
    public Double getCost() {
        return 0.0;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        CaperucitaEstado estadoCaperucita = (CaperucitaEstado) ast;

        if(estadoCaperucita.getCantidadVidas() <= 0) {
            return est;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Morir";
    }
}
