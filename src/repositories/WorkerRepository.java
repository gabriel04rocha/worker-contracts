package repositories;

import entities.Worker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WorkerRepository {

  private List<Worker> workers;

  public WorkerRepository(Worker worker) {
    workers = new ArrayList<>();
    workers.add(worker);
  }

  public List<Worker> getWorkers() {
    return Collections.unmodifiableList(workers);
  }

  public Optional<Worker> getWorkerById(UUID uuid) {
    for (Worker worker : workers) {
      if (worker.getUuid().equals(uuid)) {
        return Optional.of(worker);
      }
    }

    return Optional.empty();
  }

  public void addWorker(Worker worker) {
    workers.add(worker);
  }

  public void removeWorker(UUID uuid) {
    for (Worker worker : workers) {
      if (worker.getUuid().equals(uuid)) {
        workers.remove(worker);
      }
    }
  }
}
