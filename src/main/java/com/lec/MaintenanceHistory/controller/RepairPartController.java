package com.lec.MaintenanceHistory.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lec.MaintenanceHistory.dto.DeleteResponseDTO;
import com.lec.MaintenanceHistory.model.RepairPart;
import com.lec.MaintenanceHistory.service.RepairPartService;

@RestController
@RequestMapping(value = "/RepairPart")
public class RepairPartController {

    @Autowired
    private RepairPartService repairPartService;

    @GetMapping()
    public ResponseEntity<List<RepairPart>> findAll() {
        return ResponseEntity.ok().body(repairPartService.findAll());
    }

    @GetMapping("/{idRepairPart}")
    public ResponseEntity<RepairPart> findById(@PathVariable Integer idRepairPart) {
        return ResponseEntity.ok().body(repairPartService.findById(idRepairPart));
    }

    @PostMapping()
    public ResponseEntity<RepairPart> save(@RequestBody RepairPart repairPart) {
        RepairPart repairPartAux = repairPartService.save(repairPart);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRepairPart}")
                .buildAndExpand(repairPartAux.getId()).toUri();
        return ResponseEntity.created(uri).body(repairPartAux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepairPart> update(@PathVariable Integer id, @RequestBody RepairPart updatedRepairPart) {
        return ResponseEntity.ok().body(repairPartService.update(id, updatedRepairPart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable Integer id) {
        try {
            repairPartService.delete(id);
            return ResponseEntity.ok(new DeleteResponseDTO("OK", "Repair Part deleted successfully!"));
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting repair part: " + e.getMessage());
        }
    }

}