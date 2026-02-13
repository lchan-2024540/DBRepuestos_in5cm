package com.Luischan.RepuestosAutomotrices.service;


import com.Luischan.RepuestosAutomotrices.entity.Proveedor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Proveedor getProveedorById(Integer id);
    Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException;
    Proveedor updateProveedor(Integer id, Proveedor proveedor);
    void deleteProveedor(Integer id);
}