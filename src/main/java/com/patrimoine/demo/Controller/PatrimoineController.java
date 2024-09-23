package com.patrimoine.demo.Controller;

import com.patrimoine.demo.Model.Patrimoine;
import com.patrimoine.demo.Model.PatrimoineNotFoundException;
import com.patrimoine.demo.Service.PatrimoineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.*;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {
    private final PatrimoineService patrimoineService;

    public PatrimoineController(PatrimoineService patrimoineService) {
        this.patrimoineService = patrimoineService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> createOrUpdatePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) {
        try {
            Patrimoine updatedPatrimoine = patrimoineService.saveOrUpdatePatrimoine(id, patrimoine);
            return ResponseEntity.ok(updatedPatrimoine);
        } catch (PatrimoineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable String id) {
        try {
            Patrimoine patrimoine = patrimoineService.getPatrimoine(id);
            return ResponseEntity.ok(patrimoine);
        } catch (PatrimoineNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // logger.error("Erreur lors de la lecture du patrimoine avec ID " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
