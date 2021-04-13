package tp.caperucita.search.auxiliar;

public enum ContenidoCelda {
	DESCONOCIDO,
	LIBRE, 
	LOBO, 
	OBSTACULO, 
	DULCE, 
	FLORES;

	@Override
	public String toString() {
		String name = name();
		//agrego espacios al final para que quede mejor cuando se muestra el mapa.
		for(int i = name.length(); i < 11; i++){
			name += " ";
		}
		return name;
	}
}
