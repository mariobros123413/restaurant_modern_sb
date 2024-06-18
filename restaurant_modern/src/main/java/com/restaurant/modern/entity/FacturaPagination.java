package com.restaurant.modern.entity;

import java.util.List;

public class FacturaPagination {
    private PaginaInfo paginaInfo;
    private List<Factura> facturas;

    public FacturaPagination(PaginaInfo paginaInfo, List<Factura> facturas) {
        this.paginaInfo = paginaInfo;
        this.facturas = facturas;
    }

	public PaginaInfo getPaginaInfo() {
		return paginaInfo;
	}

	public void setPageInfo(PaginaInfo paginaInfo) {
		this.paginaInfo = paginaInfo;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

    
}
