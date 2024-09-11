package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas{

	protected int COSTO_POR_KM = 1000;
	
	@Override
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		int costoConDescuento = (int) (costoBase * (1 - descuento));
		
		return costoConDescuento + calcularValorImpuestos(costoConDescuento);
	}
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		Ruta ruta = vuelo.getRuta();
		int costoPorDistancia = calcularDistanciaVuelo(ruta) * COSTO_POR_KM;
		
		return costoPorDistancia;
	}
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		return 0.0;
	}

}

