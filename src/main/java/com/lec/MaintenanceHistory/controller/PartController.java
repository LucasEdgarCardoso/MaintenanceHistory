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
import com.lec.MaintenanceHistory.model.Part;
import com.lec.MaintenanceHistory.service.PartService;

@RestController
@RequestMapping(value = "/Part")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping()
    public ResponseEntity<List<Part>> findAll() {
        return ResponseEntity.ok().body(partService.findAll());
    }

    @GetMapping("/{idPart}")
    public ResponseEntity<Part> findById(@PathVariable Integer idPart) {
        return ResponseEntity.ok().body(partService.findById(idPart));
    }

    @PostMapping()
    public ResponseEntity<Part> save(@RequestBody Part part) {
        Part partAux = partService.save(part);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPart}").buildAndExpand(partAux.getId())
                .toUri();
        return ResponseEntity.created(uri).body(partAux);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable Integer id) {
        try {
            partService.delete(id);
            return ResponseEntity.ok(new DeleteResponseDTO("OK", "Part deleted successfully!"));
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting part: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Part> update(@PathVariable Integer id, @RequestBody Part updatedPart) {
        return ResponseEntity.ok().body(partService.update(id, updatedPart));
    }

}