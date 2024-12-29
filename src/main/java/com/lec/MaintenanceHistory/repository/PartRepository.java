package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.MaintenanceHistory.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
