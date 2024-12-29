package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.MaintenanceHistory.model.RepairPart;

public interface RepairPartRepository extends JpaRepository<RepairPart, Integer> {

}
