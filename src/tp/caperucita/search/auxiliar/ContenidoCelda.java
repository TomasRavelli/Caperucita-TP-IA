package tp.caperucita.search.auxiliar;

public enum ContenidoCelda {
	NOCONOCIDO,
	LIBRE, 
	LOBO, 
	OBSTACULO, 
	DULCE, 
	FLORES,
	CONOCIDO;

	@Override
	public String toString() {
		String name = name();
		for(int i = name.length(); i < 10; i++){
			name += " ";
		}
		return name;
	}
}
