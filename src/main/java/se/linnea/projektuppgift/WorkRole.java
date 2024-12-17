package se.linnea.projektuppgift;

import java.sql.Date;

public class WorkRole {
    private Integer roleID;
    private String title;
    private String description;
    private double salary;
    private java.sql.Date creationDate;

    public WorkRole(String title, String description, double salary, Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public WorkRole(Integer roleID, String title, String description, double salary, Date creationDate) {
        this.roleID = roleID;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getSalary() {
        return salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "WorkRole{" +
                "roleID=" + roleID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", creationDate=" + creationDate +
                '}';
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
