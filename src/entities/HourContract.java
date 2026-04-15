package entities;

import java.time.LocalDate;
import java.util.UUID;

public class HourContract {

    private final UUID uuid;
    private Worker ownerWorker;
    private LocalDate date;
    private Double valuePerHour;
    private Integer hours;

    public HourContract(
            Worker ownerWorker,
            LocalDate date,
            Double valuePerHour,
            Integer hours
    ) {
        uuid = UUID.randomUUID();
        this.ownerWorker = ownerWorker;
        this.date = date;
        this.valuePerHour = valuePerHour;
        this.hours = hours;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Worker getOwnerWorker() {
        return ownerWorker;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValuePerHour() {
        return valuePerHour;
    }

    public void setValuePerHour(Double valuePerHour) {
        this.valuePerHour = valuePerHour;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Double totalValue() {
        return valuePerHour * hours;
    }
}
