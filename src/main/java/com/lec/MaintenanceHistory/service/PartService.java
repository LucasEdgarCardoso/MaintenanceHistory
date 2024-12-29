package com.lec.MaintenanceHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.Part;
import com.lec.MaintenanceHistory.repository.PartRepository;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public List<Part> findAll() {
        return partRepository.findAll();
    }

    public Part findById(Integer id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found with ID: " + id));
    }

    public Part save(Part part) {
        part.setId(null);
        return partRepository.save(part);
    }

    public void delete(Integer id) {
        Part part = findById(id);
        partRepository.delete(part);
    }

    public Part update(Integer id, Part updatedPart) {
        Part existingPart = findById(id);

        existingPart.setName(updatedPart.getName());
        existingPart.setDescription(updatedPart.getDescription());
        existingPart.setStockQuantity(updatedPart.getStockQuantity());

        return partRepository.save(existingPart);
    }

}