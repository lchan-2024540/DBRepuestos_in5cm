package com.Luischan.RepuestosAutomotrices.service;

import com.Luischan.RepuestosAutomotrices.entity.Venta;
import com.Luischan.RepuestosAutomotrices.repository.EmpleadoRepository;
import com.Luischan.RepuestosAutomotrices.repository.RepuestoRepository;
import com.Luischan.RepuestosAutomotrices.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImplements implements VentaService {

    private final VentaRepository ventaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RepuestoRepository repuestoRepository;

    public VentaServiceImplements(VentaRepository ventaRepository,
                                  EmpleadoRepository empleadoRepository,
                                  RepuestoRepository repuestoRepository) {
        this.ventaRepository = ventaRepository;
        this.empleadoRepository = empleadoRepository;
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta saveVenta(Venta venta) {
        validarEmpleadoExiste(venta.getIdEmpleado());
        validarRepuestoExiste(venta.getIdRepuesto());

        Optional<Venta> ventaExistente = ventaRepository
                .findByFechaVentaAndIdEmpleadoAndIdRepuesto(
                        venta.getFechaVenta(),
                        venta.getIdEmpleado(),
                        venta.getIdRepuesto()
                );

        if (ventaExistente.isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe una venta registrada para este empleado, repuesto y fecha"
            );
        }

        return ventaRepository.save(venta);
    }

    @Override
    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta updateVenta(Integer id, Venta venta) {
        Optional<Venta> ventaExistente = ventaRepository.findById(id);

        if (ventaExistente.isPresent()) {
            validarEmpleadoExiste(venta.getIdEmpleado());
            validarRepuestoExiste(venta.getIdRepuesto());

            Optional<Venta> ventaConMismaCombinacion = ventaRepository
                    .findByFechaVentaAndIdEmpleadoAndIdRepuesto(
                            venta.getFechaVenta(),
                            venta.getIdEmpleado(),
                            venta.getIdRepuesto()
                    );

            if (ventaConMismaCombinacion.isPresent() &&
                    !ventaConMismaCombinacion.get().getIdVenta().equals(id)) {
                throw new IllegalArgumentException(
                        "Ya existe una venta registrada para este empleado, repuesto y fecha"
                );
            }

            Venta ventaActualizada = ventaExistente.get();
            ventaActualizada.setFechaVenta(venta.getFechaVenta());
            ventaActualizada.setCantidad(venta.getCantidad());
            ventaActualizada.setTotal(venta.getTotal());
            ventaActualizada.setIdEmpleado(venta.getIdEmpleado());
            ventaActualizada.setIdRepuesto(venta.getIdRepuesto());

            return ventaRepository.save(ventaActualizada);
        }

        return null;
    }

    @Override
    public void deleteVenta(Integer id) {
        ventaRepository.deleteById(id);
    }

    private void validarEmpleadoExiste(Integer idEmpleado) {
        if (!empleadoRepository.existsById(idEmpleado)) {
            throw new IllegalArgumentException(
                    "El empleado con ID " + idEmpleado + " no existe"
            );
        }
    }

    private void validarRepuestoExiste(Integer idRepuesto) {
        if (!repuestoRepository.existsById(idRepuesto)) {
            throw new IllegalArgumentException(
                    "El repuesto con ID " + idRepuesto + " no existe"
            );
        }
    }
}