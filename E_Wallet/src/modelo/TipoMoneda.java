package modelo;

public enum TipoMoneda {
	CLP, USD, R;
	
	public static boolean esValido(String valor) {
		for (TipoMoneda m : TipoMoneda.values()) {
			if(m.name().equalsIgnoreCase(valor)) {
				return true;
			}
		}
		return false;
	}
}
