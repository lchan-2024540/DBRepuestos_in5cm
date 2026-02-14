package com.Luischan.RepuestosAutomotrices.controller;

import com.Luischan.RepuestosAutomotrices.entity.Proveedor;
import com.Luischan.RepuestosAutomotrices.entity.Repuesto;
import com.Luischan.RepuestosAutomotrices.service.RepuestoService;
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
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {this.repuestoService = repuestoService; }

    @GetMapping
    List<Repuesto> getAllRepuestos() {return repuestoService.getAllRepuestos();}

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto) {
        try {
            Repuesto createdRepuesto = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createdRepuesto, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> getRepuestoById(@PathVariable Integer id) {
        Repuesto repuesto = repuestoService.getRepuestoById(id);
        if (repuesto != null) {
            return ResponseEntity.ok(repuesto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepuesto(@PathVariable Integer id, @Valid @RequestBody Repuesto repuesto) {
        try {
            Repuesto updatedRepuesto = repuestoService.updateRepuesto(id, repuesto);
            if (updatedRepuesto != null) {
                return ResponseEntity.ok(updatedRepuesto);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepuesto(@PathVariable Integer id) {
        repuestoService.deleteRepuesto(id);
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
