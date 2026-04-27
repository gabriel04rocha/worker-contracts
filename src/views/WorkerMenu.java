package views;

import entities.Department;
import entities.Worker;
import entities.WorkerLevel;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

import java.util.Optional;
import java.util.Scanner;

public class WorkerMenu {

    private static int innerOption;

    public static void start(
            WorkerRepository workers,
            DepartmentRepository departments
    ) {
        Scanner sc = new Scanner(System.in);

        int option;
        do {
            System.out.println(
                    "---------------------TRABALHADORES---------------------"
            );
            System.out.println();
            System.out.println("[1] Cadastrar um novo trabalhador");
            System.out.println("[2] Ver todos os trabalhadores cadastrados");
            System.out.println("[3] Alterar um trabalhador");
            System.out.println("[5] Excluir trabalhadores existentes");
            System.out.println("[6] Ver todos os contratos de um trabalhador");
            System.out.println("[7] Voltar para o menu principal");
            System.out.println("[8] Sair");
            System.out.println();
            System.out.print("Sua resposta: ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    createWorker(departments, workers);
                    break;
                case 2:
                    if (workers.getWorkers().isPresent()) {
                        listWorkers(workers);
                    } else {
                        System.out.println("Não há trabalhadores cadastrados no sistema!");
                    }
                    break;
                case 3:
                    updateWorkers(workers, departments);
                    break;
                case 4:
                default:
                    break;
            }
        } while (option != 5);

        sc.close();
    }

    public static void listWorkers(WorkerRepository workers) {
        System.out.println("Trabalhadores cadastrados no sistema:");
        System.out.println();
        if (workers.getWorkers().isPresent()) {
            for (Worker listWorker : workers.getWorkers().get()) {
                System.out.print(listWorker.toString());
                System.out.println();
            }
        }
    }

    public static void updateWorkers(WorkerRepository workers, DepartmentRepository departments) {

        Scanner sc = new Scanner(System.in);

        Optional<WorkerLevel> optionalWorkerLevel;
        Optional<Worker> optionalWorker;
        Department selectedDepartment = null;
        Worker selectedWorker = null;

        selectedWorker = selectWorker(workers);

        System.out.printf(
                "Trabalhador selecionado:\n\n%s",
                selectedWorker.toString()
        );

        System.out.print(
                "Qual informação do trabalhador você deseja alterar?"
        );
        System.out.println();
        System.out.println("[1] Nome");
        System.out.println("[2] Nível");
        System.out.println("[3] Salário-base");
        System.out.println("[4] Departamento");
        System.out.println();

        do {
            innerOption = sc.nextInt();

            switch (innerOption) {
                case 1:
                    System.out.print("Digite o novo nome do trabalhador: ");

                    sc.nextLine();
                    String name = sc.nextLine();

                    selectedWorker.setName(name);

                    System.out.print(selectedWorker.toString());
                    break;
                case 2:
                    do {
                        System.out.println("Digite o novo nível do trabalhador de acordo com as opções abaixo:\n");
                        System.out.println();
                        listLevels();
                        System.out.println();
                        System.out.print("Sua escolha: ");

                        optionalWorkerLevel = WorkerLevel.findByIdentifier(sc.nextInt());

                        if (optionalWorkerLevel.isPresent()) {
                            selectedWorker.setLevel(optionalWorkerLevel.get());
                        } else {
                            System.out.println("Opção não identificada! Tente novamente: ");
                        }
                    } while (optionalWorkerLevel.isEmpty());
                    break;
                case 3:
                    System.out.print("Digite o novo salário-base do trabalhador: ");
                    System.out.println();
                    Double baseSalary = sc.nextDouble();
                    selectedWorker.setBaseSalary(baseSalary);
                    break;
                case 4:
                    if (departments.getDepartments().isPresent()) {
                        selectedDepartment = DepartmentMenu.selectDepartment(departments);
                        selectedWorker.setDepartment(selectedDepartment);
                        System.out.printf("Novo departamento do trabalhador %s: %s", selectedWorker.getName(), selectedWorker.getDepartment());
                    } else {
                        System.out.println("Não há departamentos cadastrados no sistema!");
                        System.out.println("Deseja criar um agora?");
                        System.out.println();
                        System.out.println("[1] Sim");
                        System.out.println("[2] Não");
                        System.out.println();
                        System.out.println("Sua escolha: ");

                        innerOption = sc.nextInt();

                        if (innerOption == 1) {
                            selectedDepartment = DepartmentMenu.createDepartment(departments);

                            selectedWorker.setDepartment(selectedDepartment);

                            System.out.printf("Novo departamento do trabalhador %s: %s", selectedWorker.getName(), selectedDepartment.getName());
                        }
                    }
                    break;
                default:
                    System.out.print("Opção inválida! Tente novamente: ");
                    break;
            }
        } while (innerOption < 0 || innerOption > 4);
    }

    public static void listLevels() {
        for (WorkerLevel level : WorkerLevel.values()) {
            System.out.printf("[%d] %s", level.ordinal() + 1, level.toString());
        }
    }

    public static Worker createWorker(DepartmentRepository departments, WorkerRepository workers) {
        Scanner sc = new Scanner(System.in);
        Department selectedDepartment = null;
        System.out.println("Digite as seguintes informações do trabalhador:");
        System.out.print("Nome: ");

        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Nível (opções abaixo):");
        listLevels();
        System.out.println();
        WorkerLevel level = WorkerLevel.valueOf(sc.next());

        System.out.println("Departamento:");
        System.out.println();
        System.out.println("[1] Criar um novo:");
        System.out.println("[2] Selecionar um existente:");
        System.out.println();
        System.out.println("Sua escolha:");

        innerOption = sc.nextInt();

        if (innerOption == 1) {
            selectedDepartment = DepartmentMenu.createDepartment(departments);
        } else if (innerOption == 2) {
            selectedDepartment = DepartmentMenu.selectDepartment(departments);
        }

        System.out.print("Salário-base: ");

        Double baseSalary = sc.nextDouble();

        Worker worker = new Worker(name, level, baseSalary, selectedDepartment);

        workers.addWorker(worker);

        System.out.printf(
                "Trabalhador criado com sucesso!\n%s",
                worker.toString()
        );

        sc.close();

        return worker;
    }

    public static Worker selectWorker(WorkerRepository workers) {
        Scanner sc = new Scanner(System.in);

        Optional<Worker> selectedWorker;
        int option;
        String selectedUuid;

        System.out.println("Lista de trabalhadores:");

        if (workers.getWorkers().isPresent()) {
            listWorkers(workers);
        } else {
            System.out.print("Não há trabalhadores cadastrados no sistema! Deseja cadastrar um?");
            System.out.println();
            System.out.println("[1] Sim");
            System.out.println("[2] Não");
            System.out.println();
            System.out.println("Sua escolha: ");

            option = sc.nextInt();

            if (option == 1) {
                createWorker(workers);
            }
        }

        System.out.print("Selecione um trabalhador para continuar:");

        selectedWorker = workers.getWorkerByIndex(sc.nextInt());

        while (selectedWorker.isEmpty()) {
            listWorkers(workers);
            System.out.println();
            System.out.print("ID inválido! Selecione novamente: ");

            selectedWorker = workers.getWorkerByIndex(sc.nextInt());
        }

        sc.close();
        return selectedWorker.get();
    }
}
