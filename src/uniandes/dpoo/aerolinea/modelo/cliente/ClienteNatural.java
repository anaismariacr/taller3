package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente {

	public String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String getIdentificador() {
		return NATURAL;
	}
	
	@Override
	public String getTipoCliente() {
		return nombre;
	}

	public void getTiquete(Tiquete tiquete) {
		tiquetes.add(tiquete);

	}

	@Override
	public int calcularValorTotalTiquetes() {
		int valor = 0;
		for (Tiquete tiquete : tiquetes) {
			if(!tiquete.esUsado()) {
				valor += tiquete.getTarifa();
			}
		}
		
		return valor;
	}

	@Override
	public void usarTiquetes(Vuelo vuelo) {
		for (Tiquete tiquete : tiquetes) {
			if (tiquete.getVuelo().equals(vuelo) && !tiquete.esUsado()) {
				tiquete.marcarComoUsado();
			}
		}
		
	}
	
}
