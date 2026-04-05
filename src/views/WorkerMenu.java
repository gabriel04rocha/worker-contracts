package views;

import entities.Department;
import entities.Worker;
import entities.enums.WorkerLevel;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

public class WorkerMenu {

  private static int option;
  private static int innerOption;

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
          Worker selectedWorker = null;
          String departmentName;
          Worker worker;
          Double baseSalary;

          System.out.println("Digite as seguintes informações do trabalhador:");
          System.out.print("Nome: ");

          sc.nextLine();
          name = sc.nextLine();

          System.out.print("Nível (opções abaixo):");
          System.out.println();
          System.out.println("SENIOR");
          System.out.println("MID_LEVEL");
          System.out.println("JUNIOR");
          System.out.println();
          level = WorkerLevel.valueOf(sc.next());

          System.out.print(
            "Departamento (1) para criar um novo, (2) para selecionar um existente:"
          );

          int innerOption = sc.nextInt();

          if (innerOption == 1) {
            System.out.print("Digite o nome do novo departamento: ");

            departmentName = sc.nextLine();

            Department department = new Department(departmentName);

            departments.addDepartment(department);

            selectedDepartment = department;
          } else if (innerOption == 2) {
            do {
              optionalDepartment = DepartmentMenu.selectDepartment(departments);

              if (optionalDepartment.isEmpty()) {
                System.out.println(
                  "Departamento não encontrado! Tente novamente"
                );
              } else {
                selectedDepartment = optionalDepartment.get();
              }
            } while (selectedDepartment == null);
          }

          System.out.print("Salário-base: ");

          baseSalary = sc.nextDouble();

          worker = new Worker(name, level, baseSalary, selectedDepartment);

          workers.addWorker(worker);

          System.out.printf(
            "Trabalhador criado com sucesso!\n%s",
            worker.toString()
          );

          break;
        case 2:
          listWorkers(workers);
          break;
        case 3:
          do {
            selectWorker(workers);
          } while (selectedWorker == null);

          break;
        default:
          break;
      }
    } while (option != 5);

    sc.close();
  }

  public static void listWorkers(WorkerRepository workers) {
    System.out.println("Trabalhadores cadastrados no sistema:");
    System.out.println();
    for (Worker listWorker : workers.getWorkers().get()) {
      System.out.print(listWorker.toString());
      System.out.println();
    }
  }

  public static Optional<Worker> selectWorker(WorkerRepository workers) {
    Scanner sc = new Scanner(System.in);

    Optional<Worker> selectedWorker;

    String selectedUuid;

    System.out.println("Lista de trabalhadores:");

    for (Worker listWorker : workers.getWorkers().get()) {
      System.out.println(listWorker.toString());
      System.out.println();
    }

    System.out.print(
      "Selecione um trabalhador para continuar (Digite o UUID)."
    );

    selectedUuid = sc.next();

    selectedWorker = workers.getWorkerById(UUID.fromString(selectedUuid));

    sc.close();

    return selectedWorker;
  }
}
