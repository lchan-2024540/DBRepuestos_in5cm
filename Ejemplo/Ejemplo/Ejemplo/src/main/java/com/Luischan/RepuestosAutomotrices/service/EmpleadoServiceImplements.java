package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Empleado;
import com.Luischan.RepuestosAutomotrices.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServiceImplements implements EmpleadoService{
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws RuntimeException {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {
        Empleado empleadoExistente = empleadoRepository.findById(id).orElse(null);
        if (empleadoExistente != null) {
            empleadoExistente.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoExistente.setApellidoEmpleado(empleado.getApellidoEmpleado());
            empleadoExistente.setPuestoEmpleado(empleado.getPuestoEmpleado());
            empleadoExistente.setEmailEmpleado(empleado.getEmailEmpleado());
            return empleadoRepository.save(empleadoExistente);
        }
        return null;
    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
