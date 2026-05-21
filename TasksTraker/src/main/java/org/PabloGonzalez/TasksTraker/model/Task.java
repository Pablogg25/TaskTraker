package org.PabloGonzalez.TasksTraker.model;

public class Task {
    private int id;
    private String name;
    private String description;
    private double hours;
    private String status;

    public Task() {

    }

    public Task(int id, String name, String description, double hours, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", status='" + status + '\'' +
                '}';
    }
}