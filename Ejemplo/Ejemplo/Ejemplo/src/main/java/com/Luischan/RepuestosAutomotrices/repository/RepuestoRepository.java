package com.Luischan.RepuestosAutomotrices.repository;

import com.Luischan.RepuestosAutomotrices.entity.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    Optional<Repuesto> findByNombreRepuesto(String nombreRepuesto);
}