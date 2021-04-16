package tp.caperucita.search.ambiente;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;
import tp.caperucita.search.caperucita.Caperucita;
import tp.caperucita.search.caperucita.CaperucitaPercepcion;

public class AmbienteCaperucita extends Environment {
	
	 public AmbienteCaperucita() {
	        // Create the environment state
	        this.environmentState = new AmbienteEstado();
	    }

    public AmbienteEstado getEnvironmentState() {
        return (AmbienteEstado) super.getEnvironmentState();
    }

	@Override
	public Perception getPercept() {

	 	CaperucitaPercepcion percepcion = new CaperucitaPercepcion();

		PosicionCelda posicionCaperucita = ((AmbienteEstado)environmentState).getPosicionCaperucita();
		ContenidoCelda[][] mapa = ((AmbienteEstado)environmentState).getMapaAmbiente();

		//TODO esto hay que cambiarlo por los valores reales (fijándose en el mapa del ambiente)
//		percepcion.setCeldasArriba(new ArrayList<>());
//		percepcion.setCeldasDerecha(new ArrayList<>());
//		percepcion.setCeldasAbajo(new ArrayList<>());
//		percepcion.setCeldasIzquierda(new ArrayList<>());
		
		percepcion.contarCeldasLibresYDulces(mapa, posicionCaperucita);

	 	return percepcion;
	}

	@Override
	public String toString() {
		return "AmbienteCaperucita{\n" +
				"environmentState=" + environmentState +
				"} fin AmbienteCaperucita\n";
	}
	
	@Override
	  public boolean agentFailed(Action actionReturned) {
       
		//TODO Entrega-19-14. Implementar la condicion de falla.
		//Segun AmbienteAspiradora, se puede cambiar el argumento para que reciba un AgentState.
		//Entonces aca debemos verificar que la cantidad de vidas sea mayor a 0 del agente.
		//Y agregar alguna otra verificacion si es necesario.
		return false;
    }
}
