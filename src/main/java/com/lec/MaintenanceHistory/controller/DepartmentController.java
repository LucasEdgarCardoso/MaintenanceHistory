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
import com.lec.MaintenanceHistory.model.Department;
import com.lec.MaintenanceHistory.service.DepartmentService;

@RestController
@RequestMapping(value = "/Department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(departmentService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Department> save(@RequestBody Department department) {
        Department departmentAux = departmentService.save(department);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(departmentAux.getId()).toUri();
        return ResponseEntity.created(uri).body(departmentAux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Integer id, @RequestBody Department updatedDepartment) {
        return ResponseEntity.ok().body(departmentService.update(id, updatedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable Integer id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.ok(new DeleteResponseDTO("OK", "Department deleted successfully!"));
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting department: " + e.getMessage());
        }
    }

}