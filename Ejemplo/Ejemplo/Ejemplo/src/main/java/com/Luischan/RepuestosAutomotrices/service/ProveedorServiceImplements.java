package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Proveedor;
import com.Luischan.RepuestosAutomotrices.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImplements implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        validarDominioEmail(proveedor.getEmailProveedor());

        Optional<Proveedor> proveedorExistente = proveedorRepository
                .findByNombreProveedor(proveedor.getNombreProveedor());

        if (proveedorExistente.isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un proveedor con el nombre " + proveedor.getNombreProveedor()
            );
        }

        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        Proveedor proveedorExistente = proveedorRepository.findById(id).orElse(null);
        if (proveedorExistente != null) {
            validarDominioEmail(proveedor.getEmailProveedor());

            Optional<Proveedor> proveedorConMismoNombre = proveedorRepository
                    .findByNombreProveedor(proveedor.getNombreProveedor());

            if (proveedorConMismoNombre.isPresent() &&
                    !proveedorConMismoNombre.get().getIdProveedor().equals(id)) {
                throw new IllegalArgumentException(
                        "Ya existe un proveedor con el nombre " + proveedor.getNombreProveedor()
                );
            }

            proveedorExistente.setNombreProveedor(proveedor.getNombreProveedor());
            proveedorExistente.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            proveedorExistente.setDireccion(proveedor.getDireccion());
            proveedorExistente.setEmailProveedor(proveedor.getEmailProveedor());
            return proveedorRepository.save(proveedorExistente);
        }
        return null;
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
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