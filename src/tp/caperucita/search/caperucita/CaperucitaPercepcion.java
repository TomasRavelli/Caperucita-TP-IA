package tp.caperucita.search.caperucita;


import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import tp.caperucita.search.ambiente.AmbienteCaperucita;
import tp.caperucita.search.ambiente.AmbienteEstado;

public class CaperucitaPercepcion extends Perception {
	
	private Integer cantidadCeldasLibresArriba;
	private Integer cantidadCeldasLibresDerecha;
	private Integer cantidadCeldasLibresAbajo;
	private Integer cantidadCeldasLibresIzquierda;
	
	private Integer cantidadDulcesArriba;
	private Integer cantidadDulcesDerecha;
	private Integer cantidadDulcesAbajo;
	private Integer cantidadDulcesIzquierda;
	
	private Boolean hayLoboArriba;
	private Boolean hayLoboDerecha;
	private Boolean hayLoboAbajo;
	private Boolean hayLoboIzquierda;
	
	

	public CaperucitaPercepcion(Agent agent, Environment environment) {
		super(agent, environment);
	}
	@Override
	public void initPerception(Agent agentIn, Environment environmentIn) {
		// TODO Auto-generated method stub
		
		Caperucita agent = (Caperucita) agentIn;
        AmbienteCaperucita ambiente = (AmbienteCaperucita) environmentIn;
        AmbienteEstado environmentState = (AmbienteEstado) ambiente.getEnvironmentState();
		
	}

}
