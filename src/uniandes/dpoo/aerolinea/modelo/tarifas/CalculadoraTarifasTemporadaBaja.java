package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTO_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES = 0.2;
	
	@Override
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		int costoConDescuento = (int) (costoBase * (1 - descuento));
		
		return costoConDescuento + calcularValorImpuestos(costoConDescuento);
	}
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		String tipo = cliente.getTipoCliente();
		int costo = 0;
		if (tipo.equals("Natural") && cliente instanceof ClienteNatural) {
			costo = COSTO_POR_KM_NATURAL;
		}
		
		else if (tipo.equals("Corporativo") && cliente instanceof ClienteCorporativo){
			costo = COSTO_POR_KM_CORPORATIVO;
		}

		int costoPorDistancia = calcularDistanciaVuelo(vuelo.getRuta()) * costo;
		
		return costoPorDistancia;
	}
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		double desc = 0.0;
		if (cliente.getTipoCliente().equals("Corporativo")) {
			ClienteCorporativo clienteCorp = (ClienteCorporativo) cliente;
			
			int tamano = clienteCorp.getTamanoEmpresa();

			
			if (tamano == 1) {
				desc = DESCUENTO_PEQ;
			}
			
			else if (tamano == 2) {
				desc = DESCUENTO_MEDIANAS;
			}
			
			else if (tamano == 3) {
				desc = DESCUENTO_GRANDES;
			}

			
		}
		return desc;
	}
}
