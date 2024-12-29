package com.lec.MaintenanceHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.RepairPart;
import com.lec.MaintenanceHistory.repository.RepairPartRepository;

@Service
public class RepairPartService {

    @Autowired
    private RepairPartRepository repairPartRepository;

    public List<RepairPart> findAll() {
        return repairPartRepository.findAll();
    }

    public RepairPart findById(Integer id) {
        return repairPartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair Part not found with ID: " + id));
    }

    public RepairPart save(RepairPart repairPart) {
        repairPart.setId(null);
        return repairPartRepository.save(repairPart);
    }

    public RepairPart update(Integer id, RepairPart updatedPart) {
        RepairPart existingRepairPart = findById(id);

        existingRepairPart.setQuantityUsed(updatedPart.getQuantityUsed());
        existingRepairPart.setDescription(updatedPart.getDescription());

        return repairPartRepository.save(existingRepairPart);
    }

    public void delete(Integer id) {
        RepairPart repairPart = findById(id);
        repairPartRepository.delete(repairPart);
    }

}