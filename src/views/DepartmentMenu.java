package views;

import entities.Department;
import repositories.DepartmentRepository;

import java.util.Optional;
import java.util.Scanner;

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
        int departmentCounter = 0;
        System.out.println("Departamentos cadastrados no sistema:");
        System.out.println();
        if (departments.getDepartments().isPresent()) {
            for (Department listDepartment : departments.getDepartments().get()) {
                System.out.printf("[%d] %s", departmentCounter, listDepartment.toString());
                System.out.println();
                departmentCounter++;
            }
        }
    }

    public static Department selectDepartment(
            DepartmentRepository departments
    ) {

        Scanner sc = new Scanner(System.in);

        Optional<Department> selectedDepartment;

        String selectedUuid;

        System.out.println("Lista de departamentos:");

        listDepartments(departments);

        System.out.print("Selecione um departamento para continuar: ");

        selectedDepartment = departments.getDepartmentByIndex(sc.nextInt());

        while (selectedDepartment.isEmpty()) {
            listDepartments(departments);
            System.out.println();
            System.out.print("ID inválido! Selecione novamente: ");

            selectedDepartment = departments.getDepartmentByIndex(sc.nextInt());
        }

        sc.close();
        return selectedDepartment.get();
    }
}
