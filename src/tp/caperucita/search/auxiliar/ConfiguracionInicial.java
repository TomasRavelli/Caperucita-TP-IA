package tp.caperucita.search.auxiliar;

import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.Strategy;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import tp.caperucita.search.caperucita.FuncionCosto;
import tp.caperucita.search.caperucita.Heuristica;

public class ConfiguracionInicial {
	
	public static Integer cantidadDeVidas = 3, cantidadDulces = 0 ;

	private static IStepCostFunction funcionCosto = new FuncionCosto();
	private static IEstimatedCostFunction heuristica = new Heuristica();

	public static Strategy getEstrategia(){
		Strategy estrategia;

		//Estrategia Amplitud
//		estrategia = new BreathFirstSearch();

		//Estrategia por costo uniforme
//		estrategia = new UniformCostSearch(funcionCosto);

		//Estrategia A*
		estrategia = new AStarSearch(funcionCosto, heuristica);

		return estrategia;
	}
		
	//Escenario 1
//	public static PosicionCelda posicionInicialCaperucita = new PosicionCelda(5,11);
//	public static ContenidoCelda[][] mapaAmbiente = {
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.DULCE,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.DULCE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,   ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,   ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO}
//	};
	
	//Escenario 2
//	public static PosicionCelda posicionInicialCaperucita = new PosicionCelda(6,3);
//	public static ContenidoCelda[][] mapaAmbiente =  {
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
//			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO}
//	};

	//Escenario 3
//		public static PosicionCelda posicionInicialCaperucita = new PosicionCelda(4,11);
//		public static ContenidoCelda[][] mapaAmbiente = {
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO},
//				{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO}
//		};

	//Escenario 4: Elige distitos caminos segun la estrategia.
	public static PosicionCelda posicionInicialCaperucita = new PosicionCelda(6,3);
	public static ContenidoCelda[][] mapaAmbiente =  {
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.DULCE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.DULCE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.LIBRE,     ContenidoCelda.OBSTACULO,    ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
			{ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.FLORES,    ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO}
	};

	public ConfiguracionInicial() {
		
	}
}
