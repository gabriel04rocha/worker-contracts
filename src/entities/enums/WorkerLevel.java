package entities.enums;

import java.util.Optional;

public enum WorkerLevel {
    JUNIOR(1),
    MID_LEVEL(2),
    SENIOR(3);

    public int MenuInput;

    WorkerLevel(int menuInput) {
        this.menuInput = menuInput;
    }

    public int getMenuInput() {
        return menuInput;
    }

    public static Optional<WorkerLevel> findByIdentifier(int identifier) {
        for (WorkerLevel level : WorkerLevel.values()) {
            if (level.getMenuInput() == identifier) {
                return Optional.of(level);
            }
        }
        return Optional.empty();
    }
}
