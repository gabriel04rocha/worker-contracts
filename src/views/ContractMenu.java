package views;

import repositories.ContractRepository;

public class ContractMenu {

  public static void start(ContractRepository contracts) {
    System.out.println("---------------------CONTRATOS---------------------");
    System.out.println(
      "\n[6] Cadastrar um novo contrato\n[7] Ver todos os contratos cadastrados\n[8] Editar contratos\n[9] Deletar contratos existentes\n"
    );
  }
}
