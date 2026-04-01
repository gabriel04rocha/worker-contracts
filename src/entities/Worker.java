package entities;

import entities.enums.WorkerLevel;
import java.util.List;
import java.util.ArrayList;

public class Worker {

    private String name;
    private WorkerLevel level;
    private Double baseSalary;
    private List<HourContract> contracts = new ArrayList<>();
    private Department department;

    public Worker(String name, WorkerLevel level, Double baseSalary, List<HourContract> contracts, Department department) {

        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.contracts = contracts;
        this.department = department; 

    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {

        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department; 

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

    public void setLevel (WorkerLevel level) {

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

}
