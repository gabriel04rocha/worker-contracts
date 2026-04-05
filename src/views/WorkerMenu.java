package views;

import entities.Department;
import entities.Worker;
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
      System.out.println("[6] Ver todos os contratos de um trabalhador");
      System.out.println("[7] Voltar para o menu principal");
      System.out.println("[8] Sair");
      System.out.println();
      System.out.print("Sua resposta: ");

      option = sc.nextInt();

      switch (option) {
        case 1:
          String name;
          WorkerLevel level;
          Optional<Department> optionalDepartment;
          Department selectedDepartment = null;
          Worker worker;
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
            optionalDepartment = DepartmentMenu.selectDepartment(
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

          worker = new Worker(name, level, baseSalary, selectedDepartment);

          workers.addWorker(worker);

          break;
        case 2:
          System.out.println("Trabalhadores cadastrados no sistema:");
          System.out.println();
          for (Worker listWorker : workers.getWorkers()) {
            System.out.print(listWorker.toString());
            System.out.println();
          }

          break;
        case 3:
        default:
          break;
      }
    } while (option != 5);

    sc.close();
  }

  public static Optional<Worker> selectWorker(List<Worker> workers) {
    Scanner sc = new Scanner(System.in);

    String selectedWorker;

    System.out.println("Lista de trabalhadores:");

    for (Worker listWorker : workers) {
      System.out.println(listWorker.toString());
    }

    System.out.print("Selecione um trabalhador para continuar.");

    selectedWorker = sc.nextLine();

    sc.close();

    for (Department listDepartment : departments) {
      if (listDepartment.getName() == selectedDepartment) {
        return Optional.of(listDepartment);
      }
    }

    return Optional.empty();
  }
}
