package tp.caperucita.search.caperucita;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import tp.caperucita.search.ambiente.AmbienteCaperucita;
import tp.caperucita.search.auxiliar.CaperucitaSimulator;
import tp.caperucita.search.auxiliar.ConfiguracionInicial;

public class CaperucitaMain {
	public static void main(String[] args) throws PrologConnectorException {
       
		Caperucita agent = new Caperucita();
        
        
        AmbienteCaperucita environment = new AmbienteCaperucita();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        
        //Acá esta el clico percepcion-accion
        simulator.start();
        
        if(((CaperucitaEstado)agent.getAgentState()).getCantidadVidas() <= 0) {
        	System.out.println("El lobo se ha comido a Caperucita tres veces.");
        }
        //CaperucitaPercepcion percepcion = new CaperucitaPercepcion(agent, environment);

    }
}
