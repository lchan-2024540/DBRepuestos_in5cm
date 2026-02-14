package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> getAllVentas();
    Venta saveVenta(Venta venta);
    Venta getVentaById(Integer id);
    Venta updateVenta(Integer id, Venta venta);
    void deleteVenta(Integer id);
}