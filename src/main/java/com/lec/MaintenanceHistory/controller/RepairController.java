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
import com.lec.MaintenanceHistory.model.Repair;
import com.lec.MaintenanceHistory.model.RepairPart;
import com.lec.MaintenanceHistory.service.RepairService;

@RestController
@RequestMapping(value = "/Repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping()
    public ResponseEntity<List<Repair>> findAll() {
        return ResponseEntity.ok().body(repairService.findAll());
    }

    @GetMapping("/{idRepair}")
    public ResponseEntity<Repair> findById(@PathVariable Integer idRepair) {
        return ResponseEntity.ok().body(repairService.findById(idRepair));
    }

    @PostMapping()
    public ResponseEntity<Repair> save(@RequestBody Repair repair) {
        Repair repairAux = repairService.save(repair);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRepair}").buildAndExpand(repairAux.getId())
                .toUri();
        return ResponseEntity.created(uri).body(repairAux);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable Integer id) {
        try {
            repairService.delete(id);
            return ResponseEntity.ok(new DeleteResponseDTO("OK", "Repair deleted successfully!"));
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting repair: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repair> update(@PathVariable Integer id, @RequestBody Repair updatedRepair) {
        return ResponseEntity.ok().body(repairService.update(id, updatedRepair));
    }

    @PostMapping("/{repairId}/parts")
    public ResponseEntity<Repair> addRepairPart(@PathVariable Integer repairId, @RequestBody RepairPart newRepairPart) {
        return ResponseEntity.ok().body(repairService.addRepairPart(repairId, newRepairPart));
    }

    @DeleteMapping("/{repairId}/parts/{repairPartId}")
    public ResponseEntity<Repair> removeRepairPart(@PathVariable Integer repairId, @PathVariable Integer repairPartId) {
        return ResponseEntity.ok().body(repairService.removeRepairPart(repairId, repairPartId));
    }

}