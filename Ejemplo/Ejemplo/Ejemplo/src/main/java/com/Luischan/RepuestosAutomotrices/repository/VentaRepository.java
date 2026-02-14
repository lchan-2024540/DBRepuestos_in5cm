package com.Luischan.RepuestosAutomotrices.repository;

import com.Luischan.RepuestosAutomotrices.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    Optional<Venta> findByFechaVentaAndIdEmpleadoAndIdRepuesto(
            LocalDate fechaVenta,
            Integer idEmpleado,
            Integer idRepuesto
    );
}