package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.MaintenanceHistory.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
