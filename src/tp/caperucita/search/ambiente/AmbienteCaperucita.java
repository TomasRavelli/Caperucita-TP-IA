package tp.caperucita.search.ambiente;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

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
		// TODO Auto-generated method stub
		return null;
	}

}
