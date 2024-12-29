package com.lec.MaintenanceHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.Repair;
import com.lec.MaintenanceHistory.model.RepairPart;
import com.lec.MaintenanceHistory.repository.RepairRepository;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private RepairPartService repairPartService;

    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    public Repair findById(Integer id) {
        return repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair not found with ID: " + id));
    }

    public Repair save(Repair repair) {
        repair.setId(null);
        Repair savedRepair = repairRepository.save(repair);

        return savedRepair;
    }

    public void delete(Integer id) {
        Repair repair = findById(id);
        repairRepository.delete(repair);
    }

    public Repair update(Integer repairId, Repair updatedRepair) {
        Repair existingRepair = findById(repairId);

        existingRepair.setDate(updatedRepair.getDate());
        existingRepair.setDescription(updatedRepair.getDescription());

        return repairRepository.save(existingRepair);
    }

    public Repair addRepairPart(Integer repairId, RepairPart newRepairPart) {
        Repair existingRepair = findById(repairId);

        newRepairPart.setRepair(existingRepair);

        repairPartService.save(newRepairPart);

        existingRepair.getRepairParts().add(newRepairPart);

        return repairRepository.save(existingRepair);
    }

    public Repair removeRepairPart(Integer repairId, Integer repairPartId) {
        Repair existingRepair = findById(repairId);

        RepairPart repairPartToRemove = repairPartService.findById(repairPartId);

        existingRepair.getRepairParts().remove(repairPartToRemove);

        repairPartService.delete(repairPartToRemove.getId());

        return repairRepository.save(existingRepair);
    }

}