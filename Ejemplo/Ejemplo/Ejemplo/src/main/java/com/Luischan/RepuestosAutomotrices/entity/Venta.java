package com.Luischan.RepuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @NotNull(message = "La fecha de venta no puede estar vacía")
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Positive(message = "La cantidad debe ser mayor a 0")
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull(message = "El total no puede estar vacío")
    @Positive(message = "El total debe ser mayor a 0")
    @Column(name = "total")
    private Double total;

    @NotNull(message = "Debe seleccionar un empleado")
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @NotNull(message = "Debe seleccionar un repuesto")
    @Column(name = "id_repuesto")
    private Integer idRepuesto;

    // Getters and Setters

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }
}