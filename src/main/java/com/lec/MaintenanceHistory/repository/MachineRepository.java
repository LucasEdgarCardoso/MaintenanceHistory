package com.lec.MaintenanceHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.MaintenanceHistory.model.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer> {

}
