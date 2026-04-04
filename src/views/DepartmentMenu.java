package views;

import entities.Department;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DepartmentMenu {

  public static Optional<Department> selectDepartments(
    List<Department> departments
  ) {
    Scanner sc = new Scanner(System.in);

    String selectedDepartment;

    System.out.println("Lista de departamentos:");

    for (Department listDepartment : departments) {
      System.out.println(listDepartment.getName());
    }

    System.out.print("Selecione um departamento para continuar.");

    selectedDepartment = sc.nextLine();

    sc.close();

    for (Department listDepartment : departments) {
      if (listDepartment.getName() == selectedDepartment) {
        return Optional.of(listDepartment);
      }
    }

    return Optional.empty();
  }
}
