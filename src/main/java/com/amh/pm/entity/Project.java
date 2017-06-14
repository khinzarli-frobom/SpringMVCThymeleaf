package com.amh.pm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = false)
    @NotEmpty
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    @NotEmpty
    private String description;

    @Column(name = "scheduleStartDate", nullable = false, unique = false)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date scheduleStartDate;

    @Column(name = "scheduleEndDate", nullable = false, unique = false)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    // @NotEmpty(message = "Please enter your end date.")
    private Date scheduleEndDate;

    @Column(name = "actualStartDate", nullable = false, unique = false)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date actualStartDate;

    @Column(name = "actualEndDate", nullable = false, unique = false)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date actualEndDate;

    @ManyToOne
    private Organization manager;
    

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectMember", joinColumns = @JoinColumn(name = "projectId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private List<User> users;

    public Project() {
        super();
    }

    public Project(int id, String name, String description, Date scheduleStartDate, Date scheduleEndDate, Date acturalStartDate, Date actualEndDate) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.actualStartDate = acturalStartDate;
        this.actualEndDate = actualEndDate;
    }

    public Project(String name, String description, Date scheduleStartDate, Date scheduleEndDate, Date acturalStartDate, Date actualEndDate) {
        super();
        this.name = name;
        this.description = description;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.actualStartDate = acturalStartDate;
        this.actualEndDate = actualEndDate;
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

    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Organization getManager() {
        return manager;
    }

    public void setManager(Organization manager) {
        this.manager = manager;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

  /*  @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Project))
            return false;
        Project project = (Project) obj;
        return project.getName() == this.getName() && project.getDescription() == this.getDescription() && project.getScheduleStartDate() == this.getScheduleStartDate()
                && project.getScheduleEndDate() == this.getActualEndDate() && project.getActualStartDate() == this.getScheduleStartDate()
                && project.getActualEndDate() == this.getActualEndDate();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + scheduleStartDate.hashCode();
        result = 31 * result + scheduleEndDate.hashCode();
        result = 31 * result + actualStartDate.hashCode();
        result = 31 * result + actualEndDate.hashCode();
        return result;
    }*/
}
