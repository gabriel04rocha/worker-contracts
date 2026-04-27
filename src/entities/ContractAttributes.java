package entities;

import java.util.Optional;

public enum ContractAttributes {
    DATE(1),
    HOURLYWAGE(2),
    HOURS(3);

    public final int menuInput;

    ContractAttributes(int menuInput) {
        this.menuInput = menuInput;
    }

    public int getMenuInput() {
        return menuInput;
    }

    public static Optional<ContractAttributes> findByIdentifier(int identifier) {
        for (ContractAttributes level : ContractAttributes.values()) {
            if (level.getMenuInput() == identifier) {
                return Optional.of(level);
            }
        }
        return Optional.empty();
    }
}
