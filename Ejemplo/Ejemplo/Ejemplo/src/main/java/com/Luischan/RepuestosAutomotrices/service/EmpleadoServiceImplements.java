package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Empleado;
import com.Luischan.RepuestosAutomotrices.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        validarDominioEmail(empleado.getEmailEmpleado());

        Optional<Empleado> empleadoExistente = empleadoRepository
                .findByNombreEmpleadoAndApellidoEmpleado(
                        empleado.getNombreEmpleado(),
                        empleado.getApellidoEmpleado()
                );

        if (empleadoExistente.isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un empleado con el nombre " +
                            empleado.getNombreEmpleado() + " " +
                            empleado.getApellidoEmpleado()
            );
        }

        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(id);

        if (empleadoExistente.isPresent()) {
            validarDominioEmail(empleado.getEmailEmpleado());

            Optional<Empleado> empleadoConMismoNombre = empleadoRepository
                    .findByNombreEmpleadoAndApellidoEmpleado(
                            empleado.getNombreEmpleado(),
                            empleado.getApellidoEmpleado()
                    );

            if (empleadoConMismoNombre.isPresent() &&
                    !empleadoConMismoNombre.get().getIdEmpleado().equals(id)) {
                throw new IllegalArgumentException(
                        "Ya existe un empleado con el nombre " +
                                empleado.getNombreEmpleado() + " " +
                                empleado.getApellidoEmpleado()
                );
            }

            Empleado empleadoActualizado = empleadoExistente.get();
            empleadoActualizado.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoActualizado.setApellidoEmpleado(empleado.getApellidoEmpleado());
            empleadoActualizado.setPuestoEmpleado(empleado.getPuestoEmpleado());
            empleadoActualizado.setEmailEmpleado(empleado.getEmailEmpleado());

            return empleadoRepository.save(empleadoActualizado);
        }

        return null;
    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }

    private void validarDominioEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }

        String emailLowerCase = email.toLowerCase().trim();

        boolean esValido = emailLowerCase.endsWith("@gmail.com") ||
                emailLowerCase.endsWith("@yahoo.com") ||
                emailLowerCase.endsWith("@yahoo.es") ||
                emailLowerCase.endsWith("@outlook.com") ||
                emailLowerCase.endsWith("@outlook.es");

        if (!esValido) {
            throw new IllegalArgumentException("El correo debe ser de gmail, yahoo u outlook");
        }
    }
}