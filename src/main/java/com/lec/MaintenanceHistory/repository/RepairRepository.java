package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.MaintenanceHistory.model.Repair;

public interface RepairRepository extends JpaRepository<Repair, Integer> {

}
