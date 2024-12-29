package com.lec.MaintenanceHistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.MaintenanceHistory.model.Department;
import com.lec.MaintenanceHistory.model.Machine;
import com.lec.MaintenanceHistory.repository.MachineRepository;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private DepartmentService departmentService;

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public Machine findById(Integer id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found with ID: " + id));
    }

    public Machine save(Machine machine) {
        Department department = departmentService.findById(machine.getDepartment().getId());

        machine.setId(null);
        machine.setDepartment(department);

        return machineRepository.save(machine);
    }

    public void delete(Integer id) {
        Machine machine = findById(id);
        machineRepository.delete(machine);
    }

    public Machine update(Integer id, Machine updatedMachine) {
        Machine existingMachine = findById(id);
        Department department = departmentService.findById(updatedMachine.getDepartment().getId());

        existingMachine.setName(updatedMachine.getName());
        existingMachine.setModel(updatedMachine.getModel());
        existingMachine.setDepartment(department);

        return machineRepository.save(existingMachine);
    }

}