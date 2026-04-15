package views;

import entities.Department;
import entities.Worker;
import entities.enums.WorkerLevel;
import repositories.DepartmentRepository;
import repositories.WorkerRepository;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

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

            String name;
            WorkerLevel level;
            Optional<Department> optionalDepartment;
            Optional<Worker> optionalWorker;
            Optional<WorkerLevel> optionalWorkerLevel;
            Department selectedDepartment = null;
            Worker selectedWorker = null;
            int innerOption;
            String departmentName;
            Worker worker;
            Double baseSalary;

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
                    do {
                        optionalWorker = selectWorker(workers);

                        if (optionalWorker.isEmpty()) {
                            System.out.print("Trabalhador não encontrado! Tente novamente: ");
                        } else {
                            selectedWorker = optionalWorker.get();

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

                            innerOption = sc.nextInt();

                            switch (innerOption) {
                                case 1:
                                    System.out.print("Digite o novo nome do trabalhador: ");

                                    sc.nextLine();
                                    name = sc.nextLine();

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
                                    } while (optionalWorkerLevel.isPresent());
                                    break;
                                case 3:
                                    System.out.print("Digite o novo salário-base do trabalhador: ");
                                    System.out.println();
                                    baseSalary = sc.nextDouble();
                                    selectedWorker.setBaseSalary(baseSalary);
                                    break;
                                case 4:
                                    if (departments.getDepartments().isPresent()) {
                                        DepartmentMenu.listDepartments(departments);
                                    } else {

                                    }
                                    break;
                                default:

                                    break;
                            }
                        }
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
        if (workers.getWorkers().isPresent()) {
            for (Worker listWorker : workers.getWorkers().get()) {
                System.out.print(listWorker.toString());
                System.out.println();
            }
        }
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
            do {
                Optional<Department> optionalDepartment = DepartmentMenu.selectDepartment(departments);

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
