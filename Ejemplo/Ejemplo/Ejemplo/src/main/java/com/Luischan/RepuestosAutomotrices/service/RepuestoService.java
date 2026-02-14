package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAllRepuestos();
    Repuesto saveRepuesto(Repuesto repuesto);
    Repuesto getRepuestoById(Integer id);
    Repuesto updateRepuesto(Integer id, Repuesto repuesto);
    void deleteRepuesto(Integer id);
}