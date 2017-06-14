package com.amh.pm.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true)

    @NotEmpty
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid e-mail")
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false, unique = false)
    @NotEmpty
    @Size(min = 3, max = 80, message = "Your password must between 3 and 80 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectMember", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "projectId", referencedColumnName = "id"))
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OrganizationMember", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "organizationId", referencedColumnName = "id"))
    private List<Organization> orgList;

    public User() {
        super();
    }

    public User(String email, String name, String password) {
        super();

        this.email = email;
        this.name = name;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Organization> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Organization> orgList) {
        this.orgList = orgList;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /*
     * @Override public boolean equals(Object obj) { if (obj == this) return true; if (!(obj instanceof User)) return false;
     * User user = (User) obj; return user.getName() == this.getName() && user.getEmail() == this.getEmail() &&
     * user.getPassword() == this.getPassword(); }
     * @Override public int hashCode() { int result = 17; result = 31 * result + name.hashCode(); result = 31 * result +
     * email.hashCode(); result = 31 * result + password.hashCode(); return result; }
     */

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        if (user.id == this.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
