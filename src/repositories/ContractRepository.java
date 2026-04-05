package repositories;

import entities.HourContract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ContractRepository {

  private List<HourContract> contracts;

  public ContractRepository() {}

  public ContractRepository(HourContract contract) {
    contracts = new ArrayList<>();
    contracts.add(contract);
  }

  public List<HourContract> contracts() {
    return Collections.unmodifiableList(contracts);
  }

  public Optional<HourContract> getContractById(UUID uuid) {
    for (HourContract contract : contracts) {
      if (contract.getUuid().equals(uuid)) {
        return Optional.of(contract);
      }
    }
    return Optional.empty();
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
