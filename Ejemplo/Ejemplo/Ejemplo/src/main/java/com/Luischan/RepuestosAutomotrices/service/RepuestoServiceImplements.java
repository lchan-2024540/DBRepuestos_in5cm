package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Repuesto;
import com.Luischan.RepuestosAutomotrices.repository.ProveedorRepository;
import com.Luischan.RepuestosAutomotrices.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImplements implements RepuestoService {

    private final RepuestoRepository repuestoRepository;
    private final ProveedorRepository proveedorRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository, ProveedorRepository proveedorRepository) {
        this.repuestoRepository = repuestoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) {
        validarProveedorExiste(repuesto.getIdProveedor());

        Optional<Repuesto> repuestoExistente = repuestoRepository
                .findByNombreRepuesto(repuesto.getNombreRepuesto());

        if (repuestoExistente.isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un repuesto con el nombre " + repuesto.getNombreRepuesto()
            );
        }

        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto getRepuestoById(Integer id) {
        return repuestoRepository.findById(id).orElse(null);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        Optional<Repuesto> repuestoExistente = repuestoRepository.findById(id);

        if (repuestoExistente.isPresent()) {
            validarProveedorExiste(repuesto.getIdProveedor());

            Optional<Repuesto> repuestoConMismoNombre = repuestoRepository
                    .findByNombreRepuesto(repuesto.getNombreRepuesto());

            if (repuestoConMismoNombre.isPresent() &&
                    !repuestoConMismoNombre.get().getIdRepuesto().equals(id)) {
                throw new IllegalArgumentException(
                        "Ya existe un repuesto con el nombre " + repuesto.getNombreRepuesto()
                );
            }

            Repuesto repuestoActualizado = repuestoExistente.get();
            repuestoActualizado.setNombreRepuesto(repuesto.getNombreRepuesto());
            repuestoActualizado.setCategoriaRepuesto(repuesto.getCategoriaRepuesto());
            repuestoActualizado.setPrecioCompra(repuesto.getPrecioCompra());
            repuestoActualizado.setPrecioVenta(repuesto.getPrecioVenta());
            repuestoActualizado.setIdProveedor(repuesto.getIdProveedor());

            return repuestoRepository.save(repuestoActualizado);
        }

        return null;
    }

    @Override
    public void deleteRepuesto(Integer id) {
        repuestoRepository.deleteById(id);
    }

    private void validarProveedorExiste(Integer idProveedor) {
        if (!proveedorRepository.existsById(idProveedor)) {
            throw new IllegalArgumentException(
                    "El proveedor con ID " + idProveedor + " no existe"
            );
        }
    }
}