package entities;

import java.util.Optional;

public enum WorkerAttributes {
    NAME(1),
    BASESALARY(2),
    CONTRACTS(3),
    DEPARTMENT(4);

    public final int menuInput;

    WorkerAttributes(int menuInput) {
        this.menuInput = menuInput;
    }

    public int getMenuInput() {
        return menuInput;
    }

    public static Optional<WorkerAttributes> findByIdentifier(int identifier) {
        for (WorkerAttributes level : WorkerAttributes.values()) {
            if (level.getMenuInput() == identifier) {
                return Optional.of(level);
            }
        }
        return Optional.empty();
    }
}
