package views;

import entities.Department;
import repositories.DepartmentRepository;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class DepartmentMenu {

    public static void start(DepartmentRepository departments) {
        System.out.println(
                "---------------------DEPARTAMENTOS---------------------"
        );
        System.out.println(
                "\n[10] Cadastrar um novo departamento\n[11] Ver todos os departamentos cadastrados\n[12] Editar departamentos\n[13] Deletar departamentos existentes\n[14]Sair\n"
        );
    }

    public static Department createDepartment(DepartmentRepository departments) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do novo departamento: ");

        String departmentName = sc.nextLine();

        Department department = new Department(departmentName);

        departments.addDepartment(department);

        return department;
    }

    public static void listDepartments(DepartmentRepository departments) {
        System.out.println("Departamentos cadastrados no sistema:");
        System.out.println();
        if (departments.getDepartments().isPresent()) {
            for (Department listDepartment : departments.getDepartments().get()) {
                System.out.print(listDepartment.toString());
                System.out.println();
            }
        }
    }

    public static Optional<Department> selectDepartment(
            DepartmentRepository departments
    ) {
        Scanner sc = new Scanner(System.in);

        Optional<Department> selectedDepartment;

        String selectedUuid;

        System.out.println("Lista de departamentos:");

        for (Department listDepartment : departments.getDepartments().get()) {
            System.out.println(listDepartment.toString());
            System.out.println();
        }

        System.out.print(
                "Selecione um departamento para continuar (Digite o UUID)."
        );

        selectedUuid = sc.next();

        selectedDepartment = departments.getDepartmentById(
                UUID.fromString(selectedUuid)
        );

        sc.close();

        return selectedDepartment;
    }
}
