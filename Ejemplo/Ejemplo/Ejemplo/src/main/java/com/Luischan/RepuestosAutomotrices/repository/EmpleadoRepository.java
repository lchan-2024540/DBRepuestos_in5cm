package com.Luischan.RepuestosAutomotrices.repository;

import com.Luischan.RepuestosAutomotrices.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Optional<Empleado> findByNombreEmpleadoAndApellidoEmpleado(String nombreEmpleado, String apellidoEmpleado);
}