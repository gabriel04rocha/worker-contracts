package repositories;

import entities.HourContract;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractRepository {

  private List<HourContract> contracts;

  public ContractRepository() {
    contracts = new ArrayList<>();
  }

  public void addContract(HourContract contract) {
    contracts.add(contract);
  }

  public void removeContract(UUID uuid) {
    for (HourContract contract : contracts) {
      if (contract.getUuid().equals(uuid)) {
        contracts.remove(contract);
      }
    }
  }
}
