package views;

import java.util.Scanner;

import entities.Department;
import entities.Worker;
import entities.enums.WorkerLevel;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

public class MainMenu {

  private static int option;

  public static void start() {
    Scanner sc = new Scanner(System.in);

    String name;
    WorkerLevel level;
    Double baseSalary;
    Worker worker;

    
    System.out.println(
        "Para começar a usar o sistema, primeiro cadastre um trabalhador:"
    );
    
    System.out.print("Insira o nome do trabalhador: ");
    
    name = sc.nextLine();
    
    System.out.println(
      "Insira o o nível deste trabalhador dentre as opções descritas\n\n[JUNIOR]\n[MID_LEVEL]\n[SENIOR]\n"
    );

    System.out.print("Sua escolha: ");

    level = WorkerLevel.valueOf(sc.next());

    System.out.print("Insira o departamento deste trabalhador: ");
    
    sc.nextLine();

    Department department = new Department(sc.nextLine());
    
    DepartmentRepository departments = new DepartmentRepository(department);

    System.out.print("Insira agora o salário base deste trabalhador: ");
    
    baseSalary = sc.nextDouble();
    
    worker = new Worker(name, level, baseSalary, department);
    
    WorkerRepository workers = new WorkerRepository(worker);
    
    int option = 0;

    do {
        System.out.println("O que você deseja fazer agora?\n");

        WorkerMenu.start(workers, departments);

        System.out.println("---------------------CONTRATOS---------------------");
        System.out.println(
        "\n[6] Cadastrar um novo contrato\n[7] Ver todos os contratos cadastrados\n[8] Editar contratos\n[9] Deletar contratos existentes\n"
      );
      System.out.println(
        "---------------------DEPARTAMENTOS---------------------"
      );
      System.out.println(
        "\n[10] Cadastrar um novo departamento\n[11] Ver todos os departamentos cadastrados\n[12] Editar departamentos\n[13] Deletar departamentos existentes\n[14]Sair\n"
      );
      System.out.print("Sua escolha: ");

      option = sc.nextInt();

      switch (option) {
        case 1:
          System.out.print("Insira o nome do trabalhador: ");

          name = sc.nextLine();

          System.out.println(
            "Insira o o nível deste trabalhador dentre as opções descritas\n\n[JUNIOR]\n[MID_LEVEL]\n[SENIOR]\n"
          );

          System.out.print("Sua escolha: ");

          level = WorkerLevel.valueOf(sc.next());

          System.out.print("Insira o departamento deste trabalhador: ");

          sc.nextLine();

          department = new Department(sc.nextLine());

          System.out.print("Insira agora o salário base deste trabalhador: ");

          baseSalary = sc.nextDouble();

          break;
        case 2:
          Worker selectedWorker = null;

          listWorkers(workers);

          System.out.print(
            "Selecione o trabalhador que deseja alterar inserindo seu nome: "
          );

          do {
            name = sc.nextLine();

            for (Worker listWorker : workers) {
              if (listWorker.getName().equals(name)) {
                selectedWorker = listWorker;
              }
            }

            if (selectedWorker == null) {
              System.out.print(
                "Não foi encontrado nenhum trabalhador com este nome. Tente novamente: "
              );
            }
          } while (selectedWorker == null);

          System.out.print(
            "Qual informação do trabalhador você deseja alterar?\n\n[1] Mudar o nome do trabalhador\n[2] Mudar o salário-base do trabalhador\n[3] Editar contratos (outro menu)\n[4] Mudar o departamento do trabalhador\n\nSua resposta: "
          );

          innerOption = sc.nextInt();

          switch (innerOption) {
            case 1:
              System.out.print("\nDigite o novo nome do trabalhador: ");

              name = sc.nextLine();

              selectedWorker.setName(name);

              break;
            case 2:
              System.out.print("\nDigite o novo salário do trabalhador: ");

              baseSalary = sc.nextDouble();

              selectedWorker.setBaseSalary(baseSalary);

              break;
            case 3:
              break;
            default:
              break;
          }

          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          break;
        case 8:
          break;
        case 9:
          break;
        case 10:
          break;
        case 11:
          break;
        case 12:
          break;
        case 13:
          break;
        default:
          break;
      }
    } while (option != 9);

    sc.close();
  }
  } 
}
