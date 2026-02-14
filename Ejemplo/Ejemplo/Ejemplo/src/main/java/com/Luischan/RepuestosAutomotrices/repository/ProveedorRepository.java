package com.Luischan.RepuestosAutomotrices.repository;

import com.Luischan.RepuestosAutomotrices.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {

    Optional<Proveedor> findByNombreProveedor(String nombreProveedor);
}