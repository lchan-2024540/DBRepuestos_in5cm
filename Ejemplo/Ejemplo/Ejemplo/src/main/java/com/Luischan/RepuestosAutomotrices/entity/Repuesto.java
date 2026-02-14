package com.Luischan.RepuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Repuestos")
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Integer idRepuesto;

    @NotBlank(message = "El nombre del repuesto no puede estar vacío")
    @Column(name = "nombre_repuesto")
    private String nombreRepuesto;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Column(name = "categoria_repuesto")
    private String categoriaRepuesto;

    @NotNull(message = "El precio de compra no puede estar vacío")
    @Positive(message = "El precio de compra debe ser mayor a 0")
    @Column(name = "precio_compra")
    private Double precioCompra;

    @NotNull(message = "El precio de venta no puede estar vacío")
    @Positive(message = "El precio de venta debe ser mayor a 0")
    @Column(name = "precio_venta")
    private Double precioVenta;

    @NotNull(message = "Debe seleccionar un proveedor")
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    // Getters and Setters

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public String getCategoriaRepuesto() {
        return categoriaRepuesto;
    }

    public void setCategoriaRepuesto(String categoriaRepuesto) {
        this.categoriaRepuesto = categoriaRepuesto;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}