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
import com.lec.MaintenanceHistory.model.Machine;
import com.lec.MaintenanceHistory.service.MachineService;

@RestController
@RequestMapping(value = "/Machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping()
    public ResponseEntity<List<Machine>> findAll() {
        return ResponseEntity.ok().body(machineService.findAll());
    }

    @GetMapping("/{idMachine}")
    public ResponseEntity<Machine> findById(@PathVariable Integer idMachine) {
        return ResponseEntity.ok().body(machineService.findById(idMachine));
    }

    @PostMapping()
    public ResponseEntity<Machine> save(@RequestBody Machine machine) {
        Machine machineAux = machineService.save(machine);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idMachine}")
                .buildAndExpand(machineAux.getId()).toUri();
        return ResponseEntity.created(uri).body(machineAux);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable Integer id) {
        try {
            machineService.delete(id);
            return ResponseEntity.ok(new DeleteResponseDTO("OK", "Machine deleted successfully!"));
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting machine: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> update(@PathVariable Integer id, @RequestBody Machine updatedMachine) {
        return ResponseEntity.ok().body(machineService.update(id, updatedMachine));
    }

}