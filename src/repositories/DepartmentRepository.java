package repositories;

import entities.Department;

import java.util.*;

public class DepartmentRepository {

    private List<Department> departments = new ArrayList<>();

    public DepartmentRepository(Department department) {
        departments = new ArrayList<>();
        departments.add(department);
    }

    public Optional<List<Department>> getDepartments() {
        if (departments.size() > 0) {
            return Optional.of(Collections.unmodifiableList(departments));
        }
        return Optional.empty();
    }

    public Optional<Department> getDepartmentByIndex(int index) {
        if (index < 0 || index > (departments.size() - 1)) {
            return Optional.empty();
        }
        return Optional.of(departments.get(index));
    }

    public Optional<Department> getDepartmentById(UUID uuid) {
        for (Department department : departments) {
            if (department.getUuid().equals(uuid)) {
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(UUID uuid) {
        for (Department department : departments) {
            if (department.getUuid().equals(uuid)) {
                departments.remove(department);
            }
        }
    }
}
