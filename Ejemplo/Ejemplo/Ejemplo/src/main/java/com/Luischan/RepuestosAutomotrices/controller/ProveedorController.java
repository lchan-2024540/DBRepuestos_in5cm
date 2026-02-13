package com.Luischan.RepuestosAutomotrices.controller;

import com.Luischan.RepuestosAutomotrices.entity.Proveedor;
import com.Luischan.RepuestosAutomotrices.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    List<Proveedor> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor) {
        try {
            Proveedor createdProveedor = proveedorService.saveProveedor(proveedor);
            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Integer id) {
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProveedor(@PathVariable Integer id, @Valid @RequestBody Proveedor proveedor) {
        try {
            Proveedor updatedProveedor = proveedorService.updateProveedor(id, proveedor);
            if (updatedProveedor != null) {
                return ResponseEntity.ok(updatedProveedor);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}