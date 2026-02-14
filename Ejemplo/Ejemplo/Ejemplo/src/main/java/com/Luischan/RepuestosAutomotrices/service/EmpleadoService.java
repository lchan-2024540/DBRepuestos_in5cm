package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> getAllEmpleados();
    Empleado saveEmpleado(Empleado empleado);
    Empleado getEmpleadoById(Integer id);
    Empleado updateEmpleado(Integer id, Empleado empleado);
    void deleteEmpleado(Integer id);
}