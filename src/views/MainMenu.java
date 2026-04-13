package views;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;
import repositories.ContractRepository;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainMenu {

    private static int option;

    public static void start(
            WorkerRepository workers,
            DepartmentRepository departments,
            ContractRepository contracts
    ) {
        Scanner sc = new Scanner(System.in);

        Integer hours;
        Double hourlyWage;
        int initialContract;
        String name;
        WorkerLevel level;
        Double baseSalary;
        Worker worker;
        Department department;
        HourContract contract;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate contractDate;

        System.out.println(
                "Para começar a usar o sistema, primeiro cadastre um trabalhador:"
        );

        System.out.print("Insira o nome do trabalhador: ");

        name = sc.nextLine();

        System.out.println(
                "Insira o o nível deste trabalhador dentre as opções descritas abaixo:"
        );
        System.out.println();
        WorkerMenu.listLevels();
        System.out.println();
        System.out.print("Sua escolha: ");

        level = WorkerLevel.valueOf(sc.next());

        System.out.print("Insira o departamento deste trabalhador: ");

        sc.nextLine();

        department = new Department(sc.nextLine());

        departments = new DepartmentRepository(department);

        System.out.print("Insira agora o salário base deste trabalhador: ");

        baseSalary = sc.nextDouble();

        worker = new Worker(name, level, baseSalary, department);
        workers = new WorkerRepository(worker);

        System.out.print("Deseja incluir um contrato para este trabalhador? ");
        System.out.println();
        System.out.print("[1] Sim");
        System.out.print("[2] Não");
        System.out.println();
        System.out.print("Sua escolha: ");
        initialContract = sc.nextInt();

        if (initialContract == 1) {
            System.out.print("Digite as seguintes informações do contrato:");
            System.out.println();
            System.out.print("Data do contrato (DD/MM/AAAA): ");

            contractDate = LocalDate.parse(sc.next(), formatter);

            System.out.print("Valor por hora trabalhada: ");

            hourlyWage = sc.nextDouble();

            System.out.print("Quantidade de horas trabalhadas: ");

            hours = sc.nextInt();

            contract = new HourContract(worker, contractDate, hourlyWage, hours);

            contracts.addContract(contract);
        }

        option = 0;

        do {
            System.out.println("O que você deseja fazer agora?\n");
            System.out.println();
            System.out.println("[1] Trabalhadores");
            System.out.println("[2] Contratos");
            System.out.println("[3] Departamentos");
            System.out.println("[4] Sair");
            System.out.println();
            System.out.print("Sua escolha: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    WorkerMenu.start(workers, departments);
                    break;
                case 2:
                    ContractMenu.start(contracts);
                    break;
                case 3:
                    DepartmentMenu.start(departments);
                    break;
                default:
                    System.out.print("Opção inválida! Tente novamente:");
                    break;
            }
        } while (!(option < 5) && !(option > 1));

        sc.close();
    }
}
