package com.amh.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
@IdClass(ProjectMemberId.class)
public class ProjectMember {
	@Id
	private int userId;
	@Id
	private int projectId;
	@Column(name = "role", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "projectId", referencedColumnName = "id")
	private Project project;

	public ProjectMember(){
		super();
	}
	public ProjectMember(int userId, Role role){
		super();
		this.userId = userId;
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof ProjectMember))
            return false;
        ProjectMember projectMember = (ProjectMember) obj;
        return projectMember.getRole() == this.getRole();
    }
 @Override
    public int hashCode() {
	 int result = 17;
        result = 31 * result + role.hashCode();
        return result;
    }
	
}

