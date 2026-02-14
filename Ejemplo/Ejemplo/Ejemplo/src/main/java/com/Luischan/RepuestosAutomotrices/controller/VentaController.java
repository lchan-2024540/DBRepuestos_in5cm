package com.Luischan.RepuestosAutomotrices.controller;

import com.Luischan.RepuestosAutomotrices.entity.Venta;
import com.Luischan.RepuestosAutomotrices.service.VentaService;
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
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta) {
        try {
            Venta createdVenta = ventaService.saveVenta(venta);
            return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaService.getVentaById(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenta(@PathVariable Integer id, @Valid @RequestBody Venta venta) {
        try {
            Venta updatedVenta = ventaService.updateVenta(id, venta);
            if (updatedVenta != null) {
                return ResponseEntity.ok(updatedVenta);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
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