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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", nullable = false, unique = true)
	@NotEmpty
	private String name;

	@Column(name = "description", nullable = false, unique = false)
	@NotEmpty
	private String description;
	@ManyToOne
	private User owner;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "OrganizationMember", joinColumns = @JoinColumn(name = "organizationId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	private List<User> user;

	public Organization() {
		super();
	}

	public Organization(String name, String description, User owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	/*
	 * @Override public boolean equals(Object obj) { if (obj == this) return
	 * true; if (!(obj instanceof Organization)) return false; Organization
	 * organization = (Organization) obj; return organization.getName() ==
	 * this.getName() && organization.getDescription() == this.getDescription();
	 * }
	 * 
	 * @Override public int hashCode() { int result = 17; result = 31 * result +
	 * name.hashCode(); result = 31 * result + description.hashCode(); return
	 * result; }
	 */
}
