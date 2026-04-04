package views;

import entities.Department;
import entities.enums.WorkerLevel;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

public class WorkerMenu {

  private static int option;

  public static void start(
    WorkerRepository workers,
    DepartmentRepository departments
  ) {
    Scanner sc = new Scanner(System.in);

    do {
      System.out.println(
        "---------------------TRABALHADORES---------------------"
      );
      System.out.println();
      System.out.println("[1] Cadastrar um novo trabalhador");
      System.out.println("[2] Ver todos os trabalhadores cadastrados");
      System.out.println("[3] Alterar um trabalhador");
      System.out.println("[4] Mudar o departamento de um trabalhador");
      System.out.println("[5] Excluir trabalhadores existentes");
      System.out.println("[6] Voltar para o menu principal");
      System.out.println("[7] Sair");
      System.out.println();
      System.out.print("Sua resposta: ");

      option = sc.nextInt();

      switch (option) {
        case 1:
          String name;
          WorkerLevel level;
          Optional<Department> optionalDepartment;
          Department selectedDepartment = null;
          Double baseSalary;

          System.out.println("Digite as seguintes informações do trabalhador:");
          System.out.print("Nome: ");

          sc.nextLine();
          name = sc.nextLine();

          System.out.println();
          System.out.println("SENIOR");
          System.out.println("MID_LEVEL");
          System.out.println("JUNIOR");
          System.out.println();
          System.out.print("Nível (opções acima):");
          System.out.println();
          level = WorkerLevel.valueOf(sc.next());

          List<Department> departmentList = departments.getDepartments();

          do {
            optionalDepartment = DepartmentMenu.selectDepartments(
              departmentList
            );
            if (optionalDepartment.isEmpty()) {
              System.out.println(
                "Departamento não encontrado! Tente novamente"
              );
            } else {
              selectedDepartment = optionalDepartment.get();
            }
          } while (selectedDepartment == null);

          System.out.print("Salário-base: ");

          baseSalary = sc.nextDouble();

          break;
        default:
          break;
      }
    } while (option != 5);

    sc.close();
  }
}
