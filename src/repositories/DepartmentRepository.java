package repositories;

import entities.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentRepository {

  private List<Department> departments = new ArrayList<>();

  public DepartmentRepository(Department department) {
    departments = new ArrayList<>();
    departments.add(department);
  }

  public List<Department> getDepartments() {
    return departments;
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
