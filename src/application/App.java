package application;

import java.util.Scanner;

import entities.enums.WorkerLevel;
import entities.*;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Para começar a usar o sistema, primeiro cadastre um trabalhador:");

        System.out.print("Insira o nome do trabalhador: ");

        String name = sc.nextLine();

        System.out.println("Insira o o nível deste trabalhador dentre as opções descritas\n\n[JUNIOR]\n[MID_LEVEL]\n[SENIOR]\n");

        System.out.print("Sua escolha: ");

        WorkerLevel level = WorkerLevel.valueOf(sc.next());

        System.out.print("Insira o departamento deste trabalhador: ");

        sc.nextLine();

        Department department = new Department(sc.nextLine());

        System.out.print("Insira agora o salário base deste trabalhador: ");

        Double baseSalary = sc.nextDouble();

        int option = 0;

        do {

            System.out.println("\nO que você deseja fazer agora?\n[1] Mudar o departamento do trabalhador\n[2] Alterar o salário do trabalhador\n[3] Criar um novo contrato");

        } while (option != 9);
        
        sc.close();

    }
}
