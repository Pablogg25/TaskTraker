package org.PabloGonzalez.TasksTraker.model;

public class Task {
    private int id;
    private String code;
    private String name;
    private String description;
    private String projectName;
    private double hours;
    private String status;

    public Task() {

    }

    public Task(int id, String code, String name, String description, String projectName, double hours, String status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.projectName = projectName;
        this.hours = hours;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", projectName='" + projectName + '\'' +
                ", hours=" + hours +
                ", status='" + status + '\'' +
                '}';
    }
}