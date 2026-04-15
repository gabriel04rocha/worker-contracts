package entities;

import entities.enums.WorkerLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Worker {

    private final UUID uuid;
    private String name;
    private WorkerLevel level;
    private Double baseSalary;
    private List<HourContract> contracts = new ArrayList<>();
    private Department department;

    public Worker(
            String name,
            WorkerLevel level,
            Double baseSalary,
            List<HourContract> contracts,
            Department department
    ) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.contracts = contracts;
        this.department = department;
    }

    public Worker(
            String name,
            WorkerLevel level,
            Double baseSalary,
            Department department
    ) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addContract(HourContract contract) {
        contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    @Override
    public String toString() {
        return String.format(
                "Trabalhador %s:\nUUID: %s\nNível: %s\nSalário-base: R$%.2f\nDepartamento: %s",
                name,
                uuid.toString(),
                level.toString(),
                baseSalary,
                department.getName()
        );
    }
}
