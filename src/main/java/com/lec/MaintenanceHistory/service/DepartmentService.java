package com.lec.MaintenanceHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.Department;
import com.lec.MaintenanceHistory.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
    }

    public Department save(Department department) {
        department.setId(null);
        return departmentRepository.save(department);
    }

    public void delete(Integer id) {
        Department department = findById(id);
        departmentRepository.delete(department);
    }

    public Department update(Integer id, Department updatedDepartment) {
        Department existingDepartment = findById(id);

        existingDepartment.setName(updatedDepartment.getName());
        existingDepartment.setPhone(updatedDepartment.getPhone());

        return departmentRepository.save(existingDepartment);
    }

}