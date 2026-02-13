package com.Luischan.RepuestosAutomotrices.controller;

import com.Luischan.RepuestosAutomotrices.entity.Empleado;
import com.Luischan.RepuestosAutomotrices.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado) {
        try {
            Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Integer id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody Empleado empleado) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
            if (updatedEmpleado != null) {
                return ResponseEntity.ok(updatedEmpleado);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}