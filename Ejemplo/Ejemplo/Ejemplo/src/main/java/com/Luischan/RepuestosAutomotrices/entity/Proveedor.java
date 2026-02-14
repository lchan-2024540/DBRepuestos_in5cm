package com.Luischan.RepuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name ="Proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @NotNull(message = "El teléfono del proveedor no puede estar vacío")
    @Column(name = "telefono_proveedor")
    private Integer telefonoProveedor;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El email del proveedor no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    @Column(name = "email_proveedor")
    private String emailProveedor;


    //Generar getter and setter


    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public Integer getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setTelefonoProveedor(Integer telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }
}