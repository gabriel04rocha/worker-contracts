package entities;

import java.util.UUID;

public class Department {

    private final UUID uuid;
    private String name;

    public Department(String name) {
        uuid = UUID.randomUUID();
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Departamento %s\nUUID: %s", name, uuid.toString());
    }
}
