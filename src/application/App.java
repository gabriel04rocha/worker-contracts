package application;

import entities.*;
import entities.enums.WorkerLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    List<Worker> workers = new ArrayList<>();
    List<HourContract> contracts = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    String name;
    WorkerLevel level;
    Department department;
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

    department = new Department(sc.nextLine());

    System.out.print("Insira agora o salário base deste trabalhador: ");

    baseSalary = sc.nextDouble();

    worker = new Worker(name, level, baseSalary, department);

    workers.add(worker);

    int option = 0;

    do {
      System.out.println("O que você deseja fazer agora?\n");
      System.out.println(
        "---------------------TRABALHADORES---------------------"
      );
      System.out.println(
        "\n[1] Cadastrar um novo trabalhador\n[2] Ver todos os trabalhadores cadastrados\n[3] Alterar um trabalhador\n[4] Mudar o departamento de um trabalhador\n[5] Excluir trabalhadores existentes\n"
      );
      System.out.println("---------------------CONTRATOS---------------------");
      System.out.println(
        "\n[6] Cadastrar um novo contrato\n[7] Ver todos os contratos cadastrados\n[8] Editar contratos\n[9] Deletar contratos existentes\n"
      );
      System.out.println(
        "---------------------DEPARTAMENTOS---------------------"
      );
      System.out.println(
        "\n[10] Cadastrar um novo departamento\n[11] Ver todos os departamentos cadastrados\n[12] Editar departamentos\n[13] Deletar departamentos existentes\n"
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
        default:
          break;
      }
    } while (option != 9);

    sc.close();
  }
}
