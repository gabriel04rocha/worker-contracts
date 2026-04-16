package repositories;

import entities.Worker;

import java.util.*;

public class WorkerRepository {

    private final List<Worker> workers;

    public WorkerRepository(Worker worker) {
        workers = new ArrayList<>();
        workers.add(worker);
    }

    public Optional<List<Worker>> getWorkers() {
        if (!workers.isEmpty()) {
            return Optional.of(Collections.unmodifiableList(workers));
        }
        return Optional.empty();
    }

    public Optional<Worker> getWorkerByIndex(int id) {
        if (id < 0 || id > (workers.size() - 1)) {
            return Optional.empty();
        }
        return Optional.of(workers.get(id));
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
